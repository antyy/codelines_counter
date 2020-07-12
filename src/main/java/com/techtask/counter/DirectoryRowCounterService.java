package com.techtask.counter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class DirectoryRowCounterService implements RowCounterService {
    private final String directoryName;
    Map<String, Map<String, Integer>> result;
    RowCounter rowCounter = new RowCounter();
    StringBuilder sb = new StringBuilder();

    public DirectoryRowCounterService(String directoryName) {
        this.directoryName = directoryName;
    }

    @Override
    public void countRows() {
        Map<String, List<File>> files;

        try {
            files = Files.walk(Paths.get(directoryName))
                    .map(Path::toFile)
                    .filter(File::isFile)
                    .collect(groupingBy(File::getParent, toList()));
        } catch (IOException e) {
            System.out.println("Failed to count rows");
            throw new RuntimeException(e);
        }

        result = files.entrySet()
                .stream()
                .collect(toMap(Map.Entry::getKey, t -> countRowsForFiles(t.getValue())));

    }

    private Map<String, Integer> countRowsForFiles(List<File> files) {

        return files.stream().collect(toMap(File::getName, rowCounter::countRows));
    }

    @Override
    public String getFormattedResult() {
        result.entrySet()
                .forEach(this::formatCountedData);

        return sb.toString();

    }

    private void formatCountedData(Map.Entry<String, Map<String, Integer>> entry) {
        sb.append(entry.getKey())
                .append(' ')
                .append(':')
                .append(' ')
                .append(getSumOfCountedLines(entry))
                .append(' ');

        entry.getValue().entrySet().forEach(this::formatSubdirectoryData);
        sb.append('\n');
    }

    private int getSumOfCountedLines(Map.Entry<String, Map<String, Integer>> entry) {
        return entry.getValue()
                .values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private void formatSubdirectoryData(Map.Entry<String, Integer> entry) {
        sb.append(entry.getKey())
                .append(' ')
                .append(':')
                .append(' ')
                .append(entry.getValue())
                .append(' ');
    }

    public static void main(String[] args) throws Exception {
        Map<String, List<File>> files = Files.walk(Paths.get("src/main/resources/root"))
                .map(Path::toFile)
                .filter(File::isFile)
                .collect(groupingBy(File::getParent, toList()));
        files.entrySet().stream().forEach(System.out::println);
    }
}

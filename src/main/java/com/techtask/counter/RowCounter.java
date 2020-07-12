package com.techtask.counter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RowCounter {

    public Integer countRows(Path f) {
        StreamRowCounter src = new StreamRowCounter();
        try {
            Files.lines(f)
                    .map(String::trim)
                    .forEach(src::consumeCurrentRow);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return src.numberOfRows;
    }

    public Integer countRows(File f) {
        return countRows(Paths.get(f.getPath()));
    }

    public static class StreamRowCounter {
        private int numberOfRows;
        private boolean openComment;
        private static final String OPENING_COMMENT = "/*";
        private static final String CLOSING_COMMENT = "/*";


        public void consumeCurrentRow(String row) {
            System.out.println("Current row = " + row);
            if (row.isEmpty()) {
                return;
            }
            if (openComment) {
                if (row.contains("*/")) {
                    openComment = false;
                }
                return;
            }

            if (row.startsWith("//")) {
                return;
            }
            if (row.startsWith("/*")) {
                if (row.contains("*/")) {
                    row = row.substring(row.indexOf(OPENING_COMMENT), row.indexOf(CLOSING_COMMENT)).trim();
                    return;
                }
                openComment = true;
                return;
            }
            numberOfRows++;
        }

        private void resolveStringComment(String row) {
            if (row.contains("*/")) {
                while (row.startsWith("/*") && row.contains("*/")) {
                    row = row.substring(row.indexOf(OPENING_COMMENT), row.indexOf(CLOSING_COMMENT)).trim();
                }
                if (row.isEmpty() || row.startsWith("//")) {
                    return;
                }

                return;
            }
        }

    }

    public static void main(String[] args) {
        String row = "/*dfgdsf dfg dsfg sd*/  /**fgdfgdg df */  */ ";
        row = row.substring(row.indexOf("/*"), row.indexOf("*/")).trim();
        System.out.println(row);
    }
}

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
            System.err.println("Failed to count rows for " + f);
            return 0;
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
        private static final String CLOSING_COMMENT = "*/";
        private static final String ONE_LINE_COMMENT = "//";


        public void consumeCurrentRow(String row) {
            if (row.isEmpty()) {
                return;
            }
            if (openComment) {
                if (row.contains(CLOSING_COMMENT)) {
                    openComment = false;
                    row = row.substring(row.indexOf(CLOSING_COMMENT) + CLOSING_COMMENT.length()).trim();
                    handleClosingComment(row);
                }
                return;
            }

            if (row.startsWith(ONE_LINE_COMMENT)) {
                return;
            }

            if (row.startsWith(OPENING_COMMENT)) {
                if (row.contains(CLOSING_COMMENT)) {
                    handleClosingComment(row);
                } else {
                    openComment = true;
                }
                return;
            }
            numberOfRows++;
        }

        private void handleClosingComment(String row) {
            while (row.startsWith(OPENING_COMMENT) && row.contains(CLOSING_COMMENT)) {
                row = row.substring(row.indexOf(CLOSING_COMMENT) + CLOSING_COMMENT.length()).trim();
            }
            if (row.isEmpty() || row.startsWith("//")) {
                return;
            }
            if (row.startsWith(OPENING_COMMENT)) {
                openComment = true;
                return;
            }
            numberOfRows++;
        }
    }
}

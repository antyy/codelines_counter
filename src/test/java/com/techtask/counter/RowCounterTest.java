package com.techtask.counter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static com.google.common.truth.Truth.assertThat;

class RowCounterTest {
    RowCounter rowCounter;

    @BeforeEach
    public void init() {
        rowCounter = new RowCounter();
    }

    @Test
    @DisplayName("Should return 8 rows for mixed types of comments")
    public void countRows_returned8() {
        String className = "src/test/resources/TestClass.java";
        Integer expected = 8;

        Integer actual = rowCounter.countRows(Paths.get(className));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Should return 6 rows for multiline comment")
    public void countRows_multilineComment() {
        String className = "src/test/resources/MultilineComment.java";
        Integer expected = 6;

        Integer actual = rowCounter.countRows(Paths.get(className));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Should return 5 rows for single line comment")
    public void countRows_returned5() {
        String className = "src/test/resources/SingleLineComment.java";
        Integer expected = 5;

        Integer actual = rowCounter.countRows(Paths.get(className));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Should return 10 for corner cases ")
    public void countRows_returned_10() {
        String className = "src/test/resources/CornerCases.java";
        Integer expected = 10;

        Integer actual = rowCounter.countRows(Paths.get(className));

        assertThat(actual).isEqualTo(expected);
    }
}
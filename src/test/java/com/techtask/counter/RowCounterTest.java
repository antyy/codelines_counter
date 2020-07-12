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
//
//    @Test
//    @DisplayName("Class with multiple comments should return 8")
//    public void countRows_returned8() throws Exception {
//        String className = "src/test/resources/TestClass.java";
//        Integer expected = 8;
//
//        Integer actual = rowCounter.countRows(Paths.get(className));
//
//        assertThat(actual).isEqualTo(expected);
//    }
//
//    @Test
//    @DisplayName("Class with one multiple comment should return 6")
//    public void countRows_returned6() throws Exception {
//        String className = "src/test/resources/MultilineComment.java";
//        Integer expected = 6;
//
//        Integer actual = rowCounter.countRows(Paths.get(className));
//
//        assertThat(actual).isEqualTo(expected);
//    }
//
//    @Test
//    @DisplayName("Class with one single line comments should return 5")
//    public void countRows_returned5() throws Exception {
//        String className = "src/test/resources/SingleLineComment.java";
//        Integer expected = 5;
//
//        Integer actual = rowCounter.countRows(Paths.get(className));
//
//        assertThat(actual).isEqualTo(expected);
//    }
    @Test
    @DisplayName("Class with multiple comments and corner cases should return 10")
    public void countRows_returned9() throws Exception {
        String className = "src/test/resources/CornerCases.java";
        Integer expected = 10;

        Integer actual = rowCounter.countRows(Paths.get(className));

        assertThat(actual).isEqualTo(expected);
    }
}
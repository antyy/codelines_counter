package com.techtask.counter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static java.util.Arrays.asList;

class DirectoryRowCounterServiceTest {
    private DirectoryRowCounterService directoryRowCounterService;

    @BeforeEach
    public void init() {
        directoryRowCounterService = new DirectoryRowCounterService();
    }

    @Test
    @DisplayName("Should return five rows for complex tree directory structure")
    public void countRows_directoryTReeStructure_5directories() {
        String root = "src/test/resources/root";
        String[] expected = {"root : 2 SomeRoot.java : 2 ",
                "level2_11 : 16 MultilineComment.java : 6 CornerCases.java : 10 ",
                "level1_2 : 62 Service.java : 62 ",
                "level2_111 : 5 SingleLineComment.java : 5 ",
                "level1_1 : 18 TestClass.java : 8 SomeClass.java : 10 "};
        String actual = directoryRowCounterService.countRows(root);
        List<String> actualList = asList(actual.split("\n"));
        assertThat(actualList).containsExactly(expected);
    }

    @Test
    @DisplayName("Should return one row for one file in folder")
    public void countRows_directoryTReeStructure_1directory() {
        String root = "src/test/resources/root/level1_2";
        String expected = "level1_2 : 62 Service.java : 62 \n";

        String actual = directoryRowCounterService.countRows(root);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Should return empty response for empty folder")
    public void countRows_empty_folder() {
        String root = "src/test/resources/emptyFolder";
        String actual = directoryRowCounterService.countRows(root);

        assertThat(actual).isEmpty();
    }

    @Test
    @DisplayName("Should return one row for one file in deep tree")
    public void countRows_tree_folder() {
        String root = "src/test/resources/tree_1_file";
        String expected = "third : 10 CornerCases.java : 10 \n";

        String actual = directoryRowCounterService.countRows(root);

        assertThat(actual).isEqualTo(expected);
    }
}
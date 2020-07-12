package com.techtask.counter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

class SingleFileRowCounterServiceTest {
    private SingleFileRowCounterService service;

    @BeforeEach
    public void init() {
        service = new SingleFileRowCounterService();
    }

    @Test
    @DisplayName("Should return 10 rows for CornerCases.java class")
    void countRows() {
        String expected = "CornerCases.java : 10";

        String actual = service.countRows("src/test/resources/CornerCases.java");

        assertThat(actual).isEqualTo(expected);
    }
}
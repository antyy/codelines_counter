package com.techtask.counter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

class SingleFileRowCounterServiceTest {
    private SingleFileRowCounterService service;

    @BeforeEach
    public void init() {
        service = new SingleFileRowCounterService();
    }

    @Test
    void countRows() {
        String expected = "CornerCases.java : 10";

        String actual = service.countRows("src/test/resources/CornerCases.java");

        assertThat(actual).isEqualTo(expected);
    }
}
package com.example.project;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class IntegrationTests {

    @Test
    void test_filter_with_fixedEventWindow() {
        CapturingConsumer<List<Integer>> pipelineResult = new CapturingConsumer<>();
        FixedEventWindow fixedEventWindow = new FixedEventWindow(3, pipelineResult);
        Filter pipelineStart = new Filter(i -> i > 0, fixedEventWindow::onReceive);

        pipelineStart.onReceive(3);
        pipelineStart.onReceive(-7);
        pipelineStart.onReceive(2);
        pipelineStart.onReceive(6);

        assertThat(pipelineResult.getCapturedValue(), equalTo(Arrays.asList(3, 2, 6)));
    }
}

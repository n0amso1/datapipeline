package com.example.project;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;

public class IntegrationTests {

    @Test
    void test_filter_with_fixedEventWindow() {
        Consumer<List<Integer>> callback = System.out::println;
        FixedEventWindow fixedEventWindow = new FixedEventWindow(3, callback);
        Filter filter = new Filter(i -> i > 0, fixedEventWindow::onReceive);
        filter.onReceive(3);
        filter.onReceive(-7);
        filter.onReceive(2);
        filter.onReceive(6);
        // todo assert [3,2,6] without looking at stdout
    }
}

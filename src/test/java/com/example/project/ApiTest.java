package com.example.project;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;

public class ApiTest {

    @Test
    void test() {
        Consumer<List<Integer>> callback = System.out::println;
        BuildingBlock fixedEventWindow = new FixedEventWindow(3, callback);
        BuildingBlock filter = new Filter(i -> i > 0, fixedEventWindow::onReceive);
        filter.onReceive(3);
        filter.onReceive(-7);
        filter.onReceive(2);
        filter.onReceive(6);
        // todo assert [3,2,6] without looking at stdout
    }
}

package com.example.project;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class FoldMedian {

    private final Consumer<Double> mCallback;

    public FoldMedian(Consumer<Double> callback) {
        mCallback = callback;
    }

    public void onReceive(List<Integer> nums) {
        mCallback.accept(calcMedian(nums));
    }

    private static double calcMedian(List<Integer> nums) {
        Collections.sort(nums);
        if (nums.size() % 2 == 1) {
            return nums.get(nums.size() / 2);
        }
        int rightMiddleIndex = nums.size() / 2;
        int leftMiddleIndex = rightMiddleIndex - 1;
        return average(nums.get(leftMiddleIndex),
                nums.get(rightMiddleIndex));
    }

    private static double average(int a, int b) {
        return (a + b) / 2.0;
    }
}

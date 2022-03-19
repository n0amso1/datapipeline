package com.example.project.buildingblocks;

import com.example.project.datapipeline.BuildingBlock;
import com.example.project.datapipeline.DataPipelineBuilder;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class FoldMedian implements BuildingBlock {

    private final Consumer<Double> mCallback;

    public static DataPipelineBuilder.Factory<BuildingBlock> create() {
        return (callback) -> new FoldMedian((Consumer<Double>) callback);
    }

    FoldMedian(Consumer<Double> callback) {
        mCallback = callback;
    }

    void onReceive(List<Integer> nums) {
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

    @Override
    public Consumer<?> getInputFunc() {
        return (consumer) -> this.onReceive((List<Integer>) consumer);
    }
}

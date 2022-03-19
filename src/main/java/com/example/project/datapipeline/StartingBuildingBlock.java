package com.example.project.datapipeline;

import java.util.function.Consumer;

public interface StartingBuildingBlock extends BuildingBlock {
    void onReceive(int num);

    default Consumer<?> getInputFunc() {
        return (Consumer<Integer>) this::onReceive;
    }
}

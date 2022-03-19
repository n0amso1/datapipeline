package com.example.project.datapipeline;

import java.util.function.Consumer;

public interface BuildingBlock {
    Consumer<?> getInputFunc();
}

# Fluent API Example

```java
DataPipeline pipeline = DataPipelineBuilder.startingWith(Filter.by(i -> i > 0))
        .andThen(FixedEventWindow.withSize(2))
        .andThen(FoldSum.create())
        .andThen(FixedEventWindow.withSize(3))
        .andThen(FoldMedian.create())
        .andFinally(System.out::println);

// from now on, feed data to pipeline.onReceive(num)
// for example:
pipeline.onReceive(5);
pipeline.onReceive(-4);
pipeline.onReceive(3);
```

# Live examples -
At `GivenExamplesTest`

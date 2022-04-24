package io.vigg.jstan.methods.sample.algorithm;

public class AlgorithmBuilder {

    private String engine="nuts";
    private Integer maxDepth=10;
    private String metric="diag_e";
    private String metricFile="metric_file.csv";
    private Integer stepsize=1;
    private Integer stepSizeJitter=0;

    public AlgorithmBuilder setEngine(String engine) {
        this.engine = engine;
        return this;
    }

    public AlgorithmBuilder setMaxDepth(Integer maxDepth) {
        this.maxDepth = maxDepth;
        return this;
    }

    public AlgorithmBuilder setMetric(String metric) {
        this.metric = metric;
        return this;
    }

    public AlgorithmBuilder setMetricFile(String metricFile) {
        this.metricFile = metricFile;
        return this;
    }

    public AlgorithmBuilder setStepsize(Integer stepsize) {
        this.stepsize = stepsize;
        return this;
    }

    public AlgorithmBuilder setStepSizeJitter(Integer stepSizeJitter) {
        this.stepSizeJitter = stepSizeJitter;
        return this;
    }

    public Algorithm build() {
        return new Algorithm(engine, maxDepth, metric, metricFile, stepsize, stepSizeJitter);
    }
}
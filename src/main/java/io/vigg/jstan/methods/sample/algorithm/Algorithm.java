package io.vigg.jstan.methods.sample.algorithm;


import io.vigg.jstan.StanCommandComponent;

public class Algorithm extends StanCommandComponent {

    private String engine;
    private Integer maxDepth;
    private String metric;
    private String metricFile;
    private Integer stepsize;
    private Integer stepSizeJitter;

    public Algorithm(
            String engine,
            Integer maxDepth,
            String metric,
            String metricFile,
            Integer stepsize,
            Integer stepSizeJitter
    ) {
        this.engine = engine;
        this.maxDepth = maxDepth;
        this.metric = metric;
        this.metricFile = metricFile;
        this.stepsize = stepsize;
        this.stepSizeJitter = stepSizeJitter;
    }

    @Override
    public String genCmd() {
        return String.format("""
                algorithm=hmc hmc engine=%s max_depth=%s \
                metric=%s metric_file=%s step_size=%s \
                step_size_jitter=%s""",
                getEngine(),
                getMaxDepth(),
                getMetric(),
                getMetricFile(),
                getStepsize(),
                getStepSizeJitter()
                );
    }

    public String getEngine() {
        return engine;
    }

    public Integer getMaxDepth() {
        return maxDepth;
    }

    public String getMetric() {
        return metric;
    }

    public String getMetricFile() {
        return metricFile;
    }

    public Integer getStepsize() {
        return stepsize;
    }

    public Integer getStepSizeJitter() {
        return stepSizeJitter;
    }

}

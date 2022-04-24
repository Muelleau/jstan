package io.vigg.jstan.methods;

public class SampleMethod extends StanMethod {

    private Integer numSamples = 1000;
    private Integer numWarmup = 1000;
    private Integer saveWarmup = 0;
    private Integer thin = 1;

    public SampleMethod() {
        this.cliText = "sample";
    }

    @Override
    public String genCmd() {
        return null;
    }
}

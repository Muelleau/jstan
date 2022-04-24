package io.vigg.jstan.methods.sample;

import io.vigg.jstan.methods.sample.adapt.Adapt;
import io.vigg.jstan.methods.sample.algorithm.Algorithm;

public class SampleBuilder {

    private Integer numSamples = 1000;
    private Integer numWarmup = 1000;
    private Integer saveWarmup = 0;
    private Integer thin = 1;
    private Adapt adapt;
    private Algorithm algorithm;

    public SampleBuilder setNumSamples(Integer numSamples) {
        this.numSamples = numSamples;
        return this;
    }

    public SampleBuilder setNumWarmup(Integer numWarmup) {
        this.numWarmup = numWarmup;
        return this;
    }

    public SampleBuilder setSaveWarmup(Integer saveWarmup) {
        this.saveWarmup = saveWarmup;
        return this;
    }

    public SampleBuilder setThin(Integer thin) {
        this.thin = thin;
        return this;
    }

    public SampleBuilder setAdapt(Adapt adapt) {
        this.adapt = adapt;
        return this;
    }

    public SampleBuilder setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
        return this;
    }

    public Sample build() {
        return new Sample(numSamples, numWarmup, saveWarmup, thin, adapt, algorithm);
    }
}
package io.vigg.jstan.methods.sample;

import io.vigg.jstan.StanCommandComponent;
import io.vigg.jstan.methods.StanMethod;
import io.vigg.jstan.methods.sample.adapt.Adapt;
import io.vigg.jstan.methods.sample.algorithm.Algorithm;

public class Sample extends StanMethod {

    private Integer numSamples;
    private Integer numWarmup;
    private Integer saveWarmup;
    private Integer thin;
    private Adapt adapt;
    private Algorithm algorithm;

    public Sample(Integer numSamples,
                  Integer numWarmup,
                  Integer saveWarmup,
                  Integer thin,
                  Adapt adapt,
                  Algorithm algorithm
    ) {
        this.numSamples = numSamples;
        this.numWarmup = numWarmup;
        this.saveWarmup = saveWarmup;
        this.thin = thin;
        this.adapt = adapt;
        this.algorithm = algorithm;
    }

    public Integer getNumSamples() {
        return numSamples;
    }

    public Integer getNumWarmup() {
        return numWarmup;
    }

    public Integer getSaveWarmup() {
        return saveWarmup;
    }

    public Integer getThin() {
        return thin;
    }

    public Adapt getAdapt() {
        return adapt;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    @Override
    public String genCmd() {
        return String.format("""
                        sample \
                        num_samples=%s \
                        num_warmup=%s \
                        save_warmup=%s \
                        thin=%s \
                        %s \
                        %s
                        """,
                getNumSamples().toString(),
                getNumWarmup().toString(),
                getSaveWarmup().toString(),
                getThin().toString(),
                adapt.genCmd(),
                algorithm.genCmd()
        ).stripTrailing().stripLeading();
    }
}

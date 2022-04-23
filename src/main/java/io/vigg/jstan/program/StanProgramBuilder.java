package io.vigg.jstan.program;

import io.vigg.jstan.data.StanData;
import io.vigg.jstan.init.StanInit;
import io.vigg.jstan.methods.StanMethod;
import io.vigg.jstan.output.StanOutput;
import io.vigg.jstan.random.StanRandom;

public class StanProgramBuilder {

    private StanMethod method;
    private StanData data;
    private StanOutput output;
    private StanInit init;
    private StanRandom random;

    public StanProgramBuilder setMethod(StanMethod method) {
        this.method = method;
        return this;
    }

    public StanProgramBuilder setData(StanData data) {
        this.data = data;
        return this;
    }

    public StanProgramBuilder setOutput(StanOutput output) {
        this.output = output;
        return this;
    }

    public StanProgramBuilder setInit(StanInit init) {
        this.init = init;
        return this;
    }

    public StanProgramBuilder setRandom(StanRandom random) {
        this.random = random;
        return this;
    }

    public StanProgram build() {
        return new StanProgram(method, data, output, init, random);
    }
}
package io.vigg.jstan.program;

import io.vigg.jstan.chain.StanChain;
import io.vigg.jstan.data.StanData;
import io.vigg.jstan.init.StanInit;
import io.vigg.jstan.methods.StanMethod;
import io.vigg.jstan.model.StanModel;
import io.vigg.jstan.output.StanOutput;
import io.vigg.jstan.random.StanRandom;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class StanProgramBuilder {

    private String id;
    private StanModel model;
    private StanMethod method;
    private StanData data;
    private StanOutput output;
    private StanInit init;
    private StanRandom random;
    private StanChain chain;

    public StanProgramBuilder setMethod(StanMethod method) {
        this.method = method;
        return this;
    }

    public StanProgramBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public StanProgramBuilder setModel(StanModel model) {
        this.model = model;
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

    public StanProgram build() throws IOException {
        return new StanProgram(id, model, method, data, output, init, random);
    }
}
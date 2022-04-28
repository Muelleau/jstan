package io.vigg.jstan.model;

import io.vigg.jstan.model.data.ModelData;
import io.vigg.jstan.model.parameters.ModelParameters;
import io.vigg.jstan.model.spec.ModelSpec;

public class StanModel {

    private ModelData data;
    private ModelParameters parameters;
    private ModelSpec spec;

    String model;

    public String getModel() {
        return model;
    }

    public StanModel(String model) {
        this.model = model;
    }

    public StanModel(
            ModelData data,
            ModelParameters parameters,
            ModelSpec spec
    ) { }

    private String buildModelString() {
        return "";
    }

}



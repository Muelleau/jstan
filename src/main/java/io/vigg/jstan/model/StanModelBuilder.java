package io.vigg.jstan.model;

public class StanModelBuilder {

    private String model;

    public StanModelBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public StanModel build() {
        return new StanModel(model);
    }

}
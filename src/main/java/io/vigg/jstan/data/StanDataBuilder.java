package io.vigg.jstan.data;

public class StanDataBuilder {
    private String path;

    public StanDataBuilder setPath(String path) {
        this.path = path;
        return this;
    }

    public StanData build() {
        return new StanData(path);
    }
}
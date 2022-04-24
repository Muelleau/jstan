package io.vigg.jstan.random;

public class StanRandomBuilder {
    private Long seed;

    public StanRandomBuilder setSeed(Long seed) {
        this.seed = seed;
        return this;
    }

    public StanRandom build() {
        return new StanRandom(seed);
    }
}
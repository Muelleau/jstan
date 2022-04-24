package io.vigg.jstan.random;

public class StanRandomBuilder {
    private Long seed;

    public StanRandomBuilder setSeed(Long seed) {
        this.seed = seed;
        return this;
    }

    public StanRandom createStanRandom() {
        return new StanRandom(seed);
    }
}
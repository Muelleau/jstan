package io.vigg.jstan.random;

public class StanRandom {

    private Long seed = 3252652196L;

    public StanRandom(Long seed) {
        this.seed = seed;
    }

    public Long getSeed() {
        return seed;
    }

}

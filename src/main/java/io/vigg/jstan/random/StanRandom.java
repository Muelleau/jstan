package io.vigg.jstan.random;

import io.vigg.jstan.StanCommandComponent;

public class StanRandom extends StanCommandComponent {

    private Long seed = 3252652196L;

    public StanRandom(Long seed) {
        this.seed = seed;
    }

    public Long getSeed() {
        return seed;
    }

    @Override
    public String genCmd() {
        return String.format("""
                random \
                seed=%s""", getSeed().toString());
    }
}

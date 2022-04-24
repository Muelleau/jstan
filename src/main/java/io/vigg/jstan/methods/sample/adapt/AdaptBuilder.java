package io.vigg.jstan.methods.sample.adapt;

public class AdaptBuilder {

    private Integer engaged     = 1;
    private Double gamma        = 0.05;
    private Double delta        = 0.8;
    private Double kappa        = 0.75;
    private Integer t0          = 10;
    private Integer initBugger  = 75;
    private Integer termBuffer  = 50;
    private Integer window      = 25;

    public AdaptBuilder setEngaged(Integer engaged) {
        this.engaged = engaged;
        return this;
    }

    public AdaptBuilder setGamma(Double gamma) {
        this.gamma = gamma;
        return this;
    }

    public AdaptBuilder setDelta(Double delta) {
        this.delta = delta;
        return this;
    }

    public AdaptBuilder setKappa(Double kappa) {
        this.kappa = kappa;
        return this;
    }

    public AdaptBuilder setT0(Integer t0) {
        this.t0 = t0;
        return this;
    }

    public AdaptBuilder setInitBugger(Integer initBugger) {
        this.initBugger = initBugger;
        return this;
    }

    public AdaptBuilder setTermBuffer(Integer termBuffer) {
        this.termBuffer = termBuffer;
        return this;
    }

    public AdaptBuilder setWindow(Integer window) {
        this.window = window;
        return this;
    }

    public Adapt build() {
        return new Adapt(engaged, gamma, delta, kappa, t0, initBugger, termBuffer, window);
    }
}
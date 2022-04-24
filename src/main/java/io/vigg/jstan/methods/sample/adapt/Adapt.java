package io.vigg.jstan.methods.sample.adapt;

import io.vigg.jstan.StanCommandComponent;

public class Adapt extends StanCommandComponent {

    private Integer engaged;
    private Double gamma;
    private Double delta;
    private Double kappa;
    private Integer t0;
    private Integer initBugger;
    private Integer termBuffer;
    private Integer window;

    public Adapt(
            Integer engaged,
            Double gamma,
            Double delta,
            Double kappa,
            Integer t0,
            Integer initBugger,
            Integer termBuffer,
            Integer window
    ) {
        this.engaged = engaged;
        this.gamma = gamma;
        this.delta = delta;
        this.kappa = kappa;
        this.t0 = t0;
        this.initBugger = initBugger;
        this.termBuffer = termBuffer;
        this.window = window;
    }

    @Override
    public String genCmd() {
        return String.format("""
        adapt engaged=%s gamma=%s delta=%s kappa=%s t0=%s \
        init_buffer=%s term_buffer=%s window=%s
        """,
            getEngaged(),
            getGamma(),
            getDelta(),
            getKappa(),
            getT0(),
            getInitBugger(),
            getTermBuffer(),
            getWindow()
        ).stripLeading().stripTrailing();
    }

    public Integer getEngaged() {
        return engaged;
    }

    public Double getGamma() {
        return gamma;
    }

    public Double getDelta() {
        return delta;
    }

    public Double getKappa() {
        return kappa;
    }

    public Integer getT0() {
        return t0;
    }

    public Integer getInitBugger() {
        return initBugger;
    }

    public Integer getTermBuffer() {
        return termBuffer;
    }

    public Integer getWindow() {
        return window;
    }
}

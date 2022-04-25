package io.vigg.jstan.output;

public class StanOutputBuilder {

    private Integer refresh = 100;
    private Integer sigFigs = 2;

    public StanOutputBuilder setRefresh(Integer refresh) {
        this.refresh = refresh;
        return this;
    }

    public StanOutputBuilder setSigFigs(Integer sigFigs) {
        this.sigFigs = sigFigs;
        return this;
    }

    public StanOutput build() {
        return new StanOutput(refresh, sigFigs);
    }
}
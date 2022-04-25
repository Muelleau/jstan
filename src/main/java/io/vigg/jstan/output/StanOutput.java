package io.vigg.jstan.output;

import io.vigg.jstan.StanCommandComponent;

public class StanOutput extends StanCommandComponent {

    private Integer refresh;
    private Integer sigFigs;

    public Integer getRefresh() {
        return refresh;
    }

    public Integer getSigFigs() {
        return sigFigs;
    }

    public StanOutput(Integer refresh, Integer sigFigs) {
        this.refresh = refresh;
        this.sigFigs = sigFigs;
    }

    @Override
    public String genCmd() {
        return String.format("""
                """,
                getRefresh().toString(),
                getSigFigs().toString()
        ).stripTrailing().stripLeading();
    }

}

package io.vigg.jstan.output;

public class StanOutputBuilder {

    private String file;
    private String diagnosticFile;
    private Integer refresh;
    private Integer sigFigs;

    public StanOutputBuilder setFile(String file) {
        this.file = file;
        return this;
    }

    public StanOutputBuilder setDiagnosticFile(String diagnosticFile) {
        this.diagnosticFile = diagnosticFile;
        return this;
    }

    public StanOutputBuilder setRefresh(Integer refresh) {
        this.refresh = refresh;
        return this;
    }

    public StanOutputBuilder setSigFigs(Integer sigFigs) {
        this.refresh = refresh;
        return this;
    }

    public StanOutput build() {
        return new StanOutput(file, diagnosticFile, refresh, sigFigs);
    }
}
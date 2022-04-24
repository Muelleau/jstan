package io.vigg.jstan.output;

import io.vigg.jstan.StanCommandComponent;

public class StanOutput extends StanCommandComponent {

    private String file="output.csv";
    private String diagnosticFile="diag.csv";
    private Integer refresh=100;
    private Integer sigFigs=2;

    public String getFile() {
        return file;
    }

    public String getDiagnosticFile() {
        return diagnosticFile;
    }

    public Integer getRefresh() {
        return refresh;
    }

    public Integer getSigFigs() {
        return sigFigs;
    }

    public StanOutput(String file, String diagnosticFile, Integer refresh, Integer sigFigs) {
        this.file=file;
        this.diagnosticFile=diagnosticFile;
        this.refresh = refresh;
    }

    @Override
    public String genCmd() {
        return String.format("""
                output file=%s diagnostic_file=%s refresh=%s
                """,
                getFile(),
                getDiagnosticFile(),
                getRefresh().toString()
        ).stripTrailing().stripLeading();
    }

}

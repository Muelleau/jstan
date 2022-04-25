package io.vigg.jstan.data;

import io.vigg.jstan.StanCommandComponent;
import io.vigg.jstan.config.Config;

public class StanData extends StanCommandComponent {

    final private String path;

    public StanData(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String genCmd() {
        return String.format("""
                        data file=%s
                        """,
                getPath()
        ).stripLeading().stripTrailing();
    }
}

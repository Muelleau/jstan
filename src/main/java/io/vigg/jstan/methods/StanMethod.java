package io.vigg.jstan.methods;

import io.vigg.jstan.StanCommandComponent;

public abstract class StanMethod extends StanCommandComponent {

    String cliText;

    public String getCliText() {
        return cliText;
    }

}
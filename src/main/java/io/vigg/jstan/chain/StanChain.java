package io.vigg.jstan.chain;

import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;

import java.util.ArrayList;

public class StanChain {

    private Table data;

    public StanChain(String file) {
        data = Table.read().csv(file);
    }

    public Table getData() {
        return this.data;
    }


}

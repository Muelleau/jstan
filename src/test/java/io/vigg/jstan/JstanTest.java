package io.vigg.jstan;

import io.vigg.jstan.data.JsonData;
import io.vigg.jstan.init.StanInit;
import io.vigg.jstan.output.StanOutput;
import io.vigg.jstan.program.StanProgram;
import io.vigg.jstan.program.StanProgramBuilder;
import io.vigg.jstan.random.StanRandom;
import org.junit.Before;
import org.junit.Test;
import io.vigg.jstan.methods.SampleMethod;

import java.io.IOException;

public class JstanTest {

    @Before
    public void before() {

    }

    @Test
    public void testMultiply() throws IOException {

        var method = new SampleMethod();
        var data = new JsonData("examples/bernoulli/bernoulli.data.json");
        var output = new StanOutput();
        var init = new StanInit();
        var random = new StanRandom();

        StanProgram program = new StanProgramBuilder()
                .setMethod(method)
                .setData(data)
                .setOutput(output)
                .setInit(init)
                .setRandom(random)
                .build();

        System.out.println(method.getCliText());

        System.out.println(program.getData().getPath());

        program.compile();
        program.run();

    }

}

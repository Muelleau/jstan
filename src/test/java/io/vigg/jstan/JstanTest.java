package io.vigg.jstan;

import io.vigg.jstan.data.JsonData;
import io.vigg.jstan.init.StanInit;
import io.vigg.jstan.output.StanOutput;
import io.vigg.jstan.output.StanOutputBuilder;
import io.vigg.jstan.program.StanProgram;
import io.vigg.jstan.program.StanProgramBuilder;
import io.vigg.jstan.random.StanRandomBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import io.vigg.jstan.methods.SampleMethod;

import java.io.IOException;

public class JstanTest {

    @Before
    public void before() {

    }

    @Test
    public void testStanData() throws IOException {

        var data = new JsonData("examples/bernoulli/bernoulli.data.json");

        Assert.assertEquals(
                "data file=./bin/cmdstan-2.29.2/examples/bernoulli/bernoulli.data.json",
                data.genCmd()
        );

    }

    @Test
    public void testStanOutput() throws IOException {

        var output = new StanOutput("output.csv", "diag.csv", 101, 25);

        Assert.assertEquals(
                "output file=output.csv diagnostic_file=diag.csv refresh=101",
                output.genCmd()
        );

    }

    @Test
    public void testStanMethod() throws IOException {

    }

    @Test
    public void testCompile() throws IOException {

//        var method = new SampleMethod();
//        var data = new JsonData("examples/bernoulli/bernoulli.data.json");
//        var output = new StanOutputBuilder()
//                .setDiagnosticFile("diag.csv")
//                .setFile("o2.csv")
//                .setRefresh(100)
//                .createStanOutput();
//        var init = new StanInit();
//        var random = new StanRandomBuilder().setSeed(3252652196L).createStanRandom();
//
//        StanProgram program = new StanProgramBuilder()
//                .setMethod(method)
//                .setData(data)
//                .setOutput(output)
//                .setInit(init)
//                .setRandom(random)
//                .build();
//
//        System.out.println(method.getCliText());
//
//        System.out.println(program.getData().getPath());
//
//        program.compile();
//        program.run();

    }

}

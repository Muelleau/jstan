package io.vigg.jstan;

import io.vigg.jstan.data.JsonData;
import io.vigg.jstan.methods.sample.SampleBuilder;
import io.vigg.jstan.methods.sample.adapt.AdaptBuilder;
import io.vigg.jstan.methods.sample.algorithm.Algorithm;
import io.vigg.jstan.methods.sample.algorithm.AlgorithmBuilder;
import io.vigg.jstan.output.StanOutput;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

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
    public void testAlgorithm() throws IOException {

        var algorithm = new AlgorithmBuilder().build();
        System.out.println(algorithm.genCmd());
        Assert.assertEquals("""
                algorithm=hmc hmc engine=nuts \
                max_depth=10 metric=diag_e \
                metric_file=metric_file.csv \
                step_size=1 step_size_jitter=0""",
                algorithm.genCmd()
                );
    }

    @Test
    public void testAdapt() throws IOException {
        var adapt = new AdaptBuilder().build();
        System.out.println(adapt.genCmd());
        Assert.assertEquals("""
                adapt \
                engaged=1 \
                gamma=0.05 \
                delta=0.8 \
                kappa=0.75 t0=10 init_buffer=75 \
                term_buffer=50 window=25""",
                adapt.genCmd()
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
    public void testStanSampleMethod() throws IOException {

        var adapt = new AdaptBuilder().build();
        var algorithm = new AlgorithmBuilder().build();
        var sample = new SampleBuilder()
                .setAdapt(adapt)
                .setAlgorithm(algorithm)
                .build();

        Assert.assertEquals("""
                        method=sample sample num_samples=1000 num_warmup=1000 save_warmup=0 thin=1 adapt engaged=1 gamma=0.05 delta=0.8 kappa=0.75 t0=10 init_buffer=75 term_buffer=50 window=25 algorithm=hmc hmc engine=nuts max_depth=10 metric=diag_e metric_file=metric_file.csv step_size=1 step_size_jitter=0""",
                sample.genCmd()
        );

        System.out.println(sample.genCmd());
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

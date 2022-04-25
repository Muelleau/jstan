package io.vigg.jstan;

import io.vigg.jstan.data.JsonData;
import io.vigg.jstan.data.StanData;
import io.vigg.jstan.data.StanDataBuilder;
import io.vigg.jstan.methods.StanMethod;
import io.vigg.jstan.methods.sample.SampleBuilder;
import io.vigg.jstan.methods.sample.adapt.AdaptBuilder;
import io.vigg.jstan.methods.sample.algorithm.Algorithm;
import io.vigg.jstan.methods.sample.algorithm.AlgorithmBuilder;
import io.vigg.jstan.model.StanModel;
import io.vigg.jstan.model.StanModelBuilder;
import io.vigg.jstan.output.StanOutput;
import io.vigg.jstan.output.StanOutputBuilder;
import io.vigg.jstan.program.StanProgram;
import io.vigg.jstan.program.StanProgramBuilder;
import io.vigg.jstan.random.StanRandom;
import io.vigg.jstan.random.StanRandomBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class JstanTest {

    String id = UUID.randomUUID().toString();

    StanMethod method = new SampleBuilder().build();

    StanModel model = new StanModelBuilder()
            .setModel("""
                data {
                  int<lower=0> N;
                  array[N] int<lower=0,upper=1> y; // or int<lower=0,upper=1> y[N];
                }
                parameters {
                  real<lower=0,upper=1> theta;
                }
                model {
                  theta ~ beta(1,1);  // uniform prior on interval 0,1
                  y ~ bernoulli(theta);
                }
                """)
            .build();

    StanData data = new StanDataBuilder()
            .setPath("examples/bernoulli/bernoulli.data.json")
            .build();

    StanOutput output = new StanOutputBuilder()
            .build();

    StanRandom random = new StanRandomBuilder()
            .setSeed(3252652196L)
            .build();

    StanProgram program = new StanProgramBuilder()
            .setId(id)
            .setModel(model)
            .setMethod(method)
            .setData(data)
            .setOutput(output)
            .setRandom(random)
            .build();

    public JstanTest() throws FileNotFoundException, UnsupportedEncodingException {}

    @Test
    public void testStanData() throws IOException {
        Assert.assertEquals(
                "data file=./bin/cmdstan-2.29.2/examples/bernoulli/bernoulli.data.json",
                data.genCmd()
        );
    }

    @Test
    public void testAlgorithm() throws IOException {
        var algorithm = new AlgorithmBuilder().build();
        Assert.assertEquals("""
                algorithm=hmc engine=nuts max_depth=10 metric=diag_e""",
                algorithm.genCmd()
                );
    }

    @Test
    public void testAdapt() throws IOException {
        var adapt = new AdaptBuilder().build();
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
        Assert.assertEquals(
                "",
                output.genCmd()
        );
    }

    @Test
    public void testSeed() throws IOException {
        Assert.assertEquals("random seed=2", random.genCmd());
    }

    @Test
    public void testStanSampleMethod() throws IOException {
        Assert.assertEquals("""
sample num_samples=1000 num_warmup=1000 save_warmup=0 thin=1 adapt engaged=1 gamma=0.05 delta=0.8 kappa=0.75 t0=10 init_buffer=75 term_buffer=50 window=25 algorithm=hmc engine=nuts max_depth=10 metric=diag_e""",
                method.genCmd()
        );
    }

    @Test
    public void testStanModel() throws IOException {
        var model = new StanModelBuilder()
                .setModel("""
                data {
                  int<lower=0> N;
                  array[N] int<lower=0,upper=1> y; // or int<lower=0,upper=1> y[N];
                }
                parameters {
                  real<lower=0,upper=1> theta;
                }
                model {
                  theta ~ beta(1,1);  // uniform prior on interval 0,1
                  y ~ bernoulli(theta);
                }
                        """)
                .build();
    }

    @Test
    public void testCompileRun() throws IOException {
        program.compile();
        program.run();
    }

}

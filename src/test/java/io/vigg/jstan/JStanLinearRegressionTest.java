package io.vigg.jstan;

import io.vigg.jstan.data.StanData;
import io.vigg.jstan.data.StanDataBuilder;
import io.vigg.jstan.methods.StanMethod;
import io.vigg.jstan.methods.sample.SampleBuilder;
import io.vigg.jstan.model.StanModel;
import io.vigg.jstan.model.StanModelBuilder;
import io.vigg.jstan.output.StanOutput;
import io.vigg.jstan.output.StanOutputBuilder;
import io.vigg.jstan.program.StanProgram;
import io.vigg.jstan.program.StanProgramBuilder;
import io.vigg.jstan.random.StanRandom;
import io.vigg.jstan.random.StanRandomBuilder;
import org.junit.Test;

import java.io.IOException;


public class JStanLinearRegressionTest {

    String id = "linear_regression_test";

    StanMethod method = new SampleBuilder()
            .setNumWarmup(1000)
            .setNumSamples(1000)
            .build();

    StanModel model = new StanModelBuilder()
            .setModel("""
                        data {
                           int<lower=0> N;
                           int<lower=0> K;
                           matrix[N, K] x;
                           vector[N] y;
                         }
                         parameters {
                           real alpha;
                           vector[K] beta;
                           real<lower=0> sigma;
                         }
                         model {
                           y ~ normal(alpha + x * beta, sigma);
                         }
                         
                    """)
            .build();

    StanData data = new StanDataBuilder()
            .setPath("/Users/andrewmueller/vigg/projects/jstan/data/linear_regression_test.json")
            .build();

    StanOutput output = new StanOutputBuilder()
            .build();

    StanRandom random = new StanRandomBuilder()
            .setSeed(2L)
            .build();

    StanProgram program = new StanProgramBuilder()
            .setId(id)
            .setModel(model)
            .setMethod(method)
            .setData(data)
            .setOutput(output)
            .setRandom(random)
            .build();

    public JStanLinearRegressionTest() throws IOException {}

    @Test
    public void test() throws IOException {
        program.compile();
        program.run();
    }


}

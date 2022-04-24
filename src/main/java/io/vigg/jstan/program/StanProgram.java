package io.vigg.jstan.program;

import io.vigg.jstan.data.StanData;
import io.vigg.jstan.init.StanInit;
import io.vigg.jstan.methods.StanMethod;
import io.vigg.jstan.output.StanOutput;
import io.vigg.jstan.random.StanRandom;
import io.vigg.jstan.config.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StanProgram {

    private StanMethod method;
    private StanData data;
    private StanOutput output;
    private StanInit init;
    private StanRandom random;

    public StanProgram(
            StanMethod method,
            StanData data,
            StanOutput output,
            StanInit init,
            StanRandom random
    ) {
        this.method = method;
        this.data = data;
        this.output = output;
        this.init = init;
        this.random = random;
    }

    public StanMethod getMethod() {
        return this.method;
    }

    public StanData getData() {
        return this.data;
    }

    public StanOutput getOutput() {
        return this.output;
    }

    public StanInit getInit() {
        return this.init;
    }

    public StanRandom getRandom() {
        return this.random;
    }

    public void compile() throws IOException {
        var exec = String.format("""
                    make -C %s %s
                """, Config.CMDSTAN_DIR, "examples/bernoulli/bernoulli");
        cmdExecStdout(exec);
    }

    public void run() throws IOException {
        var exec = String.format("""
                ./bin/cmdstan-2.29.2/%s %s \
                data file=./bin/cmdstan-2.29.2/%s \
                random seed=%s \
                output file=%s \
                diagnostic_file=%s \
                refresh=%s
                """,
                "examples/bernoulli/bernoulli",
                method.getCliText(),
                data.getPath(),
                random.getSeed().toString(),
                output.getFile(),
                output.getDiagnosticFile(),
                output.getRefresh().toString()
        );
        cmdExecStdout(exec);
    }

    private static void cmdExecStdout(String exec) throws IOException {
        var process = Runtime.getRuntime().exec(exec);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }
}

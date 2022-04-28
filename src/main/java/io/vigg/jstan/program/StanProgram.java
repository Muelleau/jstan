package io.vigg.jstan.program;

import io.vigg.jstan.chain.StanChain;
import io.vigg.jstan.data.StanData;
import io.vigg.jstan.init.StanInit;
import io.vigg.jstan.methods.StanMethod;
import io.vigg.jstan.model.StanModel;
import io.vigg.jstan.output.StanOutput;
import io.vigg.jstan.random.StanRandom;
import io.vigg.jstan.config.Config;
import tech.tablesaw.columns.Column;

import static tech.tablesaw.aggregate.AggregateFunctions.*;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

public class StanProgram {

    private String id;
    private StanModel model;
    private StanMethod method;
    private StanData data;
    private StanOutput output;
    private StanInit init;
    private StanRandom random;
    private StanChain chain;

    public StanProgram(
            String id,
            StanModel model,
            StanMethod method,
            StanData data,
            StanOutput output,
            StanInit init,
            StanRandom random
    ) throws IOException {

        this.id = id;
        this.model = model;
        this.method = method;
        this.data = data;
        this.output = output;
        this.init = init;
        this.random = random;

        writeModel();

    }

    private void writeModel() throws IOException {

        Files.createDirectories(Paths.get(Config.CMDSTAN_DIR + "models/"));

        System.out.println(Config.CMDSTAN_DIR + "models/"+ getId() + ".stan");
        PrintWriter writer = new PrintWriter(Config.CMDSTAN_DIR + "models/"+ getId() + ".stan", "UTF-8");
        writer.println(model.getModel());
        writer.close();

    }

    public String getId() { return this.id; }

    public StanModel getModel() { return this.model; }

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

    public StanChain getChain() {
        return this.chain;
    }

    public void compile() throws IOException {
        var exec = String.format("""
                    make -C %s %s
                """, Config.CMDSTAN_DIR, "models/" + getId());
        cmdExecStdout(exec);
    }

    public void run() throws IOException {

        //generate the model execution command
        var exec = String.format("""
                %s %s %s %s output file=%s diagnostic_file=%s
                """,
                Config.CMDSTAN_DIR + "models/" + getId(),
                getMethod().genCmd(),
                getData().genCmd(),
                getOutput().genCmd(),
                getFileOutputFile(),
                getDiagnosticFile()
        );

        // execute the model
        cmdExecStdout(exec);

        // read the output chain into memory
        this.chain = new StanChain(String.format("output/%s.output.csv", getId()));

        var varList = this.chain.getData().columns().stream().map(Column::name).filter(name -> !(Arrays.asList(
                    "lp__",
                    "accept_stat__",
                    "stepsize__",
                    "treedepth__",
                    "n_leapfrog__",
                    "divergent__",
                    "energy__"
            ).contains(name))).collect(Collectors.toList());

        System.out.println(
            this.chain.getData().summarize(varList, mean).apply()
        );
    }

    private String getFileOutputFile() {
        return "output/" + getId() + ".output.csv";
    }
    private String getDiagnosticFile() {
        return "output/" + getId() + ".diag.csv";
    }

    private static void cmdExecStdout(String exec) throws IOException {
        var process = Runtime.getRuntime().exec(exec);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line + "...");
        }
        reader.close();
    }


}

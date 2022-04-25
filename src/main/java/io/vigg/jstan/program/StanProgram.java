package io.vigg.jstan.program;

import io.vigg.jstan.data.StanData;
import io.vigg.jstan.init.StanInit;
import io.vigg.jstan.methods.StanMethod;
import io.vigg.jstan.model.StanModel;
import io.vigg.jstan.output.StanOutput;
import io.vigg.jstan.random.StanRandom;
import io.vigg.jstan.config.Config;

import java.io.*;

public class StanProgram {

    private String id;
    private StanModel model;
    private StanMethod method;
    private StanData data;
    private StanOutput output;
    private StanInit init;
    private StanRandom random;

    public StanProgram(
            String id,
            StanModel model,
            StanMethod method,
            StanData data,
            StanOutput output,
            StanInit init,
            StanRandom random
    ) throws FileNotFoundException, UnsupportedEncodingException {

        this.id = id;
        this.model = model;
        this.method = method;
        this.data = data;
        this.output = output;
        this.init = init;
        this.random = random;

        writeModel();

    }

    private void writeModel() throws FileNotFoundException, UnsupportedEncodingException {

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

    public void compile() throws IOException {
        var exec = String.format("""
                    make -C %s %s
                """, Config.CMDSTAN_DIR, "models/" + getId());

        System.out.println(exec);
        cmdExecStdout(exec);
    }

    public void run() throws IOException {

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

        System.out.println("Execution Command");
        System.out.println(exec);

        cmdExecStdout(exec);
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
            System.out.println(line);
        }
        reader.close();
    }
}

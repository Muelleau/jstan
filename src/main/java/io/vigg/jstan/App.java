package io.vigg.jstan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

class App {

    static final String CMDSTAN_VERSION = System.getenv("CMDSTAN_VERSION");
    static final String CMDSTAN_DIR = System.getenv("CMDSTAN_DIR");

    static void buildModel(String cmd) throws IOException {
        var exec = String.format("""
                    make -C %s %s
                """, CMDSTAN_DIR, cmd);
        var process = Runtime.getRuntime().exec(
                exec
        );

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        reader.close();
    }

    public static void main(String[] args) throws IOException {

        System.out.println("Hello, World!");

        buildModel("examples/bernoulli/bernoulli");


    }

}

package resources;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public abstract class Day {


    public Pair<String, String[]> parse(String path, String split) {
        String file = null;
        try {
            file = Files.readString(Paths.get(path.substring(1)), StandardCharsets.US_ASCII);
        } catch (IOException ignored) {
            throw new Error("Could not parse file.");
        }
        String[] content = file.split(split);
        return new Pair<>(file, content);
    }

    protected abstract Object one();

    protected abstract Object two();

    public void print(String part, Object result, long time) {
        System.out.printf("\n------------ Part %s ------------\n", part);
        System.out.printf("    --> solution: %s\n", result);
        System.out.printf("    --> execution time: %d ms\n", (time / 1000000));
        System.out.println("----------------------------------");
    }

}

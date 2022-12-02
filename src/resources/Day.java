package resources;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class Day {

    public String content;
    public String[] lines;

    public void run(String path, String split) {
        parse(path, split);
        solve();
    }

    public void parse(String path, String split) {
        try {
            this.content = Files.readString(Paths.get(path.substring(1)), StandardCharsets.US_ASCII);
        } catch (IOException ignored) {
            throw new Error("Could not parse file.");
        }
        this.lines = this.content.split(split);
    }

    protected abstract Object one();

    protected abstract Object two();

    void solve() {
        long start = System.nanoTime();
        Object result = one();
        long end = System.nanoTime();
        print("One", result, (end - start));

        start = System.nanoTime();
        result = two();
        end = System.nanoTime();
        print("Two", result, (end - start));
    }

    public void print(String part, Object result, long time) {
        System.out.printf("\n------------ Part %s ------------\n", part);
        System.out.printf("    --> solution: %s\n", result);
        System.out.printf("    --> execution time: %d ms\n", (time / 1000000));
        System.out.println("----------------------------------");
    }

}

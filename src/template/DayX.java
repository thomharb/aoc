package template;

import resources.Day;
import resources.Pair;

import java.io.IOException;
import java.util.Objects;

public class DayX extends Day {
    private final boolean example = true;
    private String content;
    private String[] lines;

    public static void main(String[] args) throws IOException {
        new DayX().run();
    }

    public void run() {
        String path = this.example ?
                Objects.requireNonNull(getClass().getResource("example.txt")).getPath() :
                Objects.requireNonNull(getClass().getResource("input.txt")).getPath();
        Pair<String, String[]> parsed = super.parse(path, "\r\n");
        this.content = parsed.first();
        this.lines = parsed.second();
        solve();
    }

    void solve() {
        long start = System.nanoTime();
        Object result = one();
        long end = System.nanoTime();
        super.print("One", result, (end - start));

        start = System.nanoTime();
        result = two();
        end = System.nanoTime();
        super.print("Two", result, (end - start));
    }

    @Override
    public Object one() {
        /* -- CODE -- */
        return null;
    }

    @Override
    public Object two() {
        /* -- CODE -- */
        return null;
    }


}

package aoc2022.day1;

import resources.Day;
import resources.Pair;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Solver extends Day {
    private final boolean example = false;
    private List<Long> lines;

    public static void main(String[] args) throws IOException {
        new Solver().run();
    }

    public void run() {
        String path = this.example ?
                Objects.requireNonNull(getClass().getResource("example.txt")).getPath() :
                Objects.requireNonNull(getClass().getResource("input.txt")).getPath();
        Pair<String, String[]> parsed = super.parse(path, "\r\n\r\n");

        this.lines = Arrays.stream(parsed.second())
                .map(line -> line.split("\r\n"))
                .map(lineSplit -> (Arrays.stream(lineSplit)
                        .map(Long::parseLong))
                        .reduce((long) 0, Long::sum))
                .collect(Collectors.toList());
        this.lines.sort(Collections.reverseOrder());
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
        return this.lines.get(0);
    }

    @Override
    public Object two() {
        return this.lines.stream().mapToLong(Long::intValue).limit(3).sum();
    }


}

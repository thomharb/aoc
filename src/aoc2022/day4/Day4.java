package aoc2022.day4;

import resources.Day;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class Day4 extends Day {
    private static final boolean example = false;

    public static void main(String[] args) throws IOException {
        String path = example ?
                Objects.requireNonNull(Day4.class.getResource("example.txt")).getPath() :
                Objects.requireNonNull(Day4.class.getResource("input.txt")).getPath();
        new Day4().run(path, "\r\n");
    }

    @Override
    public Object one() {
        return Arrays.stream(lines)
                .map(i -> Arrays.stream(i.split("[,-]"))
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .filter(a -> (a[0] >= a[2] && a[1] <= a[3]) || (a[2] >= a[0] && a[3] <= a[1]))
                .count();
    }

    @Override
    public Object two() {
        return Arrays.stream(lines)
                .map(i -> Arrays.stream(i.split("[,-]"))
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .filter(a -> a[0] <= a[2] ? a[1] >= a[2] : a[0] <= a[3])
                .count();
    }

}

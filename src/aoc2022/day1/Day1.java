package aoc2022.day1;

import resources.Day;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day1 extends Day {
    private static final boolean example = true;

    private List<Long> calories;

    public static void main(String[] args) throws IOException {
        String path = example ?
                Objects.requireNonNull(Day1.class.getResource("example.txt")).getPath() :
                Objects.requireNonNull(Day1.class.getResource("input.txt")).getPath();
        new Day1().run(path, "\r\n\r\n");
    }

    @Override
    public Object one() {
        this.calories = Arrays.stream(lines)
                .map(line -> line.split("\r\n"))
                .map(lineSplit -> (Arrays.stream(lineSplit)
                        .map(Long::parseLong))
                        .reduce((long) 0, Long::sum))
                .collect(Collectors.toList());
        this.calories.sort(Collections.reverseOrder());
        return this.calories.get(0);
    }

    @Override
    public Object two() {
        return this.calories.stream().mapToLong(Long::intValue).limit(3).sum();
    }

}

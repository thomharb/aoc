package aoc2022.day6;

import resources.Day;
import resources.Pair;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day6 extends Day {
    private static final boolean example = false;

    public static void main(String[] args) throws IOException {
        String path = example ?
                Objects.requireNonNull(Day6.class.getResource("example.txt")).getPath() :
                Objects.requireNonNull(Day6.class.getResource("input.txt")).getPath();
        new Day6().run(path, "");
    }

    public Integer oneLiner(int distinct) {
        return IntStream
                .range(0, content.length() - distinct)
                .mapToObj(i -> new Pair<>(i, content.substring(i, i + distinct)))
                .map(p -> new Pair<>(p.first(), new HashSet<>(p.second().chars().mapToObj(c -> (char) c).collect(Collectors.toSet()))))
                .filter(p -> p.second().size() == distinct)
                .toList()
                .get(0).first() + distinct;
    }

    @Override
    public Object one() {
        return oneLiner(4);
    }

    @Override
    public Object two() {
        return oneLiner(14);
    }
}

package aoc2022.day3;

import resources.Day;

import java.io.IOException;
import java.util.*;

public class Day3 extends Day {
    private static final boolean example = false;

    public static void main(String[] args) throws IOException {
        String path = example ?
                Objects.requireNonNull(Day3.class.getResource("example.txt")).getPath() :
                Objects.requireNonNull(Day3.class.getResource("input.txt")).getPath();
        new Day3().run(path, "\r\n");
    }

    @Override
    public Object one() {
        List<String> priority = new ArrayList<>();
        for (String line : lines) {
            Set<String> s1 = new HashSet<>(Arrays.asList(line.substring(0, line.length() / 2).split("")));
            s1.retainAll(new HashSet<>(Arrays.asList(line.substring(line.length() / 2).split(""))));
            priority.addAll(s1);
        }
        return priority.stream()
                .map(item -> item.charAt(0))
                .map(item -> Character.isLowerCase(item) ? (int) item - 96 : (int) item - 38)
                .mapToInt(i -> i).sum();
    }

    @Override
    public Object two() {
        List<String> priority = new ArrayList<>();
        for (int i = 0; i < lines.length; i += 3) {
            Set<String> s1 = new HashSet<>(Arrays.asList(lines[i].split("")));
            s1.retainAll(new HashSet<>(Arrays.asList(lines[i + 1].split(""))));
            s1.retainAll(new HashSet<>(Arrays.asList(lines[i + 2].split(""))));
            priority.addAll(s1);
        }
        return priority.stream()
                .map(item -> item.charAt(0))
                .map(item -> Character.isLowerCase(item) ? (int) item - 96 : (int) item - 38)
                .mapToInt(i -> i).sum();
    }

}

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
        int sum = 0;
        for (String line : lines) {
            Set<String> s1 = new HashSet<>(Arrays.asList(line.substring(0, line.length() / 2).split("")));
            s1.retainAll(new HashSet<>(Arrays.asList(line.substring(line.length() / 2).split(""))));
            char dupe = s1.iterator().next().charAt(0);
            sum += Character.isLowerCase(dupe) ? (int) dupe - 96 : (int) dupe - 38;
        }
        return sum;
    }

    @Override
    public Object two() {
        int sum = 0;
        for (int i = 0; i < lines.length; i += 3) {
            Set<String> s1 = new HashSet<>(Arrays.asList(lines[i].split("")));
            s1.retainAll(new HashSet<>(Arrays.asList(lines[i + 1].split(""))));
            s1.retainAll(new HashSet<>(Arrays.asList(lines[i + 2].split(""))));
            char dupe = s1.iterator().next().charAt(0);
            sum += Character.isLowerCase(dupe) ? (int) dupe - 96 : (int) dupe - 38;
        }
        return sum;
    }

}

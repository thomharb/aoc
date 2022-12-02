package aoc2022.day2;

import resources.Day;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class Day2 extends Day {
    private static final boolean example = false;

    private final Map<String, Integer> map = Map.of("X", 1, "Y", 2, "Z", 3);

    public static void main(String[] args) throws IOException {
        String path = example ?
                Objects.requireNonNull(Day2.class.getResource("example.txt")).getPath() :
                Objects.requireNonNull(Day2.class.getResource("input.txt")).getPath();
        new Day2().run(path, "\r\n");
    }

    @Override
    public Object one() {
        int score = 0;
        for (String round : lines) {
            String[] plays = round.split(" ");
            score += map.get(plays[1]);
            score += play(plays[0], plays[1]);
        }
        return score;
    }

    @Override
    public Object two() {
        int score = 0;
        for (String round : lines) {
            String[] plays = round.split(" ");
            String move = calculatePlay(plays[0], plays[1]);
            score += map.get(move);
            score += play(plays[0], move);
        }
        return score;
    }

    public int play(String a, String b) {
        switch (a) {
            case "A":
                if (b.equals("X")) return 3;
                if (b.equals("Y")) return 6;
                if (b.equals("Z")) return 0;
            case "B":
                if (b.equals("X")) return 0;
                if (b.equals("Y")) return 3;
                if (b.equals("Z")) return 6;
            case "C":
                if (b.equals("X")) return 6;
                if (b.equals("Y")) return 0;
                if (b.equals("Z")) return 3;
        }
        return -1;
    }

    public String calculatePlay(String opponent, String ending) {
        switch (opponent) {
            case "A":
                if (ending.equals("X")) return "Z";
                if (ending.equals("Y")) return "X";
                if (ending.equals("Z")) return "Y";
            case "B":
                if (ending.equals("X")) return "X";
                if (ending.equals("Y")) return "Y";
                if (ending.equals("Z")) return "Z";
            case "C":
                if (ending.equals("X")) return "Y";
                if (ending.equals("Y")) return "Z";
                if (ending.equals("Z")) return "X";
        }
        return "";
    }

}

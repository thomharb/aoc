package aoc2022.day5;

import resources.Day;

import java.io.IOException;
import java.util.*;

public class Day5 extends Day {
    private static final boolean example = false;

    public static void main(String[] args) throws IOException {
        String path = example ?
                Objects.requireNonNull(Day5.class.getResource("example.txt")).getPath() :
                Objects.requireNonNull(Day5.class.getResource("input.txt")).getPath();
        new Day5().run(path, "\r\n\r\n");
    }

    @Override
    public Object one() {
        List<Stack<Character>> stacks = getStacks();
        for (String line : lines[1].split("\r\n")) {
            String[] split = line.split(" ");
            int amt = Integer.parseInt(split[1]);
            int from = Integer.parseInt(split[3]);
            int to = Integer.parseInt(split[5]);
            for (int i = 0; i < amt; i++) stacks.get(to - 1).add(stacks.get(from - 1).pop());
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 9; i++) result.append(stacks.get(i).peek());
        return result.toString();
    }

    @Override
    public Object two() {
        List<Stack<Character>> stacks = getStacks();
        for (String line : lines[1].split("\r\n")) {
            String[] split = line.split(" ");
            int amt = Integer.parseInt(split[1]);
            int from = Integer.parseInt(split[3]);
            int to = Integer.parseInt(split[5]);
            Stack<Character> order = new Stack<>();
            for (int i = 0; i < amt; i++) order.add(stacks.get(from - 1).pop());
            for (int i = 0; i < amt; i++) stacks.get(to - 1).add(order.pop());
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 9; i++) result.append(stacks.get(i).peek());
        return result.toString();
    }

    public List<Stack<Character>> getStacks() {
        List<Stack<Character>> stacks = new ArrayList<>();
        for (int i = 0; i < 9; i++) stacks.add(new Stack<>());

        stacks.get(0).addAll(Arrays.asList('H', 'T', 'Z', 'D'));
        stacks.get(1).addAll(Arrays.asList('Q', 'R', 'W', 'T', 'G', 'C', 'S'));
        stacks.get(2).addAll(Arrays.asList('P', 'B', 'F', 'Q', 'N', 'R', 'C', 'H'));
        stacks.get(3).addAll(Arrays.asList('L', 'C', 'N', 'F', 'H', 'Z'));
        stacks.get(4).addAll(Arrays.asList('G', 'L', 'F', 'Q', 'S'));
        stacks.get(5).addAll(Arrays.asList('V', 'P', 'W', 'Z', 'B', 'R', 'C', 'S'));
        stacks.get(6).addAll(Arrays.asList('Z', 'F', 'J'));
        stacks.get(7).addAll(Arrays.asList('D', 'L', 'V', 'Z', 'R', 'H', 'Q'));
        stacks.get(8).addAll(Arrays.asList('B', 'H', 'G', 'N', 'F', 'Z', 'L', 'D'));
        return stacks;
    }

}

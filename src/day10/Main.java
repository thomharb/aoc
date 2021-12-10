package day10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {

    static HashMap<Character, Integer> closeScores = new HashMap<>() {{
        put(')', 3);
        put(']', 57);
        put('}', 1197);
        put('>', 25137);
    }};

    static HashMap<Character, Integer> completeScores = new HashMap<>() {{
        put(')', 1);
        put(']', 2);
        put('}', 3);
        put('>', 4);
    }};

    static ArrayList<String> input = new ArrayList<>();

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        try {
            File file = new File("src/day10/input.txt");
//             File file = new File("src/day10/example.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                input.add(line);
            }

            solve1();
            solve2();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();

        long duration = (endTime - startTime);
        System.out.printf("Execution time: %d ms", (duration / 1000000));
    }

    public static void solve1() {
        int score = 0;
        for (String line : input) {
            ArrayList<Character> stack = new ArrayList<>();
            char[] characters = line.toCharArray();
            for (char character : characters) {
                if (isClosing(character)) {
                    int lastIndex = stack.size() - 1;
                    if (isOpposite(stack.get(lastIndex), character)) {
                        stack.remove(lastIndex);
                    } else {
                        score += closeScores.get(character);
                        break;
                    }
                } else {
                    stack.add(character);
                }
            }
        }
        System.out.printf("Part 1: Syntax Error Score = %d\n", score);
    }

    public static void solve2() {
        ArrayList<Long> scores = new ArrayList<>();
        ArrayList<String> incomplete = getIncompleteLines();
        for (String line : incomplete) {
            ArrayList<Character> stack = new ArrayList<>();
            char[] characters = line.toCharArray();
            for (char character : characters) {
                if (isClosing(character)) {
                    int lastIndex = stack.size() - 1;
                    if (isOpposite(stack.get(lastIndex), character)) {
                        stack.remove(lastIndex);
                    }
                } else {
                    stack.add(character);
                }
            }
            long score = 0;
            for (int i = stack.size() - 1; i >= 0 ; i--) {
                char opp = getOpposite(stack.get(i));
                score *= 5;
                score += completeScores.get(opp);
            }
            scores.add(score);
        }
        Collections.sort(scores);
        int middleIndex = (int) Math.floor(scores.size() / 2);
        System.out.printf("Part 2: Middle Score = %d\n", scores.get(middleIndex));
    }

    public static ArrayList<String> getIncompleteLines() {
        ArrayList<String> incomplete = new ArrayList<>();
        for (String line : input) {
            ArrayList<Character> stack = new ArrayList<>();
            char[] characters = line.toCharArray();
            for (int i = 0; i < characters.length; i++) {
                char character = characters[i];
                if (i == characters.length - 1 && stack.size() > 1) {
                    incomplete.add(line);
                }
                if (isClosing(character)) {
                    int lastIndex = stack.size() - 1;
                    if (isOpposite(stack.get(lastIndex), character)) {
                        stack.remove(lastIndex);
                    } else {
                        break;
                    }
                } else {
                    stack.add(character);
                }
            }
        }
        return incomplete;
    }

    public static boolean isClosing(char a) {
        return a == ')' || a == ']' || a == '}' || a == '>';
    }

    public static boolean isOpposite(char open, char close) {
        if (open == '(' && close == ')') {
            return true;
        } else if (open == '[' && close == ']') {
            return true;
        } else if (open == '{' && close == '}') {
            return true;
        } else return open == '<' && close == '>';
    }

    public static char getOpposite(char a) {
        if (a == '(') {
            return ')';
        } else if (a == '[') {
            return ']';
        } else if (a == '{') {
            return '}';
        } else if (a == '<') {
            return '>';
        }
        return 0;
    }
}

package day10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    static HashMap<Character, Integer> scores = new HashMap<>() {{
        put(')', 3);
        put(']', 57);
        put('}', 1197);
        put('>', 25137);
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
                        score += scores.get(character);
                        break;
                    }
                } else {
                    stack.add(character);
                }
            }
        }
        System.out.printf("Part 1: Syntax Error Score = %d\n", score);
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
}

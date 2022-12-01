package aoc2021.day14;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.*;

public class Main {

    static int steps = 40;
    static String word;
    static HashMap<String, String> rules = new HashMap<>();

    static boolean test = false;

    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();
        String path = test ? "src/aoc2021.day14/example.txt" : "src/aoc2021.day14/input.txt";
        parse(Files.readString(Paths.get(path), StandardCharsets.US_ASCII));
        solve();
        long endTime = System.nanoTime();

        long duration = (endTime - startTime);
        System.out.printf("Execution time: %d ms", (duration / 1000000));
    }

    static void parse(String file) {
        String[] content = file.split("\r\n");
        word = content[0];

        for (int i = 2; i < content.length; i++) {
            String[] split = content[i].split(" -> ");
            rules.put(split[0], split[1]);
        }
    }

    static void solve() {
        for (int s = 0; s < steps; s++) {
            System.out.println(s);
            for (int i = 0; i < word.length() - 1; i++) {
                word = insert(word, (i + 1), rules.get("" + word.charAt(i) + word.charAt(i + 1)));
                i++;
            }
        }
        HashMap<Character, Integer> count = characterCount();
        int max = count.get(Collections.max(count.entrySet(), Map.Entry.comparingByValue()).getKey());
        int min = count.get(Collections.min(count.entrySet(), Map.Entry.comparingByValue()).getKey());
        System.out.printf("Part 1: %d - %d = %d\n", max, min, max - min);
    }

    static String insert(String str, int position, String character) {
        return str.substring(0, position) + character + str.substring(position);
    }

    static HashMap<Character, Integer> characterCount() {
        HashMap<Character, Integer> charCountMap = new HashMap<>();
        char[] strArray = word.toCharArray();
        for (char c : strArray) {
            int curr = charCountMap.getOrDefault(c, 0);
            charCountMap.put(c, curr + 1);
        }
        return charCountMap;
    }

}

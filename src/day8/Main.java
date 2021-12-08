package day8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    static ArrayList<Input> input = new ArrayList<>();

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        try {
            File file = new File("src/day8/input.txt");
//             File file = new File("src/day8/example.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                input.add(new Input(line));
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
        int counter = 0;
        HashMap<Integer, Integer> segments = getUniqueSegmentsToNumber();
        for (Input input : input) {
            for (String digit : input.output) {
                int length = digit.length();
                if (segments.getOrDefault(length, 0) != 0) {
                    counter++;
                }
            }
        }
        System.out.printf("Part 1: %d\n", counter);
    }

    public static HashMap<Integer, Integer> getUniqueSegmentsToNumber() {
        HashMap<Integer, Integer> map = new HashMap<>();
        // segments -> number
        map.put(2, 1);
        map.put(4, 4);
        map.put(3, 7);
        map.put(7, 8);
        return map;
    }

    public static void solve2() {
        int sum = 0;
        HashMap<Integer, String> mapping = new HashMap<>();
        HashMap<Integer, String> uniqueMapping = new HashMap<>();
        for (Input input : input) {
            for (String digit : input.input) {
                String sorted = sort(digit);
                int length = digit.length();
                switch (length) {
                    case 2 -> {
                        uniqueMapping.put(1, sorted);
                        mapping.put(1, sorted);
                    }
                    case 3 -> {
                        uniqueMapping.put(7, sorted);
                        mapping.put(7, sorted);
                    }
                    case 4 -> {
                        uniqueMapping.put(4, sorted);
                        mapping.put(4, sorted);
                    }
                    case 7 -> {
                        uniqueMapping.put(8, sorted);
                        mapping.put(8, sorted);
                    }
                }
            }
            for (String digit : input.input) {
                String sorted = sort(digit);
                int length = digit.length();
                switch (length) {
                    // 5 chars: 5 2 3
                    case 5:
                        if (overlap(sorted, uniqueMapping, 1) == 2) {
                            mapping.put(3, sorted);
                            break;
                        } else if (overlap(sorted, uniqueMapping, 4) == 3) {
                            mapping.put(5, sorted);
                            break;
                        } else {
                            mapping.put(2, sorted);
                            break;
                        }

                        // 6 chars: 6 9 0
                    case 6:
                        if (overlap(sorted, uniqueMapping, 1) == 1) {
                            mapping.put(6, sorted);
                            break;
                        } else if (overlap(sorted, uniqueMapping, 4) == 3) {
                            mapping.put(0, sorted);
                            break;
                        } else {
                            mapping.put(9, sorted);
                            break;
                        }
                }
            }
            StringBuilder value = new StringBuilder();
            for (String digit : input.output) {
                String sorted = sort(digit);
                for (Integer key : mapping.keySet()) {
                    if (mapping.get(key).equals(sorted)) {
                        value.append(key);
                        break;
                    }
                }
            }
            sum += Integer.parseInt(value.toString());
        }
        System.out.printf("Part 2: %d\n", sum);
    }

    public static int overlap(String digit, HashMap<Integer, String> mapping, int key) {
        String digitChars = mapping.get(key);
        int counter = 0;
        for (int i = 0; i < digit.length(); i++) {
            for (int j = 0; j < digitChars.length(); j++) {
                if (digit.charAt(i) == digitChars.charAt(j)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public static String sort(String digit) {
        char[] chars = digit.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}

package aoc2021.day3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static ArrayList<String> input = new ArrayList<>();

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        try {
            File file = new File("src/aoc2021.day3/input.txt");
//             File file = new File("src/aoc2021.day3/example.txt");
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
        int binaryWordLength = input.get(0).length();

        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();

        for (int i = 0; i < binaryWordLength; i++) {
            char[] common = mostCommonBit(input, i);
            gamma.append(common[0]);
            epsilon.append(common[1]);
        }

        int gammaDecimal = Integer.parseInt(gamma.toString(), 2);
        int epsilonDecimal = Integer.parseInt(epsilon.toString(), 2);

        System.out.println("gamma * epsilon = " + gammaDecimal * epsilonDecimal);
    }

    public static char[] mostCommonBit(ArrayList<String> input, int position) {
        int counter = 0;
        for (String word : input) {
            if (word.charAt(position) == '1') {
                counter++;
            }
        }
        if (counter > (input.size() - 1) / 2) {
            return new char[]{'1', '0'};
        } else {
            return new char[]{'0', '1'};
        }
    }

    public static void solve2() {
        int oxygen = Integer.parseInt(computeOxygen(), 2);
        int co2 = Integer.parseInt(computeCO2(), 2);
        System.out.println("oxygen * co2 = " + oxygen * co2);
    }

    public static String computeOxygen() {
        ArrayList<String> subarray = input;
        int binaryWordLength = subarray.get(0).length();

        for (int i = 0; i < binaryWordLength; i++) {
            char mostCommon = mostCommonBit(subarray, i)[0];
            subarray = computeSubarray(subarray, i, mostCommon);

            if (subarray.size() == 1) {
                return subarray.get(0);
            }
        }
        return "";
    }

    public static String computeCO2() {
        ArrayList<String> subarray = input;
        int binaryWordLength = subarray.get(0).length();

        for (int i = 0; i < binaryWordLength; i++) {
            char leastCommon = mostCommonBit(subarray, i)[1];
            subarray = computeSubarray(subarray, i, leastCommon);

            if (subarray.size() == 1) {
                return subarray.get(0);
            }
        }
        return "";
    }

    public static ArrayList<String> computeSubarray(ArrayList<String> subarray, int position, char common) {
        ArrayList<String> wordsLeft = new ArrayList<>();
        for (String word : subarray) {
            if (word.charAt(position) == common) {
                wordsLeft.add(word);
            }
        }
        return wordsLeft;
    }
}

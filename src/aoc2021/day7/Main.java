package aoc2021.day7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

public class Main {

    static ArrayList<Integer> input = new ArrayList<>();

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        try {
            File file = new File("src/aoc2021.day7/input.txt");
//             File file = new File("src/aoc2021.day7/example.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                for (String number : split) {
                    input.add(Integer.parseInt(number));
                }
            }
            solve();
            solve2();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();

        long duration = (endTime - startTime);
        System.out.printf("Execution time: %d ms", (duration / 1000000));
    }

    public static void solve() {
        int goal = (int) getMedian();
        int fuel = 0;
        for (Integer crab : input) {
            fuel += Math.abs(crab - goal);
        }

        System.out.printf("Amount of fuel needed for position %d = %d\n", goal, fuel);
    }

    public static double getMedian() {
        Collections.sort(input);
        double median;
        if (input.size() % 2 == 0)
            median = ((double)input.get(input.size()/2) + (double)input.get(input.size()/2 - 1))/2;
        else
            median = (double) input.get(input.size()/2);
        return median;
    }

    public static void solve2() {
        int minOffset = -1;
        for (int i = 0; i < Collections.max(input); i++) {
            int[] arr = new int[input.size()];
            for (int j = 0; j < input.size(); j++) {
                int displacement = Math.abs(input.get(j) - i);
                arr[j] = displacement + IntStream.range(0, displacement).sum();
            }
            int fuel = Arrays.stream(arr).sum();
            if (fuel < minOffset || minOffset < 0) {
                minOffset = fuel;
            }
        }
        System.out.println(minOffset);
    }
}

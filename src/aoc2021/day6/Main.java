package aoc2021.day6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static ArrayList<Integer> input = new ArrayList<>();
    static int daysPartOne = 80;
    static int daysPartTwo = 256;

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        try {
            File file = new File("src/aoc2021.day6/input.txt");
//            File file = new File("src/aoc2021.day6/example.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                for (String fish : split) {
                    input.add(Integer.parseInt(fish));
                }
            }
            System.out.printf("Day 1 - Total amount of fish: %d\n", solve(daysPartOne));
            System.out.printf("Day 2 - Total amount of fish: %d\n", solve(daysPartTwo));
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.printf("Execution time: %d ms", (duration / 1000000));
    }

    public static long solve(int days) {
        long[] fish = new long[9];
        for (Integer startingFish : input) {
            fish[startingFish]++;
        }
        for (int i = 0; i < days; i++) {
            long zeroFishes = fish[0];
            System.arraycopy(fish, 1, fish, 0, fish.length - 1);
            fish[8] = zeroFishes;
            fish[6] += zeroFishes;
        }
        long counter = 0;
        for (long j : fish) {
            counter += j;
        }
        return counter;
    }
}

package day6;

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
            File file = new File("src/day6/input.txt");
//            File file = new File("src/day6/example.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                for (String fish : split) {
                    input.add(Integer.parseInt(fish));
                }
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
        for (int i = 0; i < daysPartOne; i++) {
            System.out.println("day " + (i + 1));
            int newFish = 0;
            for (int j = 0; j < input.size(); j++) {
                int fish = input.get(j);
                if (fish == 0) {
                    newFish++;
                    input.set(j, 6);
                } else {
                    input.set(j, fish - 1);
                }
            }
            for (int j = 0; j < newFish; j++) {
                input.add(8);
            }
        }
        System.out.println(input.size());
    }

    public static void solve2() {

    }
}

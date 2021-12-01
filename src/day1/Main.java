package day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static ArrayList<Integer> input = new ArrayList<>();

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        try {
            File file = new File("src/day1/input.txt");
            BufferedReader br1 = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br1.readLine()) != null) {
                input.add(Integer.parseInt(line));
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
        int counter = -1;
        int previous = 0;
        for (Integer number : input) {
            if (number > previous) {
                counter++;
            }
            previous = number;
        }
        System.out.println(counter);
    }

    public static void solve2() {
        int counter = 0;
        for (int i = 0; i < input.size() - 3; i++) {
            int currentWindow = 0;
            int nextWindow = 0;
            for (int j = i; j < i + 3; j++) {
                currentWindow += input.get(j);
                nextWindow += input.get(j + 1);
            }
            if (currentWindow < nextWindow) {
                counter++;
            }
        }
        System.out.println(counter);
    }
}

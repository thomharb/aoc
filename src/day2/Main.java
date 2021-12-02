package day2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    static ArrayList<String> input = new ArrayList<>();

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        try {
            File file = new File("src/day2/input.txt");
//            File file = new File("src/day2/example.txt");
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
        HashMap<String, Integer> totalDistanceInDirection = new HashMap<>();

        for (String move : input) {
            String[] moveSplit = move.split(" ");
            String direction = moveSplit[0];
            int distance = Integer.parseInt(moveSplit[1]);

            int currentDistance = totalDistanceInDirection.getOrDefault(direction, 0);
            totalDistanceInDirection.put(direction, (currentDistance + distance));
        }
        int depth = totalDistanceInDirection.get("down") - totalDistanceInDirection.get("up");
        int forward = totalDistanceInDirection.get("forward");

        System.out.println("depth * horizontal = " + depth * forward);
    }

    public static void solve2() {
        int horizontal = 0;
        int depth = 0;
        int aim = 0;
        for (String move : input) {
            String[] moveSplit = move.split(" ");
            String direction = moveSplit[0];
            int value = Integer.parseInt(moveSplit[1]);

            switch (direction) {
                case "forward" -> {
                    horizontal += value;
                    depth += aim * value;
                }
                case "up" -> aim -= value;
                case "down" -> aim += value;
            }
        }

        System.out.println("depth * horizontal = " + depth * horizontal);
    }
}

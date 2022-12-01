package aoc2021.day5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static ArrayList<String[]> input = new ArrayList<>();
    static int size = 1000;
    static int[][] map;

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        try {
            File file = new File("src/aoc2021.day5/input.txt");
//            File file = new File("src/aoc2021.day5/example.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                String[] lineSplit = line.replace(" -> ", ",").split(",");
                input.add(lineSplit);
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
        map = new int[size][size];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j] = 0;
            }
        }

        for (String[] line : input) {
            drawPipe(line, false);
        }

        System.out.println("Amount of overlaps: " + countOverlaps());
    }

    public static void solve2() {
        map = new int[size][size];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j] = 0;
            }
        }

        for (String[] line : input) {
            drawPipe(line, true);
        }

        System.out.println("Amount of overlaps: " + countOverlaps());
    }

    public static void drawPipe(String[] coords, boolean diagonal) {
        int x1 = Integer.parseInt(coords[0]);
        int y1 = Integer.parseInt(coords[1]);
        int x2 = Integer.parseInt(coords[2]);
        int y2 = Integer.parseInt(coords[3]);

        int offsetX = x2 - x1;
        int offsetY = y2 - y1;

        if (x1 == x2 || y1 == y2) {

            if (offsetX < 0) {
                offsetX = Math.abs(offsetX);
                x1 = x2;
            }
            if (offsetY < 0) {
                offsetY = Math.abs(offsetY);
                y1 = y2;
            }
            for (int i = x1; i < x1 + offsetX + 1; i++) {
                for (int j = y1; j < y1 + offsetY + 1; j++) {
                    map[j][i]++;
                }
            }
        } else if (diagonal && Math.abs(x1 - x2) == Math.abs(y1 - y2)) {
            int j = y1;
            if (x1 < x2 && y1 < y2) {
                for (int i = x1; i < x1 + offsetX + 1; i++) {
                    map[j][i]++;
                    j++;
                }
            } else if (x1 > x2 && y1 < y2) {
                for (int i = x1; i > x1 + offsetX - 1; i--) {
                    map[j][i]++;
                    j++;
                }
            } else if (x1 < x2) {
                for (int i = x1; i < x1 + offsetX + 1; i++) {
                    map[j][i]++;
                    j--;
                }
            } else {
                for (int i = x1; i > x1 + offsetX - 1; i--) {
                    map[j][i]++;
                    j--;
                }
            }
        }
    }

    public static int countOverlaps() {
        int counter = 0;
        for (int[] ints : map) {
            for (int j = 0; j < map.length; j++) {
                if (ints[j] > 1) {
                    counter++;
                }
            }
        }
        return counter;
    }
}

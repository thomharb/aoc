package day9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    // example w = 10, h = 5 - input w = 100, h = 100
    static int width = 100;
    static int height = 100;

    static int[][] map = new int[height][];

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        try {
            File file = new File("src/day9/input.txt");
//            File file = new File("src/day9/example.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                int[] num = new int[line.length()];
                for (int i = 0; i < line.length(); i++) {
                    num[i] = line.charAt(i) - '0';
                }
                map[row] = num;
                row++;
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
        int risk = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int value = map[i][j];
                if (low(i, j, value)) {
                    risk += value + 1;
                }
            }
        }
        System.out.printf("Part 1: Total risk level = %d\n", risk);
    }

    public static boolean low(int i, int j, int value) {
        int[][] directions = new int[][]{{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

        for (int[] direction : directions) {
            int dirX = direction[0];
            int dirY = direction[1];

            int x = i + dirX;
            int y = j + dirY;

            if (x >= 0 && x < height && y >= 0 && y < width) {
                if (map[x][y] <= value) {
                    return false;
                }
            }
        }
//        System.out.printf("Smaller! %d, %d with value %d\n", i, j, value);
        return true;
    }
}

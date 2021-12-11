package day11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    // steps to 500 for p2
    static int steps = 100;
    static int width = 10;
    static int height = 10;

    static int[][] map = new int[height][];

    static ArrayList<int[]> flashed;

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        try {
            File file = new File("src/day11/input.txt");
//            File file = new File("src/day11/example.txt");
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

            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();

        long duration = (endTime - startTime);
        System.out.printf("Execution time: %d ms", (duration / 1000000));
    }

    public static void solve() {
        int flashCounter = 0;
        int allFlashed = -1;
        for (int s = 0; s < steps; s++) {
            System.out.println("Step " + s);
            printMap();

            flashed = new ArrayList<>();
            increaseEnergy();

            while (notFlashedYet()) {
                ArrayList<int[]> toFlash = toFlash();
                for (int[] point : toFlash) {
                    flash(point[0], point[1]);
                }
            }

            for (int[] point : flashed) {
                map[point[0]][point[1]] = 0;
                flashCounter++;
            }

            if (allFlashed == -1 && allFlashed()) {
                allFlashed = s + 1;
            }
        }
        System.out.printf("Part 1: Total amount of flashes = %d\n", flashCounter);
        System.out.printf("Part 2: All flashed at step %d\n", allFlashed);
    }

    public static void increaseEnergy() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j]++;
            }
        }
    }

    public static ArrayList<int[]> toFlash() {
        ArrayList<int[]> toFlash = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j] > 9 && !flashed(i, j)) {
                    toFlash.add(new int[] {i, j});
                }
            }
        }
        return toFlash;
    }

    public static void flash(int i, int j) {
        map[i][j] = 0;
        flashed.add(new int[] {i, j});
        ArrayList<int[]> neighbours = getNeighbours(i, j);
        for (int[] neighbour : neighbours) {
            int neighbourX = neighbour[0];
            int neighbourY = neighbour[1];

            map[neighbourX][neighbourY]++;
        }
    }

    public static ArrayList<int[]> getNeighbours(int i, int j) {
        ArrayList<int[]> neighbours = new ArrayList<>();
        int[][] directions = new int[][]{{0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}};
        for (int[] direction : directions) {
            int dirX = direction[0];
            int dirY = direction[1];

            int x = i + dirX;
            int y = j + dirY;
            if (x >= 0 && x < height && y >= 0 && y < width) {
                neighbours.add(new int[]{x, y});
            }
        }
        return neighbours;
    }

    public static boolean flashed(int i, int j) {
        for (int[] point : flashed) {
            if (point[0] == i && point[1] == j) {
                return true;
            }
        }
        return false;
    }

    public static boolean notFlashedYet() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j] > 9) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void printMap() {
        StringBuilder mapSB = new StringBuilder();
        for (int i = 0; i < height; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < width; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
            mapSB.append(sb);
        }
        System.out.println(mapSB);
    }

    public static boolean allFlashed() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}

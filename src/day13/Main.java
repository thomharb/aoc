package day13;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    static boolean test = false;

    static int width = 0;
    static int height = 0;
    static int[][] map;
    static ArrayList<int[]> folds = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();
        String path = test ? "src/day13/example.txt" : "src/day13/input.txt";
        getWidthHeight(Files.readString(Paths.get(path), StandardCharsets.US_ASCII));
        map = new int[width][height];
        parse(Files.readString(Paths.get(path), StandardCharsets.US_ASCII));
        solve1();
        solve2();
        long endTime = System.nanoTime();

        long duration = (endTime - startTime);
        System.out.printf("Execution time: %d ms", (duration / 1000000));
    }

    static void getWidthHeight(String file) {
        String[] lines = file.split("\r\n");
        for (String line : lines) {
            if (!line.equals("") && line.charAt(0) != 'f') {
                String[] coords = line.split(",");
                int x = Integer.parseInt(coords[0]);
                int y = Integer.parseInt(coords[1]);
                if ((x + 1) > width) width = x + 1;
                if ((y + 1) > height) height = y + 1;
            }
        }
    }

    static void parse(String file) {
        String[] lines = file.split("\r\n");
        for (String line : lines) {
            if (line.equals("")) {
                continue;
            } else if (line.charAt(0) == 'f') {
                String[] split = line.split("fold along ");
                String[] fold = split[1].split("=");
                if (fold[0].equals("x")) folds.add(new int[]{0, Integer.parseInt(fold[1])});
                else folds.add(new int[]{1, Integer.parseInt(fold[1])});
            } else {
                String[] coords = line.split(",");
                map[Integer.parseInt(coords[0])][Integer.parseInt(coords[1])]++;
            }
        }
    }

    static void solve1() {
        int[] fold = folds.get(0);
        switch (fold[0]) {
            case 0 -> foldX(fold[1]);
            case 1 -> foldY(fold[1]);
        }
        System.out.printf("Part 1: Total points after fold = %d\n", count());
    }

    static void solve2() {
        for (int[] fold :folds) {
            switch (fold[0]) {
                case 0 -> foldX(fold[1]);
                case 1 -> foldY(fold[1]);
            }
        }
        System.out.printf("Part 2: Total points after fold = %d\n", count());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.max(width, height); i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < Math.max(width, height); j++) {
                row.append(map[i][j]);
            }
            sb.append(row).append("\n");
        }
        System.out.println(sb.toString());
    }

    static void foldX(int foldAt) {
        for (int y = 0; y < height; y++) {
            for (int x = foldAt + 1; x < width; x++) {
                if (map[x][y] > 0) {
                    int newX = foldAt - (x - foldAt);
                    if (map[newX][y] == 0) map[newX][y]++;
                }
            }
        }
        width = (width - 1) / 2;
    }

    static void foldY(int foldAt) {
        for (int y = foldAt + 1; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (map[x][y] > 0) {
                    int newY = foldAt - (y - foldAt);
                    if (map[x][newY] == 0) map[x][newY]++;
                }
            }
        }
        height = (height - 1) / 2;
    }

    static int count() {
        int counter = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (map[i][j] > 0) {
                    counter++;
                }
            }
        }
        return counter;
    }
}

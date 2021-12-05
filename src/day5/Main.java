package day5;

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
            File file = new File("src/day5/input.txt");
//            File file = new File("src/day5/example.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                String[] lineSplit = line.replace(" -> ", ",").split(",");
                input.add(lineSplit);
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
        map = new int[size][size];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j] = 0;
            }
        }

        for (String[] line : input) {
            drawPipe(line);
        }

        System.out.println(Arrays.deepToString(map));
        System.out.println("Amount of overlaps: " + countOverlaps());
    }

    public static void drawPipe(String[] coords) {
        int x1 = Integer.parseInt(coords[0]);
        int y1 = Integer.parseInt(coords[1]);
        int x2 = Integer.parseInt(coords[2]);
        int y2 = Integer.parseInt(coords[3]);

        if (x1 == x2 || y1 == y2) {

            int offsetX = x2 - x1;
            int offsetY = y2 - y1;

            if (offsetX < 0) {
                offsetX = Math.abs(offsetX);
                int temp = x1;
                x1 = x2;
                x2 = temp;
            }
            if (offsetY < 0) {
                offsetY = Math.abs(offsetY);
                int temp = y1;
                y1 = y2;
                y2 = temp;
            }

            System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);

            System.out.println("offsetX " + offsetX);
            System.out.println("offsetY " + offsetY);

            for (int i = x1; i < x1 + offsetX + 1; i++) {
                for (int j = y1; j < y1 + offsetY + 1; j++) {
                    System.out.println(i + " " + j);
                    map[j][i]++;
                }
            }
        }
    }

    public static int countOverlaps() {
        int counter = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] > 1) {
                    counter++;
                }
            }
        }
        return counter;
    }
}

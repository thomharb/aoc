package aoc2021.day9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    // example w = 10, h = 5 - input w = 100, h = 100
    static int width = 10;
    static int height = 5;

    static int[][] map = new int[height][];

    static ArrayList<ArrayList<int[]>> basins = new ArrayList<>();

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        try {
//            File file = new File("src/aoc2021.day9/input.txt");
            File file = new File("src/aoc2021.day9/example.txt");
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
            solve2();
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
        ArrayList<int[]> neighbours = getNeighbours(i, j);
        for (int[] neighbour : neighbours) {
            if (map[neighbour[0]][neighbour[1]] <= value) {
                return false;
            }
        }
//        System.out.printf("Smaller! %d, %d with value %d\n", i, j, value);
        return true;
    }

    public static ArrayList<int[]> getNeighbours(int i, int j) {
        ArrayList<int[]> neighbours = new ArrayList<>();
        int[][] directions = new int[][]{{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
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

    public static void solve2() {
        ArrayList<int[]> basinPoints = getBasinPoints();
        for (int[] lowPoints : basinPoints) {
            ArrayList<int[]> basin = new ArrayList<>();
            int x = lowPoints[0];
            int y = lowPoints[1];

            ArrayList<int[]> getBasin = isInBasins(x, y, basins);
            if (getBasin != null) {
                basin = getBasin;
                basins.remove(getBasin);
            } else {
                basin.add(new int[]{x, y});
            }

            ArrayList<int[]> neighbours = getNeighbours(x, y);
            for (int[] neighbour : neighbours) {
                int neighbourX = neighbour[0];
                int neighbourY = neighbour[1];
                for (int[] lowPointsNeighbour : basinPoints) {
                    if (neighbourX == lowPointsNeighbour[0] && neighbourY == lowPointsNeighbour[1]) {
                        if (!isInBasin(lowPointsNeighbour[0], lowPointsNeighbour[1], basin)) {
                            basin.add(new int[]{neighbourX, neighbourY});
                        }
                    }

                }
            }
            basins.add(basin);
        }
        mergeBasins();
        ArrayList<int[]> one = new ArrayList<>();
        ArrayList<int[]> two = new ArrayList<>();
        ArrayList<int[]> three = new ArrayList<>();
        for (ArrayList<int[]> bassin : basins) {
            int size = bassin.size();
            if (size > one.size()) {
                three = two;
                two = one;
                one = bassin;
            } else if (size > two.size()) {
                three = two;
                two = bassin;
            } else if (size > three.size()) {
                three = bassin;
            }
        }
        System.out.printf("Part 2: %d\n", one.size() * two.size() * three.size());
    }


    public static ArrayList<int[]> getBasinPoints() {
        ArrayList<int[]> lowPoints = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j] != 9) {
                    lowPoints.add(new int[]{i, j});
                }
            }
        }
        return lowPoints;
    }

    public static ArrayList<int[]> isInBasins(int i, int j, ArrayList<ArrayList<int[]>> basins) {
        for (ArrayList<int[]> basin : basins) {
            for (int[] point : basin) {
                if (point[0] == i && point[1] == j) {
                    return basin;
                }
            }
        }
        return null;
    }

    public static boolean isInBasin(int i, int j, ArrayList<int[]> basin) {
        for (int[] point : basin) {
            if (point[0] == i && point[1] == j) {
                return true;
            }
        }
        return false;
    }

    public static void mergeBasins() {
        ArrayList<ArrayList<int[]>> finalBasins = new ArrayList<>();
        for (ArrayList<int[]> bassinX : basins) {
            ArrayList<int[]> merged = new ArrayList<>(bassinX);
            for (ArrayList<int[]> bassinY : basins) {
                if (bassinX == bassinY) {
                    continue;
                }
                if (hasOverlap(bassinX, bassinY)) {
                    for (int[] point : bassinY) {
                        merged.add(point);
                    }
                }
            }
            ArrayList<int[]> remove = new ArrayList<>();
            for (int[] pointX : merged) {
                for (int[] pointY : merged) {
                    if (pointX[0] == pointY[0] && pointX[1] == pointY[1] && pointX != pointY) {
                        remove.add(pointX);
                    }
                }
            }
            ArrayList<int[]> keep = new ArrayList<>();
            for (int[] pointKeep : remove) {
                if (!isInBasin(pointKeep[0], pointKeep[1], keep)) {
                    keep.add(pointKeep);
                }
            }
            for (int[] pointRemove : remove) {
                merged.remove(pointRemove);
            }
            for (int[] pointKeep : keep) {
                merged.add(pointKeep);
            }
            if (isInBasins(merged.get(0)[0], merged.get(0)[1], finalBasins) == null) {
                finalBasins.add(merged);
            }

        }
        basins = finalBasins;
    }

    public static boolean hasOverlap(ArrayList<int[]> a, ArrayList<int[]> b) {
        for (int[] pointA : a) {
            for (int[] pointB : b) {
                if (pointA[0] == pointB[0] && pointA[1] == pointB[1] && pointA != pointB) {
                    return true;
                }
            }
        }
        return false;
    }
}

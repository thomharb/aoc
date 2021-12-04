package day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static boolean parsedNumbers = false;
    static int len = 5;

    static ArrayList<String> numbers = new ArrayList<>();
    static ArrayList<String[][]> boards = new ArrayList<>();

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        try {
            File file = new File("src/day4/input.txt");
//             File file = new File("src/day4/example.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String[][] board = new String[5][5];
            int row = 0;
            String line;
            while ((line = br.readLine()) != null) {
                if (!parsedNumbers) {
                    parseNumbers(line);
                } else if (line.equals("")) {
                    boards.add(board);
                    board = new String[5][5];
                    row = 0;
                } else {
                    String[] lineSplit = line.split("\\s+");
                    if (lineSplit[0].equals("")) {
                        lineSplit = Arrays.copyOfRange(lineSplit, 1, len + 1);
                    }
                    board[row] = lineSplit;
                    row++;
                }
            }

            boards.add(board);
            boards.remove(0);
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();

        long duration = (endTime - startTime);
        System.out.printf("Execution time: %d ms", (duration / 1000000));
    }

    public static void parseNumbers(String line) {
        String[] lineSplit = line.split(",");
        numbers.addAll(Arrays.asList(lineSplit));
        parsedNumbers = true;
    }

    public static void solve() {
        for (String number : numbers) {
            for (String[][] board : boards) {
                for (int i = 0; i < len; i++) {
                    for (int j = 0; j < len; j++) {
                        if (board[i][j].equals(number)) {
                            board[i][j] = "X";
                        }
                        String[][] win = bingo();
                        if (win != null) {
                            finalScore(win, Integer.parseInt(number));
                            return;
                        }
                    }
                }
            }
        }
    }

    public static String[][] bingo() {
        for (String[][] board : boards) {
            if (checkCols(board) || checkRows(board)) {
                return board;
            }
        }
        return null;
    }

    public static boolean checkRows(String[][] board) {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (!board[i][j].equals("X")) {
                    break;
                } else if (j == (len - 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkCols(String[][] board) {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (!board[j][i].equals("X")) {
                    break;
                } else if (j == (len - 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void finalScore(String[][] board, int number) {
        int sum = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (!board[i][j].equals("X")) {
                    sum += Integer.parseInt(board[i][j]);
                }
            }
        }

        System.out.println("Final score = " + sum * number);
    }

}

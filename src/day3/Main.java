package day3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static ArrayList<String> input = new ArrayList<>();

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        try {
            File file = new File("src/day3/input.txt");
//             File file = new File("src/day3/example.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                input.add(line);
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
        int binaryWordLength = input.get(0).length();

        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();

        for (int i = 0; i < binaryWordLength; i++) {
            int counter = 0;
            for (String word : input) {
                if (word.charAt(i) == '1') {
                    counter++;
                }
            }
            if (counter > (input.size()) / 2) {
                gamma.append("1");
                epsilon.append("0");
            } else {
                gamma.append("0");
                epsilon.append("1");
            }
        }

        int gammaDecimal = Integer.parseInt(gamma.toString(), 2);
        int epsilonDecimal = Integer.parseInt(epsilon.toString(), 2);

        System.out.println("gamma * epsilon = " + gammaDecimal * epsilonDecimal);
    }
}

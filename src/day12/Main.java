package day12;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    static boolean test = true;
    static HashMap<String, ArrayList<String>> paths = new HashMap<>();
    static HashMap<String, ArrayList<String>> edges = new HashMap<>();

    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();
        String path = test ? "src/day12/example.txt" : "src/day12/input.txt";
        parse(Files.readString(Paths.get(path), StandardCharsets.US_ASCII));
        solve();
        long endTime = System.nanoTime();

        long duration = (endTime - startTime);
        System.out.printf("Execution time: %d ms", (duration / 1000000));
    }

    static void parse(String file) {
        String[] lines = file.split("\r\n");
        for (String line : lines) {
            String[] fromTo = line.split("-");
            String from = fromTo[0];
            String to = fromTo[1];

            if (!from.equals("start") && !to.equals("start") && !from.equals("end") && !to.equals("end")) {
                edges.computeIfAbsent(from, a -> new ArrayList<>()).add(to);
                edges.computeIfAbsent(to, a -> new ArrayList<>()).add(from);
            } else if (from.equals("start")) edges.computeIfAbsent(from, a -> new ArrayList<>()).add(to);
            else if (to.equals("start")) edges.computeIfAbsent(to, a -> new ArrayList<>()).add(from);
            else if (from.equals("end")) edges.computeIfAbsent(to, a -> new ArrayList<>()).add(from);
            else edges.computeIfAbsent(from, a -> new ArrayList<>()).add(to);
        }
    }

    static void solve() {
        System.out.println(edges);
        visited = new ArrayList<>();
        DFS("start");

        System.out.println(counter);
    }

    static ArrayList<String> visited;
    static int counter = 0;
    static void DFS(String edge) {
        if (edge.equals("end")) {
            counter++;
            return;
        }

        visited.add(edge);
        System.out.println("Visit edge " + edge);

        for (String neighbour : edges.get(edge)) {
            if (!visited.contains(neighbour)) {
                DFS(neighbour);
            }
        }
    }
}

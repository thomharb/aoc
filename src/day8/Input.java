package day8;

import java.util.ArrayList;
import java.util.List;

public class Input {

    public List<String> input;
    public List<String> output;

    public Input(String input) {
        String[] split = input.split(" \\| ");
        this.input = List.of(split[0].split(" "));
        this.output = List.of(split[1].split(" "));
    }
}

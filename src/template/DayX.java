package template;

import resources.Day;

import java.io.IOException;
import java.util.Objects;

public class DayX extends Day {
    private static final boolean example = true;

    public static void main(String[] args) throws IOException {
        String path = example ?
                Objects.requireNonNull(DayX.class.getResource("example.txt")).getPath() :
                Objects.requireNonNull(DayX.class.getResource("input.txt")).getPath();
        new DayX().run(path, "\r\n");
    }

    @Override
    public Object one() {
        // -- CODE -- //
        return null;
    }

    @Override
    public Object two() {
        // -- CODE -- //
        return null;
    }

}

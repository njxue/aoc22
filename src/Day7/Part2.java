package Day7;

import static Day7.Part1.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class Part2 {
    private static final int MAX_SIZE = 70000000;
    private static final int MIN_SIZE = 30000000;

    public static int solve() throws FileNotFoundException {
        HashMap<String, Integer> dirSize = calculateSizes();
        int rootSize = dirSize.get("/");
        int toFree = rootSize - (MAX_SIZE - MIN_SIZE);

        int minDirSize = Integer.MAX_VALUE;

        for (Integer size : dirSize.values()) {
            if (size >= toFree) {
                minDirSize = Math.min(minDirSize, size);
            }
        }

        return minDirSize;
    }
}
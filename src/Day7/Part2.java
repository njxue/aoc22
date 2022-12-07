package Day7;

import static Day7.Part1.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;


public class Part2 {
    private static final String CHANGE_DIR_WORD = "cd";
    private static final int MAX_SIZE = 70000000;
    private static final int MIN_SIZE = 30000000;

    public static int solve() throws FileNotFoundException {
        HashMap<String, Integer> dirSize = new HashMap<>();
        Stack<String> dirStack = new Stack<>();
        Scanner sc = new Scanner(new File("src/Day7/input"));
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            if (isInput(str)) {
                String command = getCommand(str);
                if (command.equals(CHANGE_DIR_WORD)) {
                    String dir = getDir(str);
                    updateStack(dir, dirStack, dirSize);
                }
            } else {
                if (isFile(str)) {
                    updateSizes(getFileSize(str), dirStack, dirSize);
                }
            }
        }

        sc.close();
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
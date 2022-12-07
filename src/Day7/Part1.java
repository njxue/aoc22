package Day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Part1 {
    private static final String CHANGE_DIR_WORD = "cd";
    private static final String DIR_WORD = "dir";
    private static final char INPUT_CHAR = '$';
    private static final String PARENT_DIR_PATH = "..";

    private static final int MAX_SIZE = 100000;

    public static int solve() throws FileNotFoundException {
        HashMap<String, Integer> dirSize = new HashMap<>();
        Stack<String> dirStack = new Stack<>();
        Scanner sc = new Scanner(new File("src/Day7/input"));
        int totalSize = 0;
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

        for (Integer size : dirSize.values()) {
            if (size <= MAX_SIZE) {
                totalSize += size;
            }
        }
        return totalSize;
    }

    static boolean isInput(String str) {
        return str.charAt(0) == INPUT_CHAR;
    }

    static String getCommand(String input) {
        return input.substring(2,4);
    }

    static boolean isFile(String str) {
        return !str.startsWith(DIR_WORD);
    }

    static int getFileSize(String str) {
        return Integer.parseInt(str.split(" ")[0]);
    }

    static String getDir(String input) {
        return input.substring(5);
    }


    static String getAbsolutePath(String dirName, Stack<String> dirStack) {
        // replacing '/' with '\' because '/' is used for the root
        dirStack.push(dirName);
        String path = String.join("\\", dirStack);
        dirStack.pop();
        return path;
    }

    static void updateStack(String dir, Stack<String> dirStack, HashMap<String, Integer> dirSize) {
        if (dir.equals(PARENT_DIR_PATH)) {
            dirStack.pop();
        } else {
            String newDir = getAbsolutePath(dir, dirStack);
            dirStack.push(newDir);
            if (!dirSize.containsKey(newDir)) {
                dirSize.put(newDir, 0);
            }
        }
    }

    static void updateSizes(int size, Stack<String> dirStack, HashMap<String, Integer> dirSize) {
        for (String dir : dirStack) {
            if (!dirSize.containsKey(dir)) {
                dirSize.put(dir, size);
            } else {
                dirSize.put(dir, dirSize.get(dir) + size);
            }
        }
    }
}
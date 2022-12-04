package Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {
    public static int solve() throws FileNotFoundException {
        File file = new File("src/Day4/input");
        Scanner sc = new Scanner(file);
        int count = 0;
        while (sc.hasNextLine()) {
            String pair = sc.nextLine();
            String firstElf = getFirstElf(pair);
            String secondElf = getSecondElf(pair);

            if (hasOverlap(firstElf, secondElf)) {
                count++;
            }
        }
        return count;
    }

    private static boolean hasOverlap(String firstElf, String secondElf) {
        int firstElfFirstRoom = getFirstRoom(firstElf);
        int firstElfLastRoom = getLastRoom(firstElf);

        int secondElfFirstRoom = getFirstRoom(secondElf);
        int secondElfLastRoom = getLastRoom(secondElf);

        return firstElfFirstRoom <= secondElfFirstRoom && firstElfLastRoom >= secondElfLastRoom
                || secondElfFirstRoom <= firstElfFirstRoom && secondElfLastRoom >= firstElfLastRoom;
    }

    static int getFirstRoom(String str) {
        return Integer.parseInt(str.split("-")[0]);
    }

    static int getLastRoom(String str) {
        return Integer.parseInt(str.split("-")[1]);
    }

    static String getFirstElf(String str) {
        return str.split(",")[0];
    }

    static String getSecondElf(String str) {
        return str.split(",")[1];
    }
}

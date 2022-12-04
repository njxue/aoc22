package Day4;

import static Day4.Part1.getFirstElf;
import static Day4.Part1.getFirstRoom;
import static Day4.Part1.getLastRoom;
import static Day4.Part1.getSecondElf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {
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

        return firstElfFirstRoom >= secondElfFirstRoom && firstElfFirstRoom <= secondElfLastRoom
                || secondElfFirstRoom >= firstElfFirstRoom && secondElfFirstRoom <= firstElfLastRoom;
    }
}

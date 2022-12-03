package Day3;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.HashSet;
import java.util.Scanner;

public class Part1 {
    public static int solve() throws FileNotFoundException {
        File file = new File("src/Day3/input");
        Scanner sc = new Scanner(file);

        int prioritySum = 0;

        while (sc.hasNextLine()) {
            HashSet<Character> set = new HashSet<>();
            String ruckSack = sc.nextLine();
            int numCompartmentItems = ruckSack.length() / 2;
            for (int i = 0; i < numCompartmentItems; i++) {
                set.add(ruckSack.charAt(i));
            }

            for (int j = 0; j < numCompartmentItems; j++) {
                char item = ruckSack.charAt(j + numCompartmentItems);
                if (set.contains(item)) {
                    prioritySum += getPriority(item);
                    break;
                }
            }
        }
        return prioritySum;
    }

    public static int getPriority(char item) {
        if (Character.isLowerCase(item)) {
            return (int) item - 96;
        }
        return (int) item - 38;
    }
}

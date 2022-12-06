package Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Parts {
    public static int solve() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("src/Day6/input"));
        String str = sc.nextLine();
        sc.close();

        int i = 0;
        int markerLength = 14;
        boolean done = false;

        while (!done) {
            done = true;
            HashMap<Character, Integer> seen = new HashMap<>();
            for (int j = 0; j < markerLength; j++) {
                char c = str.charAt(j + i);
                if (seen.containsKey(c)) {
                    done = false;
                    i += seen.get(c) + 1;
                    break;
                }
                seen.put(c, j);
            }
        }

        return i + markerLength;
    }
}

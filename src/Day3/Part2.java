package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Part2 {
    public static int solve() throws FileNotFoundException {
        File file = new File("src/Day3/input");
        Scanner sc = new Scanner(file);

        int prioritySum = 0;

        while (sc.hasNextLine()) {
            String firstRuckSack = sc.nextLine();
            String secondRuckSack = sc.nextLine();
            HashSet<Character> set = getCommon(firstRuckSack, secondRuckSack);

            String thirdRuckSack = sc.nextLine();
            for (int i = 0; i < thirdRuckSack.length(); i++) {
                char item = thirdRuckSack.charAt(i);
                if (set.contains(item)) {
                    prioritySum += Part1.getPriority(item);
                    break;
                }
            }
        }
        return prioritySum;
    }

    private static HashSet<Character> getCommon(String first, String second) {
        HashSet<Character> firstSet = new HashSet<>();
        HashSet<Character> commonSet = new HashSet<>();
        for (int i = 0; i < first.length(); i++) {
            firstSet.add(first.charAt(i));
        }

        for (int j = 0; j < second.length(); j++) {
            char item = second.charAt(j);
            if (firstSet.contains(item)) {
                commonSet.add(item);
            }
        }
        return commonSet;
    }
}

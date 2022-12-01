package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1 {
    public static int solve1() {
        int maxCalories = 0;
        int calories = 0;
        try {
            File file = new File("src/Day1/input");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                if (str.isEmpty()) {
                    maxCalories = Math.max(maxCalories, calories);
                    calories = 0;
                } else {
                    calories += Integer.parseInt(str);
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found");
        }
        return maxCalories;
    }

    public static int solve2() {
        int[] highest = new int[3];
        int calories = 0;
        try {
            File file = new File("src/Day1/input");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                if (str.isEmpty()) {
                    int i = findMinIndex(highest);
                    if (calories > highest[i]) {
                        highest[i] = calories;
                    }
                    calories = 0;
                } else {
                    calories += Integer.parseInt(str);
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found");
        }
        return highest[0] + highest[1] + highest[2];
    }

    private static int findMinIndex(int[] arr) {
        int minIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < arr[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }
}

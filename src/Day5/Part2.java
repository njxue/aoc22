package Day5;

import static Day5.Part1.generateStacks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Part2 {
    public static String solve() throws FileNotFoundException {
        HashMap<Integer, Stack<Character>> map = generateStacks();
        File file = new File("src/Day5/actionsInput");
        Scanner sc = new Scanner(file);
        Stack<Character> intermediateStack = new Stack<>();
        // move crates
        while (sc.hasNextLine()) {
            if (!sc.hasNext()) { // eof line
                break;
            }
            sc.next(); // "move"
            int quantity = sc.nextInt();
            sc.next(); // "from"
            Stack<Character> fromStack = map.get(sc.nextInt());
            sc.next(); // "to"
            Stack<Character> toStack = map.get(sc.nextInt());


            for (int i = 0; i < quantity; i++) {
                intermediateStack.push(fromStack.pop());
            }

            for (int j = 0; j < quantity; j++) {
                toStack.push(intermediateStack.pop());
            }
        }

        sc.close();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < map.size(); i++) {
            sb.append(map.get(i + 1).pop());
        }
        return sb.toString();
    }
}

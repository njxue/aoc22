package Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Part1 {
    public static String solve() throws FileNotFoundException {
        HashMap<Integer, Stack<Character>> map = generateStacks();
        File file = new File("src/Day5/actionsInput");
        Scanner sc = new Scanner(file);

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
                toStack.push(fromStack.pop());
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < map.size(); i++) {
            sb.append(map.get(i + 1).pop());
        }
        return sb.toString();
    }

    static HashMap<Integer, Stack<Character>> generateStacks() throws FileNotFoundException {
        File file = new File("src/Day5/stackInput");
        Scanner sc = new Scanner(file);
        HashMap<Integer, Stack<Character>> map = new HashMap<>();
        while (sc.hasNextLine()) {
            int stackNum = sc.nextInt();
            String str = sc.nextLine();
            Stack<Character> stack = new Stack<>();
            map.put(stackNum, stack);
            for (int i = 0; i < str.length(); i++) {
                stack.push(str.charAt(i));
            }

        }
        sc.close();
        return map;
    }
}

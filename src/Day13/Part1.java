package Day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Part1 {
    public static int solve() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("src/Day13/input"));
        int res = 0;
        int index = 1;

        while (sc.hasNextLine()) {
            List<Object> first = parseList(sc.nextLine());
            List<Object> second = parseList(sc.nextLine());
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
            if (compare(first, second) < 0) {
                res += index;
                System.out.println(index);
            }
            index++;
        }

        sc.close();
        return res;
    }

    static int compare(Object first, Object second) {
        if (!(first instanceof List) && !(second instanceof List)) {
            return (int) first - (int) second;
        }

        if (!(first instanceof List)) {
            first = List.of(first);
        }

        if (!(second instanceof List)) {
            second = List.of(second);
        }

        List<Object> fList = (List<Object>) first;
        List<Object> sList = (List<Object>) second;

        if (fList.isEmpty() && sList.isEmpty()) {
            return 0;
        }

        if (fList.isEmpty()) {
            return -1;
        }
        if (sList.isEmpty()) {
            return 1;
        }

        int headCompare = compare(fList.get(0), sList.get(0));
        if (headCompare == 0) {
            return compare(fList.subList(1, fList.size()), sList.subList(1, sList.size()));
        }
        return headCompare;
    }

    static List<Object> parseList(String str) {
        Stack<List<Object>> stack = new Stack<>();
        int i = 0;
        while (i < str.length() - 1) {
            char c = str.charAt(i);
            if (c == ',') {
                i++;
                continue;
            } else if (c == '[') {
                stack.push(new ArrayList<>());
            } else if (c == ']') {
                List<Object> sub = stack.pop();
                stack.peek().add(sub);
            } else {
                StringBuilder j = new StringBuilder();
                while (Character.isDigit(c)) {
                    j.append(c);
                    i++;
                    c = str.charAt(i);
                }
                stack.peek().add(Integer.parseInt(String.valueOf(j)));
                i--;
            }
            i++;
        }
        return stack.pop();
    }
}

package Day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Part1 {
    private static class Monkey {

        private long itemsInspected = 0;

        Consumer<Long> trueAction;
        Consumer<Long> falseAction;
        Predicate<Long> pred;
        Function<Long, Long> op;

        Queue<Long> items = new LinkedList<>();

        void addItem(long i) {
            items.add(i);
        }

        void throwItems() {
            while (!items.isEmpty()) {
                itemsInspected++;
                long i = items.poll();
                long newWorry = op.apply(i) / (long) 3;
                if (test(newWorry)) {
                    trueAction.accept(newWorry);
                } else {
                    falseAction.accept(newWorry);
                }
            }
        }

        boolean test(long worryLevel) {
            return pred.test(worryLevel);
        }

        void setTrueAction(Consumer<Long> f) {
            trueAction = f;
        }

        void setFalseAction(Consumer<Long> f) {
            falseAction = f;
        }

        void setPred(Predicate<Long> p) {
            pred = p;
        }

        void setOperation(Function<Long, Long> f) {
            op = f;
        }

        int compareTo(Monkey other) {
            if (itemsInspected > other.itemsInspected) {
                return -1;
            } else if (itemsInspected == other.itemsInspected) {
                return 0;
            }
            return 1;
        }
    }

    public static List<Monkey> monkeys = new ArrayList<>();

    public static long solve() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("src/Day11/input"));
        int numRounds = 20;

        // initialise monkeys
        while (sc.hasNextLine()) {
            if (sc.next().isEmpty()) {
                continue;
            }
            sc.nextLine(); // Consume the line "Monkey x"
            Monkey m = new Monkey();
            monkeys.add(m);
            List<Long> items = parseItems(sc.nextLine());
            for (long item : items) {
                m.addItem(item);
            }
            m.setOperation(parseOperation(sc.nextLine()));
            m.setPred(parsePred(sc.nextLine()));
            m.setTrueAction(parseAction(sc.nextLine()));
            m.setFalseAction(parseAction(sc.nextLine()));
        }

        // start throwing
        for (int i = 0; i < numRounds; i++) {
            runRound();
        }

        // monkey business
        monkeys.sort(Monkey::compareTo);
        return monkeys.get(0).itemsInspected * monkeys.get(1).itemsInspected;
    }

    private static void runRound() {
        for (Monkey m : monkeys) {
            m.throwItems();
        }
    }

    private static List<Long> parseItems(String items) {
        List<Long> i = new ArrayList<>();
        Arrays.stream(items.split(": ")[1].split(", ")).forEach(item -> i.add(Long.parseLong(item)));
        return i;
    }

    private static Function<Long, Long> parseOperation(String str) {
        String[] arr = str.split(" ");
        String op = arr[arr.length - 2];
        String qty = arr[arr.length - 1];

        switch (op) {
        case "+":
            return i -> i + parseSecondOperand(i, qty);
        case "*":
            return i -> i * parseSecondOperand(i, qty);
        case "-":
            return i -> i - parseSecondOperand(i, qty);
        default:
            return i -> i / parseSecondOperand(i, qty);
        }
    }

    private static long parseSecondOperand(long first, String second) {
        if (second.equals("old")) {
            return 1;
        }
        return Long.parseLong(second);
    }

    private static Predicate<Long> parsePred(String pred) {
        String[] arr = pred.split(" ");
        long i = Long.parseLong(arr[arr.length - 1]);
        return j -> j % i == 0;
    }

    private static Consumer<Long> parseAction(String action) {
        String[] arr = action.split(" ");
        int i = Integer.parseInt(arr[arr.length - 1]);
        return item -> monkeys.get(i).addItem(item);
    }
}

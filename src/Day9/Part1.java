package Day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Part1 {
    static final char RIGHT = 'R';
    static final char LEFT = 'L';
    static final char UP = 'U';
    static final char DOWN = 'D';

    public static int solve() throws FileNotFoundException {
        HashSet<String> visited = new HashSet<>();
        int[][] pos = new int[9][2];
        Scanner sc = new Scanner(new File("src/Day9/input"));
        int[] tail = {0,0};
        int[] head = {0,0};
        while (sc.hasNextLine()) {
            String instruction = sc.nextLine();
            addNewPositions(head, tail, visited, instruction);
        }
        sc.close();
        return visited.size();
    }

    static void addNewPositions(int[] head, int[] tail, HashSet<String> visited, String instruction) {
        char dir = getDirection(instruction);
        int steps = getSteps(instruction);

        for (int i = 0; i < steps; i++) {
            nextHead(head, dir);
            nextTail(head, tail, dir);
            visited.add(Arrays.toString(tail));
        }
    }

    static void nextHead(int[] head, char dir) {
        if (dir == RIGHT) {
            head[0] += 1;
        } else if (dir == LEFT) {
            head[0] -= 1;
        } else if (dir == UP) {
            head[1] += 1;
        } else {
            head[1] -= 1;
        }
    }

    static void nextTail(int[] newHead, int[] tail, char dir) {
        if (isInContact(newHead, tail)) {
            return;
        }

        if (dir == 'R') {
            tail[0] = newHead[0] - 1;
            tail[1] = newHead[1];
        } else if (dir == 'L') {
            tail[0] = newHead[0] + 1;
            tail[1] = newHead[1];
        } else if (dir == 'U') {
            tail[0] = newHead[0];
            tail[1] = newHead[1] - 1;
        } else {
            tail[0] = newHead[0];
            tail[1] = newHead[1] + 1;
        }
    }

    static char getDirection(String instruction) {
        return instruction.charAt(0);
    }

    static int getSteps(String instruction) {
        return Integer.parseInt(instruction.substring(2));
    }

    static boolean isInContact(int[] head, int[] tail) {
        return Math.abs(head[0] - tail[0]) <= 1 && Math.abs(head[1] - tail[1]) <= 1;
    }
}

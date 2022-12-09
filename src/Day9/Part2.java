package Day9;

import static Day9.Part1.getDirection;
import static Day9.Part1.getSteps;
import static Day9.Part1.isInContact;
import static Day9.Part1.nextHead;
import static Day9.Part1.nextTail;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Part2 {
    public static int solve() throws FileNotFoundException {
        int numKnots = 10;
        HashSet<String> visited = new HashSet<>();

        int[][] knots = new int[numKnots][2];
        Scanner sc = new Scanner(new File("src/Day9/input"));

        while (sc.hasNextLine()) {
            String instruction = sc.nextLine();
            char dir = getDirection(instruction);
            int steps = getSteps(instruction);
            for (int i = 0; i < steps; i++) {
                nextHead(knots[0], dir); /// head
                for (int j = 1; j < numKnots; j++) {
                    int[] head = knots[j - 1];
                    nextPos(head, knots[j]);
                    if (j == numKnots - 1) { // tail
                        visited.add(Arrays.toString(knots[j]));
                    }
                }
            }
        }
        sc.close();
        return visited.size();
    }

    private static void nextPos(int[] head, int[] knot) {
        if (isInContact(head, knot)) {
            return;
        }

        int headX = head[0];
        int headY = head[1];
        int knotX = knot[0];
        int knotY = knot[1];

        int xDiff = Math.abs(headX - knotX);
        int yDiff = Math.abs(headY - knotY);

        if (yDiff > xDiff) {
            knot[0] = headX;
            if (headY > knotY) {
                knot[1] = headY - 1;
            } else {
                knot[1] = headY + 1;
            }
        } else if (yDiff == xDiff) {
            knot[0] = (knotX + headX) / 2;
            knot[1] = (knotY + headY) / 2;
        } else {
            knot[1] = headY;
            if (headX > knotX) {
                knot[0] = headX - 1;
            } else {
                knot[0] = headX + 1;
            }
        }
    }
}

package Day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {
    static final String INST_ADDX = "addx";
    static final String INST_NOOP = "noop";

    static final int CYCLES_ADDX = 2;
    static final int CYCLES_NOOP = 1;

    static final int UPDATE_CYCLE_FIRST = 20;
    static final int UPDATE_CYCLE_INTERVAL = 40;

    public static int solve() throws FileNotFoundException {
        int cycle = 1;
        int cycleToAdd = 0;
        int toAdd = 0;
        int x = 1;
        int strengthSum = 0;
        Scanner sc = new Scanner(new File("src/Day10/input"));
        while (sc.hasNextLine()) {
            if (!sc.hasNext()) { // eof line
                break;
            }
            String inst = sc.next();
            if (inst.equals(INST_ADDX)) {
                toAdd = sc.nextInt();
                cycleToAdd = cycle + CYCLES_ADDX;
            }

            for (int i = 0; i < getNumCycles(inst); i++) {
                cycle++;
                if (cycle == cycleToAdd) {
                    x += toAdd;
                }
                if (isTargetCycle(cycle)) {
                    strengthSum += x * cycle;
                }
            }
        }
        sc.close();
        return strengthSum;
    }


    static boolean isTargetCycle(int cycle) {
        return (cycle - UPDATE_CYCLE_FIRST) % UPDATE_CYCLE_INTERVAL == 0;
    }

    static int getNumCycles(String inst) {
        if (inst.equals(INST_ADDX)) {
            return CYCLES_ADDX;
        }
        return CYCLES_NOOP;
    }

}

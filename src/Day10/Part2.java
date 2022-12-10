package Day10;

import static Day10.Part1.CYCLES_ADDX;
import static Day10.Part1.INST_ADDX;
import static Day10.Part1.getNumCycles;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {
    private static final int WIDTH = 40;
    private static final String PIXEL_LIT = "#";
    private static final String PIXEL_DARK = ".";

    public static String solve() throws FileNotFoundException {
        StringBuilder wholeImage = new StringBuilder();
        StringBuilder row = new StringBuilder(PIXEL_LIT);
        Scanner sc = new Scanner(new File("src/Day10/input"));
        int pixelPos = 0; // initial pixel position
        int x = 1; // initial sprite position
        int cycle = 1;
        int cycleToAdd = 0;
        int toAdd = 0;

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
                x = updateSpritePos(x, toAdd, cycle, cycleToAdd);
                pixelPos =  updatePixelPos(pixelPos);
                updateRow(row, x, pixelPos);
                updateImage(cycle, row, wholeImage);
            }
        }

        return wholeImage.toString();
    }

    private static void updateRow(StringBuilder row, int spritePos, int pixelPos) {
        if (hasOverlap(spritePos, pixelPos)) {
            row.append(PIXEL_LIT);
        } else {
            row.append(PIXEL_DARK);
        }
    }

    private static boolean hasOverlap(int spritePos, int pixelPos) {
        return pixelPos == spritePos || pixelPos == spritePos - 1 || pixelPos == spritePos + 1;
    }

    private static int updateSpritePos(int x, int toAdd, int cycle, int cycleToAdd) {
        if (cycle == cycleToAdd) {
            return x + toAdd;
        }
        return x;
    }

    private static int updatePixelPos(int pixelPos) {
        return (pixelPos + 1) % WIDTH;
    }

    private static void updateImage(int cycle, StringBuilder row, StringBuilder wholeImage) {
        if (cycle % WIDTH == 0) {
            row.append("\n");
            wholeImage.append(row);
            row.setLength(0);
        }
    }
}

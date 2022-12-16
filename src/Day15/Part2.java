package Day15;

import static Day15.Part1.Coord;
import static Day15.Part1.getDistance;
import static Day15.Part1.parseCoord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Part2 {
    public static long solve() throws FileNotFoundException {
        int min = 0;
        int max = 4000000;
        HashMap<Integer, List<int[]>> lines = new HashMap<>();

        Scanner sc = new Scanner(new File("src/Day15/input"));
        while (sc.hasNextLine()) {
            String[] arr = sc.nextLine().split(": ");
            Coord sensorCoord = parseCoord(arr[0]);
            Coord beaconCoord = parseCoord(arr[1]);

            int d = getDistance(sensorCoord, beaconCoord);

            int minY = Math.max(min, sensorCoord.getY() - d);
            int maxY = Math.min(max, sensorCoord.getY() + d);

            for (int y = minY; y <= maxY; y++) {
                if (!lines.containsKey(y)) {
                    lines.put(y, new ArrayList<>());
                }

                int v = Math.abs(sensorCoord.getY() - y);
                int minX = Math.max(min, sensorCoord.getX() - d + v);
                int maxX = Math.min(max, sensorCoord.getX() + d - v);

                lines.get(y).add(new int[] {minX, maxX});
            }
        }

        long beaconX = 0;
        long beaconY = 0;

        for (int y : lines.keySet()) {
            List<int[]> ls = lines.get(y);
            ls.sort(Comparator.comparingInt(l -> l[0]));
            int x = getMaxX(ls, min);
            if (x != max) {
                beaconX = x + 1;
                beaconY = y;
                break;
            }
        }
        sc.close();
        return beaconX * (long) max + beaconY;
    }

    private static int getMaxX(List<int[]> lines, int min) {
        if (lines.get(0)[0] > min) {
            return lines.get(0)[0];
        }
        int minX = min;
        int maxX = lines.get(0)[1];
        for (int i = 1; i < lines.size(); i++) {
            int[] l = lines.get(i);
            if (l[0] > maxX + 1) {
                return maxX;
            }
            minX = Math.max(minX, lines.get(i)[0]);
            maxX = Math.max(maxX, lines.get(i)[1]);
        }
        return maxX;
    }
}

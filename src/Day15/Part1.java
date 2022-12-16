package Day15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    public static int solve() throws FileNotFoundException {
        int y = 2000000;
        HashSet<Integer> notBeacon = new HashSet<>();
        Scanner sc = new Scanner(new File("src/Day15/input"));
        while (sc.hasNextLine()) {
            String[] arr = sc.nextLine().split(": ");
            Coord sensorCoord = parseCoord(arr[0]);
            Coord beaconCoord = parseCoord(arr[1]);
            int d = getDistance(sensorCoord, beaconCoord);
            if (y > sensorCoord.getY() - d && y < sensorCoord.getY() + d) {
                addNonBeaconCoords(notBeacon, sensorCoord, d, y);
            }
        }
        sc.close();
        return notBeacon.size();
    }

    static int getDistance(Coord sensor, Coord beacon) {
        return Math.abs(sensor.getX() - beacon.getX()) + Math.abs(sensor.getY() - beacon.getY());
    }

    static void addNonBeaconCoords(HashSet<Integer> notBeacon, Coord sensor, int d, int y) {
        int v = Math.abs(sensor.getY() - y);
        int minX = sensor.getX() - d + v;
        int maxX = sensor.getX() + d - v;
        for (int i = minX; i < maxX; i++) {
            notBeacon.add(i);
        }
    }

    static class Coord {
        private int x;
        private int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", x, y);
        }

        @Override
        public int hashCode() {
            return toString().hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Coord)) {
                return false;
            }
            Coord other = (Coord) o;
            return this.x == other.x && this.y == other.y;
        }
    }

     static Coord parseCoord(String str) {
        Pattern p = Pattern.compile("-?[\\d]+");
        Matcher m = p.matcher(str);
        m.find();
        int x = Integer.parseInt(m.group());
        m.find();
        int y = Integer.parseInt(m.group());
        return new Coord(x, y);
    }
}

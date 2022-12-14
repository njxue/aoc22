package Day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Part1 {
    static final int PARTICLE_AIR = 0;
    static final int PARTICLE_ROCK = 1;
    static final int PARTICLE_SAND = 2;

    public static int solve() throws FileNotFoundException {
        Grid grid = generateGrid();
        int[] sandSource = new int[] {0, 500 - grid.minX};
        int i = 0;
        int[] sand = sandSource;
        while (!isInAbyss(grid, sand)) {
            sand = sandSource;
            while (!hasSettled(grid, sand)) {
                sand = fall(grid, sand);
            }
            grid.map[sand[0]][sand[1]] = PARTICLE_SAND;
            i++;
        }
        return i - 1;
    }

    static Grid generateGrid() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("src/Day14/input"));
        // find depth and width
        int depth = 0;
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        while (sc.hasNextLine()) {
            String[] coords = sc.nextLine().split(" -> ");
            for (String coord : coords) {
                String[] xy = coord.split(",");
                depth = Math.max(depth, Integer.parseInt(xy[1]));
                minX = Math.min(minX, Integer.parseInt(xy[0]));
                maxX = Math.max(maxX, Integer.parseInt(xy[0]));
            }
        }

        // initialise grid
        int[][] map = new int[depth + 1][maxX - minX + 1];
        sc = new Scanner(new File("src/Day14/input"));
        while (sc.hasNextLine()) {
            String[] coords = sc.nextLine().split(" -> ");
            for (int i = 0; i < coords.length - 1; i++) {
                String[] start = coords[i].split(",");
                String[] end = coords[i + 1].split(",");
                int startX = Math.min(Integer.parseInt(start[0]), Integer.parseInt(end[0]));
                int endX = Math.max(Integer.parseInt(start[0]), Integer.parseInt(end[0]));
                int startY = Math.min(Integer.parseInt(start[1]), Integer.parseInt(end[1]));
                int endY = Math.max(Integer.parseInt(start[1]), Integer.parseInt(end[1]));
                for (int j = startX; j <= endX; j++) {
                    for (int k = startY; k <= endY; k++) {
                        map[k][j - minX] = PARTICLE_ROCK;
                    }
                }
            }
        }
        sc.close();
        return new Grid(map, minX, maxX, depth);
    }

    static boolean hasSettled(Grid grid, int[] coord) {
        int r = coord[0];
        int c = coord[1];

        if (isInAbyss(grid, coord)) {
            return true;
        }

        if (grid.map[r + 1][c] == PARTICLE_AIR) {
            return false;
        }

        return grid.map[r + 1][c - 1] != PARTICLE_AIR && grid.map[r + 1][c + 1] != PARTICLE_AIR;
    }

    static int[] fall(Grid grid, int[] from) {
        int r = from[0];
        int c = from[1];
        if (grid.map[r + 1][c] == PARTICLE_AIR) {
            return new int[] {r + 1, c};
        }
        if (grid.map[r + 1][c - 1] == PARTICLE_AIR) {
            return new int[] {r + 1, c - 1};
        }
        return new int[] {r + 1, c + 1};
    }

    private static boolean isInAbyss(Grid grid, int[] sand) {
        return sand[1] <= 0 || sand[1] >= grid.width - 1 || sand[0] == grid.depth;
    }

    static class Grid {
        int[][] map;
        int minX;
        int maxX;
        int depth;
        int width;

        public Grid(int[][] map, int minX, int maxX, int depth) {
            this.map = map;
            this.minX = minX;
            this.maxX = maxX;
            this.depth = depth;
            this.width = maxX - minX + 1;
        }

        public void showGrid() {
            for (int i = 0; i < map.length; i++) {
                System.out.println(Arrays.toString(map[i]));
            }
        }
    }
}

package Day14;

import static Day14.Part1.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {

    public static int solve() throws FileNotFoundException {
        Grid grid = generateGrid();
        int[] sandSource = new int[] {0, 500 - grid.minX + grid.depth};
        int i = 0;
        while (true) {
            int[] sand = sandSource;
            while (!hasSettled(grid, sand)) {
                sand = fall(grid, sand);
            }
            i++;
            if (sand[0] == sandSource[0] && sand[1] == sandSource[1]) {
                break;
            }
            grid.map[sand[0]][sand[1]] = PARTICLE_SAND;
        }
        System.out.println(grid.depth);
        return i;
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
        int[][] map = new int[depth + 3][maxX - minX + 2 * depth];
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
                for (int j = startX + depth; j <= endX + depth; j++) {
                    for (int k = startY; k <= endY; k++) {
                        map[k][j - minX] = PARTICLE_ROCK;
                    }
                }
            }
        }

        for (int i = 0; i < maxX - minX + 2 * depth; i++) {
            map[depth + 2][i] = PARTICLE_ROCK;
        }
        sc.close();
        return new Grid(map, minX, maxX, depth);
    }

    static boolean hasSettled(Grid grid, int[] coord) {
        int r = coord[0];
        int c = coord[1];

        if (grid.map[r + 1][c] == PARTICLE_AIR) {
            return false;
        }

        return grid.map[r + 1][c - 1] != PARTICLE_AIR && grid.map[r + 1][c + 1] != PARTICLE_AIR;
    }
}

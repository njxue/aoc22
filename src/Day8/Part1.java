package Day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part1 {
    public static int solve() throws FileNotFoundException {
        List<List<Integer>> grid = generateGrid();
        int rows = grid.size();
        int cols = grid.get(0).size();
        int[][] visited = new int[rows][cols];

        int fromLeft = fromLeft(grid, visited, rows, cols);
        int fromRight = fromRight(grid, visited, rows, cols);
        int fromTop = fromTop(grid, visited, rows, cols);
        int fromBottom = fromBottom(grid, visited, rows, cols);
        return fromLeft + fromRight + fromTop + fromBottom;
    }

    static List<List<Integer>> generateGrid() throws FileNotFoundException {
        List<List<Integer>> grid = new ArrayList<>();
        Scanner sc = new Scanner(new File("src/Day8/input"));
        while (sc.hasNextLine()) {
            List<Integer> row = new ArrayList<>();
            String str = sc.nextLine();
            for (int i = 0; i < str.length(); i++) {
                row.add(Integer.parseInt(String.valueOf(str.charAt(i))));
            }
            grid.add(row);
        }
        return grid;
    }

    static int fromLeft(List<List<Integer>> grid, int[][] visited, int rows, int cols) {
        int count = 0;
        for (int r = 0; r < rows; r++) {
            int rowMax = Integer.MIN_VALUE;
            for (int c = 0; c < cols; c++) {
                int height = grid.get(r).get(c);
                if (visited[r][c] == 0 && height > rowMax) {
                    count++;
                    visited[r][c] = 1;
                }
                rowMax = Math.max(rowMax, height);
            }
        }
        return count;
    }

    static int fromRight(List<List<Integer>> grid, int[][] visited, int rows, int cols) {
        int count = 0;
        for (int r = 0; r < rows; r++) {
            int rowMax = Integer.MIN_VALUE;
            for (int c = cols - 1; c >= 0; c--) {
                int height = grid.get(r).get(c);
                if (visited[r][c] == 0 && height > rowMax) {
                    count++;
                    visited[r][c] = 1;
                }
                rowMax = Math.max(rowMax, height);
            }
        }
        return count;
    }

    static int fromTop(List<List<Integer>> grid, int[][] visited, int rows, int cols) {
        int count = 0;
        for (int c = 0; c < cols; c++) {
            int colMax = Integer.MIN_VALUE;
            for (int r = 0; r < rows; r++) {
                int height = grid.get(r).get(c);
                if (visited[r][c] == 0 && height > colMax) {
                    count++;
                    visited[r][c] = 1;
                }
                colMax = Math.max(colMax, height);
            }
        }
        return count;
    }

    static int fromBottom(List<List<Integer>> grid, int[][] visited, int rows, int cols) {
        int count = 0;
        for (int c = 0; c < cols; c++) {
            int colMax = Integer.MIN_VALUE;
            for (int r = rows - 1; r >= 0; r--) {
                int height = grid.get(r).get(c);
                if (visited[r][c] == 0 && height > colMax) {
                    count++;
                    visited[r][c] = 1;
                }
                colMax = Math.max(colMax, height);
            }
        }
        return count;
    }
}

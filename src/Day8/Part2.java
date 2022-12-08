package Day8;

import static Day8.Part1.generateGrid;

import java.io.FileNotFoundException;

import java.util.List;

public class Part2 {
    public static int solve() throws FileNotFoundException {
        List<List<Integer>> grid = generateGrid();
        int rows = grid.size();
        int cols = grid.get(0).size();
        int maxScore = 0;

        for (int r = 1; r < rows - 1; r++) {
            for (int c = 1; c < cols - 1; c++) {
                int score = calculateScore(grid, r, c, rows, cols);
                maxScore = Math.max(score, maxScore);
            }
        }
        return maxScore;

    }

    private static int calculateScore(List<List<Integer>> grid, int r, int c, int rows, int cols) {
        int h = grid.get(r).get(c);

        int l = c - 1;
        int rt = c + 1;
        int t = r - 1;
        int b = r + 1;

        // left;
        int left = 1;
        while (l >= 0 && h > grid.get(r).get(l) && !isEdge(r, l, rows, cols)) {
            left++;
            l--;
        }

        // right score
        int right = 1;
        while (rt < cols && h > grid.get(r).get(rt) && !isEdge(r, rt, rows, cols)) {
            right++;
            rt++;
        }

        // top score
        int top = 1;
        while (t >= 0 && h > grid.get(t).get(c) && !isEdge(t, c, rows, cols)) {
            top++;
            t--;
        }

        // bottom score
        int bottom = 1;
        while (b < rows && h > grid.get(b).get(c) && !isEdge(b, c, rows, cols)) {
            bottom++;
            b++;
        }
        return left * right * top * bottom;
    }

    private static boolean isEdge(int r, int c, int rows, int cols) {
        return r * c == 0 || r == rows - 1 || c == cols - 1;
    }
}

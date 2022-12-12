package Day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Part2 {

    public static int solve() throws FileNotFoundException {
        Grid grid = generateGrid();
        Node n = bfs(grid);
        int length = -1;
        while (n != null) {
            length++;
            n = n.parent;
        }
        return length;
    }

    private static Node bfs(Grid grid) {
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> visited = new HashSet<>();
        queue.add(grid.src);
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            visited.add(n);
            for (Node nb : grid.getNeighbours(n)) {
                if (nb.val == 'a') {
                    nb.parent = n;
                    return nb;
                }
                if (!visited.contains(nb)) {
                    visited.add(nb);
                    queue.add(nb);
                    nb.setParent(n);
                }
            }
        }
        return null;
    }

    private static Grid generateGrid() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("src/Day12/input"));
        List<List<Node>> grid = new ArrayList<>();
        Node src = null;
        Node dest = null;
        int r = 0;
        while (sc.hasNextLine()) {
            List<Node> row = new ArrayList<>();
            String s = sc.nextLine();
            for (int c = 0; c < s.length(); c++) {
                Node n = new Node(s.charAt(c), r, c);
                row.add(n);
                if (n.val == 'E') {
                    n.val = 'z';
                    src = n;
                }
            }
            grid.add(row);
            r++;
        }
        return new Grid(grid, src);
    }


    static class Node {
        int r;
        int c;
        char val;
        Node parent;

        public Node(char val, int r, int c) {
            this.r = r;
            this.c = c;
            this.val = val;
        }

        public void setParent(Node p) {
            parent = p;
        }

        @Override
        public String toString() {
            return String.format("(%s, %s)", r, c);
        }
    }

    static class Grid {
        List<List<Node>> nodes;
        int height;
        int width;
        Node src;

        public Grid(List<List<Node>> nodes, Node src) {
            this.nodes = nodes;
            this.src = src;
            this.height = nodes.size();
            this.width = nodes.get(0).size();
        }

        public List<Node> getNeighbours(Node n) {
            List<Node> nbs = new ArrayList<>();
            if (n.r - 1 >= 0) {
                nbs.add(nodes.get(n.r - 1).get(n.c));
            }

            if (n.r + 1 < height) {
                nbs.add(nodes.get(n.r + 1).get(n.c));
            }

            if (n.c - 1 >= 0) {
                nbs.add(nodes.get(n.r).get(n.c - 1));
            }

            if (n.c + 1 < width) {
                nbs.add(nodes.get(n.r).get(n.c + 1));
            }

            return nbs.stream().filter(nb -> n.val - nb.val <= 1).collect(Collectors.toList());
        }
    }
}

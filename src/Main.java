public class Main {
    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            System.out.println(Day15.Part2.solve());
            long end = System.currentTimeMillis();
            System.out.println(String.format("Execution time: %ss", (end - start) / 1000));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

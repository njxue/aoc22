import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(Day5.Part2.solve());
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found");
        }
    }
}

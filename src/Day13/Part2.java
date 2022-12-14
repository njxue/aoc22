package Day13;
import static Day13.Part1.compare;
import static Day13.Part1.parseList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Part2 {
    private static final String DIVIDER_2 = "[[2]]";
    private static final String DIVIDER_6 = "[[6]]";

    public static int solve() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("src/Day13/input"));
        List<Object> dividerTwo = parseList(DIVIDER_2);
        List<Object> dividerSix = parseList(DIVIDER_6);
        int beforeTwo = 0;
        int afterSix = 0;
        int totalPackets = 2; // inclusive of dividers
        while (sc.hasNextLine()) {
            String packetString = sc.nextLine();
            if (packetString.isEmpty()) {
                continue;
            }
            List<Object> packet = parseList(packetString);
            totalPackets++;
            if (compare(packet, dividerTwo) < 0) { // if packet comes before [[2]]
                beforeTwo++;
            } else if (compare(packet, dividerSix) > 0) { // if packet comes after [[6]]
                afterSix++;
            }
        }
        return (beforeTwo + 1) * (totalPackets - afterSix);
    }
}

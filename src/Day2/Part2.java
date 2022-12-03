package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Part2 {
    private static final int SCORE_WIN = 6;
    private static final int SCORE_DRAW = 3;
    private static final int SCORE_LOSE = 0;

    private static final int SCORE_ROCK = 1;
    private static final int SCORE_PAPER = 2;
    private static final int SCORE_SCISSORS = 3;

    public static int solve() throws FileNotFoundException {
        HashMap<Character, Integer> handMap = new HashMap<>();
        handMap.put('A', SCORE_ROCK);
        handMap.put('B', SCORE_PAPER);
        handMap.put('C', SCORE_SCISSORS);

        HashMap<Character, Integer> outcomeMap = new HashMap<>();
        outcomeMap.put('X', SCORE_LOSE);
        outcomeMap.put('Y', SCORE_DRAW);
        outcomeMap.put('Z', SCORE_WIN);

        int totalScore = 0;
        File file = new File("src/Day2/input");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String round = sc.nextLine();
            assert round.length() == 3;
            char opponent = round.charAt(0);
            char player = round.charAt(2);
            totalScore += getRoundScore(opponent, player, outcomeMap, handMap);
        }
        return totalScore;
    }

    private static int getRoundScore(char opponent, char outcome, HashMap<Character, Integer> outcomeMap,
                                     HashMap<Character, Integer> handMap) {
        int outcomeScore = outcomeMap.get(outcome);
        int handScore = handMap.get(getRequiredHand(opponent, outcome));
        return outcomeScore + handScore;
    }

    private static char getRequiredHand(char opponent, char outcome) {
        if (outcome == 'X') {
            return getLosingHand(opponent);
        } else if (outcome == 'Z') {
            return getWinningHand(opponent);
        } else {
            return opponent;
        }
    }

    private static char getLosingHand(char opponent) {
        if (opponent == 'A') {
            return 'C';
        } else if (opponent == 'B') {
            return 'A';
        }
        return 'B';
    }

    private static char getWinningHand(char opponent) {
        if (opponent == 'A') {
            return 'B';
        } else if (opponent == 'B') {
            return 'C';
        }
        return 'A';
    }
}

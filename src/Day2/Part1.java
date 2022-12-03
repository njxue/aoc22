package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Part1 {
    private static final int SCORE_WIN = 6;
    private static final int SCORE_DRAW = 3;
    private static final int SCORE_LOSE = 0;

    private static final int SCORE_ROCK = 1;
    private static final int SCORE_PAPER = 2;
    private static final int SCORE_SCISSORS = 3;

    public static int solve() throws FileNotFoundException {
        HashMap<Character, Integer> handMap = new HashMap<>();
        handMap.put('X', SCORE_ROCK);
        handMap.put('Y', SCORE_PAPER);
        handMap.put('Z', SCORE_SCISSORS);

        int totalScore = 0;
        File file = new File("src/Day2/input");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String round = sc.nextLine();
            assert round.length() == 3;
            char opponent = round.charAt(0);
            char player = round.charAt(2);
            totalScore += getRoundScore(opponent, player, handMap);
        }
        return totalScore;
    }


    private static int getRoundScore(char opponent, char player, HashMap<Character, Integer> handMap) {
        int handScore = handMap.get(player);
        int outcomeScore = getOutcomeScore(opponent, player);
        return handScore + outcomeScore;
    }

    private static boolean isDraw(char opponent, char player) {
        return opponent == 'A' && player == 'X'
                || opponent == 'B' && player == 'Y'
                || opponent == 'C' && player == 'Z';
    }

    private static boolean isWin(char opponent, char player) {
        return opponent == 'A' && player == 'Y'
                || opponent == 'B' && player == 'Z'
                || opponent == 'C' && player == 'X';
    }

    private static int getOutcomeScore(char opponent, char player) {
        if (isWin(opponent, player)) {
            return SCORE_WIN;
        } else if (isDraw(opponent, player)) {
            return SCORE_DRAW;
        } else {
            return SCORE_LOSE;
        }
    }
}

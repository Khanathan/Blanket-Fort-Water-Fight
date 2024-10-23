package UI;

import Model.FortMap;
import Model.OpponentsPointsManager;
import Utils.Utility;

import java.util.List;
import java.util.Scanner;

/**
 * Responsible for all user interactions including printing on screen and getting input from the keyboard.
 * Communicates with backend components (FortMap, OpponentsPointsManager) which are passed in at creation.
 * Game flow and user input validation are handled in the frontend.
 */
public class GameUI {
    private final FortMap fortMap;
    private final OpponentsPointsManager pointsManager;
    private final boolean cheatMode;
    private static final String WINNING_MESSAGE = "Congratulations! You won!";
    private static final String LOSING_MESSAGE = "I'm sorry, your fort is all wet! They win!";

    public GameUI(FortMap fortMap, OpponentsPointsManager pointsManager, boolean cheatMode) {
        this.fortMap = fortMap;
        this.pointsManager = pointsManager;
        this.cheatMode = cheatMode;
    }
    public void start() {
        if(cheatMode) {
            printVisibleGameState();
        }
        printWelcomeMessage();
        while(true) {
            printFortMap(fortMap.getCurrentStateMap());
            printOpponentsPoints();
            if(pointsManager.finished()) {
                break;
            }
            String nextMove = promptNextMove();
            boolean result = pointsManager.calculateResult(nextMove, fortMap);
            printResults(result);
        }
        System.out.println(pointsManager.defeatedAll() ? WINNING_MESSAGE : LOSING_MESSAGE);
        printVisibleGameState();
    }
    private void printVisibleGameState() {
        printFortMap(fortMap.getRevealedMap());
        printOpponentsPoints();
        System.out.println("(Lower case fort letters are where you shot.)");
    }
    private void printWelcomeMessage() {
        System.out.println("\n\nStarting game with " + pointsManager.opponentCount + " forts.");
        System.out.println("----------------------------");
        System.out.println("Welcome to Fort Defense!    ");
        System.out.println("by Risa Kawagoe & Khanh Doan");
        System.out.println("----------------------------\n");
    }
    private void printFortMap(List<List<Character>> fortMap) {
        System.out.println("\nGame Board:");
        System.out.print("     ");
        assert !fortMap.isEmpty();
        // print table column heading
        for(int colIdx = 0; colIdx < fortMap.get(0).size(); colIdx++) {
            System.out.printf("%3d", (colIdx + 1));
        }
        System.out.println();
        // print table body
        for(int rowIdx = 0; rowIdx < fortMap.size(); rowIdx ++) {
            List<Character> rowMap = fortMap.get(rowIdx);
            System.out.printf("    %c", Utility.intToAlpha(rowIdx + 1));
            for(Character field : rowMap) {
                System.out.printf("%3c", field);
            }
            System.out.println();
        }
    }
    private void printOpponentsPoints() {
        System.out.printf("Opponents points: %d / %d.\n",
                pointsManager.getTotalPoints(),
                pointsManager.REQUIRED_POINTS_TO_WIN);
    }
    private String promptNextMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your move: ");
        String nextMove = scanner.nextLine().strip();
        while (!isValidMove(nextMove)) {
            System.out.println("Re-enter a valid move in a valid format (<Letter><Number>):");
            System.out.print("Enter your move: ");
            nextMove = scanner.nextLine().strip();
        }
        return nextMove;
    }
    private boolean isValidMove(String moveStr) {
        if (moveStr.length() < 2 || moveStr.length() > 3) {
            return false;
        }
        try {
            if (!Character.isAlphabetic(moveStr.charAt(0)))
                throw new RuntimeException();
            int row = Utility.alphaToInt(moveStr.charAt(0)) - 1;
            int col = Integer.parseInt(moveStr.substring(1)) - 1;
            if (!validBounds(row, col)) {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            return false;
        }
        return true;
    }
    private boolean validBounds(int row, int col) {
        boolean validRow = 0 <= row && row < fortMap.MAP_ROWS;
        boolean validCol = 0 <= col && col < fortMap.MAP_COLUMNS;
        return validRow && validCol;
    }
    private void printResults(boolean hit) {
        System.out.println(hit ? "HIT!" : "Miss.");
        List<Integer> remainingOpponents = pointsManager.getOpponentsPoints();
        for(int i = 0; i < remainingOpponents.size(); i++) {
            System.out.printf("Opponent #%d of %d shot you for %d points!\n",
                    (i + 1),
                    remainingOpponents.size(),
                    remainingOpponents.get(i));
        }
    }
}
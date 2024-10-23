package GameBoardAndCellRepresentation;

import java.util.List;

public class GameUI {
    private boolean cheating = true; // true for debug purposes
    public GameUI() {
        // if --cheat provided: set cheating = true
    }
    public void printGameBoard(GameBoard board) {
        System.out.println("\nGame Board:");
        System.out.print("     ");
        for(int col = 0; col < board.cols; col++) {
            System.out.printf("%3d", (col + 1));
        }
        System.out.println();
        for (List<Cell> row : board.getBoard()) {
            int rowNum = board.getBoard().indexOf(row);
            System.out.printf("    %c", board.rowToAlpha(rowNum));
            for (Cell cell : row) {
                System.out.printf("%3c", cell.getFortId(cheating));
            }
            System.out.println();
        }
    }
}

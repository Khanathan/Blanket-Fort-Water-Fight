package GameBoardAndCellRepresentation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameBoard {
    public final int rows;
    public final int cols;
    private final List<List<Cell>> board = new ArrayList<>();

    public GameBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        for(int row = 0; row < rows; row ++) {
            List<Cell> newRow = new ArrayList<>();
            for(int col = 0; col < cols; col++) {
                newRow.add(new Cell());
            }
            board.add(newRow);
        }
    }
    public List<List<Cell>> getBoard() {
        return Collections.unmodifiableList(board);
    }
    public void setOccupancy(String location, char fortId) {
        // TODO responsibility of checking if location is valid is promptNextMove
        String tmp = location.toUpperCase();

        // debug
        int rowIndex = alphaToRow(tmp.charAt(0));
        int colIndex = Character.getNumericValue(tmp.charAt(1)) - 1;
        System.out.printf("Placing fort block for team %c: %s (%d, %d)\n", fortId, tmp, rowIndex, colIndex);
        board.get(rowIndex).get(colIndex).assignTo(fortId);
    }


    public char rowToAlpha(int row) {
        assert row >= 0 && row < rows;
        int ASCII_DIFFERENCE = 65;
        return (char) (row + ASCII_DIFFERENCE);
    }
    public int alphaToRow(char alpha) {
        int ASCII_DIFFERENCE = 65;
        return alpha - ASCII_DIFFERENCE;
    }
}

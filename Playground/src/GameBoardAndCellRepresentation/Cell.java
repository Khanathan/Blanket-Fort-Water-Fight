package GameBoardAndCellRepresentation;

public class Cell {
    private final char UNOCCUPIED_SYMBOL_CHEAT = '.';
    private final char UNOCCUPIED_SYMBOL = ' ';
    private final char OCCUPIED_SYMBOL = 'X';
    private final char HIDDEN_SYMBOL = '~';
    private char fortId = UNOCCUPIED_SYMBOL_CHEAT;
    private boolean revealed = false;
    public void assignTo(char fortId) {
        this.fortId = fortId;
    }
    public char getFortId(boolean cheating) {
        if(cheating) {
            return fortId;
        }
        if(revealed) {
            if(fortId == UNOCCUPIED_SYMBOL_CHEAT) {
                return UNOCCUPIED_SYMBOL;
            }else {
                return OCCUPIED_SYMBOL;
            }
        }else{
            return HIDDEN_SYMBOL;
        }
    }
    public boolean isUnoccupied() {
        return fortId == UNOCCUPIED_SYMBOL_CHEAT;
    }
    public void reveal() {
        revealed = true;
    }
    public boolean isRevealed() {
        return revealed;
    }
}

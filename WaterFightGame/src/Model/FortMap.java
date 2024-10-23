package Model;

import Utils.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores fields in the map as a 2D list and updates fields when given field gets hit.
 * Provides different printable representation depending on the field visibility preference.
 */
public class FortMap {
    public final int MAP_ROWS;
    public final int MAP_COLUMNS;
    private final List<List<Field>> map;
    private final char UNOCCUPIED_SYMBOL_VISIBLE = '.';
    private final char UNOCCUPIED_SYMBOL = ' ';
    private final char OCCUPIED_SYMBOL = 'X';
    private final char HIDDEN_SYMBOL = '~';

    public FortMap(int rows, int cols, int opponentCount, int fortSize) {
        // create blank map
        List<List<Field>> fortMap = new ArrayList<>();
        for(int i = 0; i < rows; i ++) {
            List<Field> row = new ArrayList<>();
            for(int j = 0; j < cols; j++) {
                row.add(new Field());
            }
            fortMap.add(row);
        }
        // place forts on the blank map
        FortMapBuilder fortMapBuilder = new FortMapBuilder();
        fortMapBuilder.build(fortMap, opponentCount, fortSize);

        this.map = fortMap;
        this.MAP_ROWS = map.size();
        this.MAP_COLUMNS = map.get(0).size();
    }
    public boolean hit(int rowIdx, int colIdx) {
        return map.get(rowIdx).get(colIdx).hit();
    }
    public boolean fieldIsHit(int rowIdx, int colIdx) {
        return map.get(rowIdx).get(colIdx).isHit();
    }
    public int getFieldFortId(int rowIdx, int colIdx) {
        return map.get(rowIdx).get(colIdx).getFortId();
    }
    public List<List<Character>> getCurrentStateMap() {
        return getMapRepresentation(false);
    }
    public List<List<Character>> getRevealedMap() {
        return getMapRepresentation(true);
    }
    private List<List<Character>> getMapRepresentation(boolean visible) {
        List<List<Character>> fortMap = new ArrayList<>();
        for(int rowIdx = 0; rowIdx < MAP_ROWS; rowIdx ++) {
            List<Character> rowMap = new ArrayList<>();
            for(int colIdx = 0; colIdx < MAP_COLUMNS; colIdx++) {
                rowMap.add(getFieldDisplaySymbol(rowIdx, colIdx, visible));
            }
            fortMap.add(rowMap);
        }
        return fortMap;
    }
    private char getFieldDisplaySymbol(int rowIdx, int colIdx, boolean visible) {
        Field field = map.get(rowIdx).get(colIdx);
        int fortId = field.getFortId();
        char charFortId = HIDDEN_SYMBOL;
        if(visible) {
            if(fortId == 0) {
                charFortId = UNOCCUPIED_SYMBOL_VISIBLE;
            }else {
                charFortId = Utility.intToAlpha(fortId);
                if(field.isHit()) {
                    charFortId = Character.toLowerCase(charFortId);
                }
            }
        }else if(field.isHit()) {
            charFortId = fortId == 0 ? UNOCCUPIED_SYMBOL : OCCUPIED_SYMBOL;
        }
        return charFortId;
    }
}
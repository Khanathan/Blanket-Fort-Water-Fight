package Model;

import java.util.Random;

public class Board {
     private final char[][] map;
     private final char[][] userMap;
     public final int size;
     private char currentFortSymbol;
     private static final int x = 0;
     private static final int y = 1;
     public Board(int boardSize) {
          size = boardSize;
          currentFortSymbol = 'A';

          //initialize board to default
          map = new char[size][size];
          userMap = new char[size][size];
          initMap(map, size);
          initMap(userMap, size);
     }

     public static void initMap (char[][] map, int boardSize) {
          for (int x = 0; x < boardSize; x++) {
               for (int y = 0; y < boardSize; y++) {
                    map[x][y] = '~';
               }
          }
     }

     public char getCell (int row, int col) {
          return map[row][col];
     }

     public void setCell(int row, int col, char newValue) {
          map[row][col] = newValue;
     }

     //the parameter index is 1-based
     public boolean isValidPlacement(FortShape fort, int row, int col) {
          if (map[row][col] != '~')
               return false;


          final int x = 0;
          final int y = 1;
          for (int[] coords : fort) {
               int tempR = row + coords[x];
               int tempC = col + coords[y];

               boolean rowIndexInvalid = (tempR >= size || tempR < 0);
               boolean columnIndexInvalid = (tempC >= size || tempC < 0);
               boolean cellOccupied = (map[tempR][tempC] != '~');

               if (rowIndexInvalid || columnIndexInvalid || cellOccupied)
                    return false;
          }

          //iterated through the list of shape, and it's all good
          return true;
     }

     //randomly places a fort
     public void placeAFort() {
          //attempts size*size times (arbitrary amount of attempts)
          int maxAttempts = size * size;
          FortShape currentShape;
          Random rnd = new Random();
          int startRow;
          int startCol;

          //try until find a valid shape and coords for placement
          for (int attemptCount = 0; attemptCount < maxAttempts; attemptCount++){
               currentShape = new FortShape();
               startRow = rnd.nextInt(0, size);
               startCol = rnd.nextInt(0, size);

               //setFort onto board
               if (isValidPlacement(currentShape, startRow, startCol)) {
                    setFort(currentShape, startRow, startCol);
                    break;
               }
          }
     }

     public void setFort(FortShape fort, int startRow, int startCol) {
          setCell(startRow, startCol, currentFortSymbol);
          for (int[] coords : fort) {
               setCell(startRow + coords[x], startCol + coords[y], currentFortSymbol);
          }

          //increment fort symbol/char to the next char in the alphabet.
          currentFortSymbol += 1;
     }
}

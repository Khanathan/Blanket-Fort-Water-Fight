package View;

import Model.Board;

public class GameUI {
     private final Board board;

     public GameUI(Board board) {
          this.board = board;
     }

     public void renderBoard() {
          System.out.println(renderHorizontalBar());
          for (int rowIndex = 0; rowIndex < board.size; rowIndex++) {
               System.out.println(renderRow(rowIndex));
          }
          System.out.println(renderHorizontalBar());
     }

     public String renderHorizontalBar() {
          String bar = "+";
          for (int i = 0; i < board.size; i++) {
               bar += "--";
          }
          bar += "-+";
          return bar;
     }

     public String renderRow (int rowIndex) {
          String row = "| ";
          for (int colIndex = 0; colIndex < board.size; colIndex++) {
               row += board.getCell(rowIndex, colIndex) + " ";
          }
          row += '|';
          return row;
     }

     private static void clear() {
          try {
               if (System.getProperty("os.name").contains("Windows"))
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
               else
                    Runtime.getRuntime().exec("clear");
          } catch (Exception e) {
               System.out.println(e);
          }
     }
}

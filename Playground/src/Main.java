import GameBoardAndCellRepresentation.GameBoard;
import GameBoardAndCellRepresentation.GameUI;
import FortBuilder.FortBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        System.out.println("*******************************************************");
        System.out.println("* Playground for experimenting and testing prototype. *");
        System.out.println("*******************************************************");
        System.out.println("Create new package at the same level as Main.java");
        System.out.println("when experimenting new features.");
        System.out.println("Place driver code of each package in functions named by package name.\n\n\n");

        // Package driver codes:
//        GameBoardAndCellRepresentation();
//        KhanhDoanPrototype();
        FortBuilder();
    }
    private static void GameBoardAndCellRepresentation() {
        System.out.println("*******************************************************");
        GameBoard board = new GameBoard(10, 10);
        GameUI gameUI = new GameUI();
        board.setOccupancy("b2", 'A');
        board.setOccupancy("b3", 'A');
        board.setOccupancy("c3", 'A');
        board.setOccupancy("c4", 'A');
        board.setOccupancy("d3", 'A');

        board.setOccupancy("g4", 'B');
        board.setOccupancy("g5", 'B');
        board.setOccupancy("g6", 'B');
        board.setOccupancy("h6", 'B');
        board.setOccupancy("i6", 'B');
        gameUI.printGameBoard(board);
    }
    private static void KhanhDoanPrototype() {
        System.out.println("*******************************************************");
        Model.Board board = new Model.Board(10);
        View.GameUI game = new View.GameUI(board);

        Scanner scan = new Scanner(System.in);
        System.out.println("Hello. Here is your board:");
        game.renderBoard();

        System.out.println("Placed a fort: ");
        int numOfForts = 4;
        for (int i = 0; i < numOfForts; i++) {
            board.placeAFort();
            game.renderBoard();
        }
    }
    private static void FortBuilder() {
        System.out.println("*******************************************************");
        int MAP_ROWS = 10;
        int MAP_COLUMNS = 20;
        int FORT_COUNT = 10;
        int FORT_SIZE = 5;
        // create blank map
        List<List<Integer>> map = new ArrayList<>();
        for(int i = 0; i < MAP_ROWS; i++) {
            List<Integer> newRow = new ArrayList<>();
            for(int j = 0; j < MAP_COLUMNS; j++) {
                newRow.add(0);
            }
            map.add(newRow);
        }
        FortBuilder fortBuilder = new FortBuilder();
        try {
            fortBuilder.build(map, FORT_COUNT, FORT_SIZE);
            fortBuilder.testPrintMap();
        }catch (RuntimeException e) {
            fortBuilder.testPrintMap();
            System.out.println(e.getMessage());
        }
    }
}
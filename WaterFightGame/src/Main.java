import Model.FortMap;
import Model.OpponentsPointsManager;
import UI.GameUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Driver code for starting the game UI
 */
public class Main {
    public static void main(String[] args) {
        final int DEFAULT_OPPONENT_COUNT = 5;
        final int MAP_ROWS = 10;
        final int MAP_COLUMNS = 10;

        int opponentCount = args.length > 0 ? Integer.parseInt(args[0]) : DEFAULT_OPPONENT_COUNT;
        boolean cheatMode = args.length > 1;

        try {
            List<Integer> pointSystem = new ArrayList<>(Arrays.asList(0, 1, 2, 5, 20, 20));
            OpponentsPointsManager pointsManager = new OpponentsPointsManager(pointSystem, opponentCount);
            FortMap fortMap = new FortMap(MAP_ROWS, MAP_COLUMNS, pointsManager.opponentCount, pointSystem.size() - 1);
            GameUI gameUI = new GameUI(fortMap, pointsManager, cheatMode);
            gameUI.start();
        }catch (RuntimeException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
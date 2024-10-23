package FortBuilder;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a location in map, will randomly place a (single) fort if safe to do so and returns true,
 * otherwise does not modify the matrix and returns false
 * "safe" means there is enough connected unoccupied field to span out
 * IMPORTANT: testing with Integers instead of Field objects
 */
public class FortBuilder {
    private List<List<Integer>> map = new ArrayList<>();
    private int fortCount = 0;
    private int fortSize = 0;

    private List<int[]> options = new ArrayList<>();
    private int fieldCount = 0;

    public void build(List<List<Integer>> map, int opponentCount, int size) {
        if(map.isEmpty() || map.get(0).isEmpty() || map.size() * map.get(0).size() < opponentCount * size) {
            throw new RuntimeException("Error: Map ia not big enough.\n" +
                    "       Try running game again with fewer forts or a larger board.");
        }
        setVariables(map, size);
        while(fortCount < opponentCount) {
            if(findMaxCapacity() < size) {
                throw new RuntimeException("Error: Unable to place " + opponentCount + " forts on the board.\n" +
                        "       Try running game again with fewer forts.");
            }
            Random random = new Random();
            int[] randomField = new int[] {random.nextInt(map.size()), random.nextInt(map.get(0).size())};
            int nextFortId = fortCount + 1;
            boolean success = placeFort(randomField, nextFortId);
            if(success) {
                fortCount++;
            }
        }
    }
    private void setVariables(List<List<Integer>> map, int fortSize) {
        this.map = map;
        this.fortSize = fortSize;
        this.fortCount = 0;
    }
    private boolean placeFort(int[] location, int fortId) {
        if(canBeVisited(location)) {
            boolean[][] visited = new boolean[map.size()][map.get(0).size()];
            if(findAreaCapacity(location, visited) >= fortSize) {
                buildFort(location, fortId);
                return true;
            }
        }
        return false;
    }
    private boolean canBeVisited(int[] location) {
        return isValidRowAndColumn(location[0], location[1]) && map.get(location[0]).get(location[1]).equals(0);
    }

    private int findMaxCapacity() {
        boolean[][] visited = new boolean[map.size()][map.get(0).size()];
        int maxCapacity = 0;
        for(int i = 0; i < map.size(); i++) {
            for(int j = 0; j < map.get(0).size(); j++) {
                maxCapacity = Math.max(findAreaCapacity(new int[] {i, j}, visited), maxCapacity);
            }
        }
        return maxCapacity;
    }

    private void buildFort(int[] field, int fortId) {
        options.clear();
        fieldCount = 0;
        addFieldToFort(field, fortId);

        while(fieldCount < fortSize) {
            int[] randomField = options.get(new Random().nextInt(options.size()));
            List<int[]> availableNeighbors = getUnoccupiedNeighbors(randomField);
            assert !availableNeighbors.isEmpty();
            int[] randomNeighbor = availableNeighbors.get(new Random().nextInt(availableNeighbors.size()));

            addFieldToFort(randomNeighbor, fortId);
        }
    }
    private void addFieldToFort(int[] field, int fortId) {
        map.get(field[0]).set(field[1], fortId);
        options.add(field);
        options = options.stream()
                .filter(option -> !getUnoccupiedNeighbors(option).isEmpty())
                .collect(Collectors.toList());
        fieldCount++;
    }
    private List<int[]> getUnoccupiedNeighbors(int[] location) {
        int row = location[0];
        int col = location[1];
        List<int[]> unoccupiedNeighbors = new ArrayList<>();
        List<int[]> neighbors = Arrays.asList(
                new int[]{1, 0},
                new int[]{0, 1},
                new int[]{-1, 0},
                new int[]{0, -1}
        );
        Collections.shuffle(neighbors);
        for(int[] neighbor : neighbors) {
            if(canBeVisited( new int[] {row + neighbor[0], col + neighbor[1]})) {
                unoccupiedNeighbors.add(new int[] {row + neighbor[0], col + neighbor[1]});
            }
        }
        return unoccupiedNeighbors;
    }

    // adapted DFS implementation from https://www.geeksforgeeks.org/find-length-largest-region-boolean-matrix/
    private int findAreaCapacity(int[] location, boolean[][] visited) {
        if(!canBeVisited(location) || visited[location[0]][location[1]]) {
            return 0;
        }
        int row = location[0];
        int col = location[1];
        int capacity = 1;
        List<int[]> neighbors = Arrays.asList(
                new int[]{1, 0},
                new int[]{0, 1},
                new int[]{-1, 0},
                new int[]{0, -1}
        );
        visited[row][col] = true;
        for(int[] neighbor : neighbors) {
            int[] neighborLocation = new int[] {row + neighbor[0], col + neighbor[1]};
            capacity += findAreaCapacity(neighborLocation, visited);
        }
        return capacity;
    }

    private boolean isValidRowAndColumn(int row, int col) {
        return !map.isEmpty()
                && 0 <= row && row < map.size()
                && 0 <= col && col < map.get(0).size();
    }

    public void testPrintMap() {
        if(map.isEmpty()) {
            return;
        }
        System.out.printf("\n\nTEST PRINT FORT MAP [%d x %d] ***********************\n",
                map.size(), map.get(0).size());
        System.out.printf("config: %d forts of size %d\n\n", fortCount, fortSize);
        for (List<Integer> field : map) {
            for (int j = 0; j < map.get(0).size(); j++) {
                System.out.printf("  %c", alphaFortId(field.get(j)));
            }
            System.out.println();
        }
    }
    private char alphaFortId(Integer fortId) {
        return fortId.equals(0) ? '.' : (char) (fortId + 64);
    }
}
package Model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class FortShape implements Iterable<int[]>{
     private static final int[][][] shapeLibrary =
               {{{0, 1}, {0, 2}},
                         {{0,1}, {1,0}}};

     protected List<int[]> shape;

     public FortShape() {
          //randomly choosing a fort shape
          int[][] randomShape = shapeLibrary[(new Random()).nextInt(2)];
          shape = Arrays.asList(randomShape);
     }

     public Iterator<int[]> iterator() {
          return shape.iterator();
     }
}

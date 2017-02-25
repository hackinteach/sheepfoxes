import java.util.ArrayList;
import java.util.List;

/**
 * Created by HackinteachK on 2/24/17.
 */
public class TestSheepGrass {

    public static void main(String[] args) {
        Location l1 = new Location(2, 1);
        Field fi1 = new Field(5, 5);
        Sheep s1 = new Sheep(fi1,l1);
        List<Entity> entList = new ArrayList<>();

        /* Test eat grass */
        Location l2 = new Location(2,2);
        GrassTufts g1 = new GrassTufts(fi1,l2);
        Location l3 = new Location(2,0);
        GrassTufts g2 = new GrassTufts(fi1,l3);
        Location l4 = new Location(1,1);
        GrassTufts g3 = new GrassTufts(fi1,l4);
        Location l5 = new Location(3,1);
        GrassTufts g4 = new GrassTufts(fi1,l5);

        s1.act(entList);
        System.out.println(s1.getField().getFreeAdjacentLocations(l1,1));
        System.out.println(g1.isAlive());
        System.out.println(s1.getLocation().toString());
        System.out.println(entList.size());

        /* Test same location with grass */
//        Location l2 = new Location(2,2);
//        GrassTufts g1 = new GrassTufts(fi1,l2);
//        ;
//
//        System.out.println(s1.getField().getFreeAdjacentLocations(l1,1));
//        fi1.place(s1,l2);
//        fi1.clear(l1,s1);
//        Fox f1 = new Fox(fi1,l2); //Full
//        System.out.println(fi1.getObjectAt(2,2)); //Grass and Sheep

        /* Test grass giveBirth() */
//        Location l2 = new Location(3,3);
//        GrassTufts g1 = new GrassTufts(fi1,l2);
//        g1.act(entList);
//        System.out.println(entList.size());


    }

}

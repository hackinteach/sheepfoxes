import java.util.ArrayList;
import java.util.List;

/**
 * Created by HackinteachK on 2/24/17.
 */
public class TestFence {

    public static void main(String[] args) {
        Location l1 = new Location(1, 1);
        Field fi1 = new Field(5, 5);
        Fences f1 = new Fences(fi1,l1);
        List<Entity> entList = new ArrayList<>();

        /* Check can't place location*/
        Location l2 = new Location(0,0);
        Sheep s1 = new Sheep(fi1,l2);

        Location l3 = new Location(1,0);
        Fences f2 = new Fences(fi1,l3);

        Location l4 = new Location(0,1);
        Fences f3 = new Fences(fi1,l4);

        s1.act(entList);
        System.out.println(s1.getField().getFreeAdjacentLocations(l2,1).size());
        System.out.println(s1.getLocation());

    }

}

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HackinteachK on 2/24/17.
 */
public class Test {

    public static void main(String[] args) {
        Location l1 = new Location(2, 1);
        Field fi1 = new Field(5, 5);
        Fox f1 = new Fox(fi1, l1);
        List<Entity> entList = new ArrayList<>();

        /* ObjectInsight */
//        Location ls1 = new Location(0,0);
//        Sheep s1 = new Sheep(fi1,ls1);
//        Location ls2 = new Location(4,3);
//        Sheep s2 = new Sheep(fi1,ls2);
//        Location ls3 = new Location(1,1);
//        Sheep s3 = new Sheep(fi1,ls3);
//        Location ls4 = new Location(3,1);
//        Sheep s4 = new Sheep(fi1,ls4);
//        Location ls5 = new Location(1,0);
//        Sheep s5 = new Sheep(fi1,ls5);
//        Location ls6 = new Location(3,0);
//        Sheep s6 = new Sheep(fi1,ls6);
//        Location ls7 = new Location(2,4);
//        Sheep s7 = new Sheep(fi1,ls7);
//
//        f1.act(entList);
//
//        System.out.println(f1.getObjectInSight().size() == 6);

        /* Check findFood() */
//        Location ls1 = new Location(1,0);
//        Sheep s1 = new Sheep(fi1,ls1);
//        Location ls2 = new Location(1,1);
//        Sheep s2 = new Sheep(fi1,ls2);
//        Location ls3 = new Location(3,1);
//        Sheep s3 = new Sheep(fi1,ls3);
//
//        /* findFood() and giveBirths() */
//        int i=0;
//        while(i<3){
//            f1.act(entList);
//            i++;
//            System.out.println("New Animals " + entList.size());
//            System.out.println("Sheep 1 "+s1.isAlive());
//            System.out.println("Sheep 2 "+s2.isAlive());
//            System.out.println("Sheep 3 "+s3.isAlive());
//            System.out.println("Final Location "+f1.getLocation().toString());
//            System.out.println("-----------------");
//        }

        /* Check findFood() with sightRange and speed */
//        Location ls1 = new Location(4,4);
//        Sheep s1 = new Sheep(fi1,ls1);
//        Location ls2 = new Location(0,3);
//        Sheep s2 = new Sheep(fi1,ls2);
//        Location ls3 = new Location(2,2);
//        Sheep s3 = new Sheep(fi1,ls3);
//
//        /* This Works */
//
////        Location foodLoc = findFood();
////        if(foodLoc == null){
////            move();
////        }else{
////            goNewLocation(foodLoc);
////        }
//
//        /* findFood() and giveBirths() */
//        int i=0;
//        while(i<3){
//            f1.act(entList);
//            i++;
//            //System.out.println("New Animals " + entList.size());
//            System.out.println("Sheep 1 "+s1.isAlive());
//            System.out.println("Sheep 2 "+s2.isAlive());
//            System.out.println("Sheep 3 "+s3.isAlive());
//            System.out.println("Final Location "+f1.getLocation().toString());
//            System.out.println("-----------------");
//        }

        /* Check Dead */
//        System.out.println(fi1.getObjectAt(2,1));
//
//        f1.setDead();
//
//        System.out.println(fi1.getObjectAt(2,1));

        /* Check move to free nearby location*/
//        Location ls1 = new Location(2,0);
//        Sheep s1 = new Sheep(fi1,ls1);
//        Location ls2 = new Location(2,2);
//        Sheep s2 = new Sheep(fi1,ls2);
//        Location ls3 = new Location(1,1);
//        Sheep s3 = new Sheep(fi1,ls3);
//        Location ls4 = new Location(3,1);
//        Sheep s4 = new Sheep(fi1,ls4);
//        Location ls5 = new Location(1,0);
//        Sheep s5 = new Sheep(fi1,ls5);
//        Location ls6 = new Location(3,0);
//        Sheep s6 = new Sheep(fi1,ls6);
//        Location ls7 = new Location(1,2);
//        Sheep s7 = new Sheep(fi1,ls7);
//
//
//        f1.act(entList);
//        Location newLoc = f1.getLocation();
//        System.out.println(newLoc.getRow() + " " + newLoc.getCol());
//
//        //Must ans 3,2

       /* Check giveBirth to nearby location */
//        System.out.println(entList.size());
//        f1.act(entList);
//        System.out.println(entList.size());
//        f1.act(entList);
//        System.out.println(entList.size());
//
//        for(Entity elt: entList){
//            System.out.println(elt.getLocation().toString());
//            int i =0;
//            while(i<10){
//                ((Fox)elt).act(entList);
//                System.out.println(entList.size());
//                i++;
//            }
//        }

        /* Check move with speed */
        // manually compare with board
//
//        int row = f1.getLocation().getRow();
//        int col = f1.getLocation().getCol();
//
//        System.out.println(row + " " + col);
//        f1.act(entList);
//
//        row = f1.getLocation().getRow();
//        col = f1.getLocation().getCol();
//
//        System.out.println(row + " " + col);
//        System.out.println(fi1.getObjectAt(row,col));
    }
}



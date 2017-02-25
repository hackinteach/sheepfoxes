import java.util.List;
import java.util.Random;

/**
 * Created by HackinteachK on 2/25/17.
 */
public class GrassTufts extends Plant {

    private static final int MAX_AGE = 100;
    private static final int BREEDING_AGE = 30;
    private static final int MAX_LITTER_SIZE = 2;
    private static final double BREEDING_PROBABILITY = 0.1;
    private static Random rand = new Random();

    private int age;

    public GrassTufts(boolean randomAge,Field f, Location loc) {
        super(f, loc);
        if(randomAge){
            age = rand.nextInt(MAX_AGE/2);
        }else{
            age = 0;
        }
    }

    public GrassTufts(Field f,Location loc){
        this(false,f,loc);
    }

    @Override
    public void act(List<Entity> newEnt) {
        old();
        if(isAlive()){
            giveBirth(newEnt);
        }
    }

    private void old(){
        age++;
        if(age > MAX_AGE) setDead();
    }

    private void giveBirth(List<Entity> newFoxes)
    {
        // New foxes are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation(),1);
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            GrassTufts young = new GrassTufts(false, field, loc);
            newFoxes.add(young);
        }
    }

    private int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }

    private boolean canBreed(){
        return age >= BREEDING_AGE;
    }
}

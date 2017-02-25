import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by HackinteachK on 2/24/17.
 */
public class Sheep extends Animal {

    // Characteristics shared by all sheep (class variables).

    // The age at which a rabbit can start to breed.
    private static final int BREEDING_AGE = 5;
    // The age to which a rabbit can live.
    private static final int MAX_AGE = 40;
    // The likelihood of a rabbit breeding.
    private static final double BREEDING_PROBABILITY = 0.18;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 4;
    // number of steps a fox can go before it has to eat again.
    private static final int GRASS_FOOD_VALUE = 15;
    // A shared random number generator to control breeding.
    private static final Random rand = new Random();
    // Number of box a fox can see from its location.
    private static final int SIGHT_RANGE = 3;
    // Number of box that a fox can move in 1 yip
    private static final int MAX_SPEED = 2;

    // Individual characteristics (instance fields).

    // The sheep's age.
    private int age;
    private int foodLevel;

    // Indication of breeding
    private boolean ate = false;
    // One's speed
    private int speed;
    // Seen object int its range
    private List<Entity> objectInSight;


    public Sheep(boolean randomAge,Field f, Location loc) {
        super(f, loc);
        if(randomAge){
            age = rand.nextInt(MAX_AGE);
            foodLevel = rand.nextInt(GRASS_FOOD_VALUE)+1;
        }else{
            age = 0;
            foodLevel = GRASS_FOOD_VALUE;
        }
        objectInSight = new ArrayList<>();
        speed = rand.nextInt(MAX_SPEED) +1;
    }


    public Sheep(Field f, Location loc){
        this(false,f,loc);
    }

    @Override
    public void act(List<Entity> newEnt) {
        old();
        hungry();
        if(isAlive()){
            move();
            Location foodLoc = findFood();
            giveBirth(newEnt);
            if(foodLoc == null){
                move();
            }
            if(foodLoc != null) {
                goNewLocation(foodLoc);
            }
        }
    }


    private void old(){
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }

    private void hungry(){
        foodLevel--;
        if(foodLevel <= 0){
            setDead();
        }
    }

    /**
     * Look for rabbits adjacent to the current location.
     * Only the first live rabbit is eaten.
     * @return Where food was found, or null if it wasn't.
     */
    private Location findFood()
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation(),SIGHT_RANGE);
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            if(getLocation().compare(where) <= speed){
                GrassTufts grass = (GrassTufts) field.getSpecificObject(where,GrassTufts.class);
                if(grass instanceof Plant) {
                    if(grass.isAlive()) {
                        grass.setDead();
                        foodLevel = GRASS_FOOD_VALUE;
                        ate = true;
                        return where;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Check whether or not this fox is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newFoxes A list to return newly born foxes.
     */
    private void giveBirth(List<Entity> newFoxes)
    {
        // New foxes are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation(),1);
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Sheep young = new Sheep(false, field, loc);
            newFoxes.add(young);
        }
    }

    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    private int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }

    /**
     * A fox can breed if it has reached the breeding age.
     */
    private boolean canBreed()
    {
        return age >= BREEDING_AGE && ate;
    }

    private void look(){
        List<Location> adjacent = getField().adjacentLocations(getLocation(),SIGHT_RANGE);

        for(Location loc : adjacent){
            List<Entity> allObj = getField().getObjectAt(loc);
            if(allObj.size() > 0){
                for(Entity ent: allObj){
                    if(!(ent instanceof GrassTufts) ){
                        objectInSight.add(ent);
                    }
                }
            }
        }
    }

    private void goNewLocation(Location newLocation){
        if(getLocation().compare(newLocation) <= speed){
            setLocation(newLocation);
        }
//        else{
//            System.out.println("Sorry, not enough speed");
//        }
    }

    private void move(){
        List<Location> free = getField().getFreeAdjacentLocations(getLocation(),speed);
        if(free.size() > 0){
            goNewLocation(free.get(0));
        }
    }
}

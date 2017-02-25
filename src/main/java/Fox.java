import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by HackinteachK on 2/24/17.
 */
public class Fox extends Animal {

    // Characteristics shared by all foxes (class variables).

    // The age at which a fox can start to breed.
    private static final int BREEDING_AGE = 15;
    // The age to which a fox can live.
    private static final int MAX_AGE = 150;
    // The likelihood of a fox breeding.
    private static final double BREEDING_PROBABILITY = 0.25;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 2;
    // The food value of a single rabbit. In effect, this is the
    // number of steps a fox can go before it has to eat again.
    private static final int SHEEP_FOOD_VALUE = 9;
    // A shared random number generator to control breeding.
    private static final Random rand = new Random();
    // Number of box a fox can see from its location.
    private static final int SIGHT_RANGE = 2;
    // Number of box that a fox can move in 1 yip
    private static final int MAX_SPEED = 3;

    // Individual characteristics (instance fields).
    // The fox's age.
    private int age;
    // The fox's food level, which is increased by eating rabbits.
    private int foodLevel;
    // Indication of breeding
    private boolean ate = false;
    // One's speed
    private int speed;
    // Seen object int its range
    private List<Entity> objectInSight;

    /**
     * Create a fox. A fox can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     *
     * @param randomAge If true, the fox will have random age and hunger level.
     * @param f The field currently occupied.
     * @param loc The location within the field.
     */
    public Fox(boolean randomAge,Field f, Location loc) {
        super(f, loc);
        if(randomAge){
            age = rand.nextInt(MAX_AGE);
            foodLevel = rand.nextInt(SHEEP_FOOD_VALUE) +1;
        }else{
            age = 0;
            foodLevel = SHEEP_FOOD_VALUE;
        }
        objectInSight = new ArrayList<>();
        speed = rand.nextInt(MAX_SPEED) +1;
    }

    public Fox(Field f, Location loc){
        this(true,f,loc);
    }

    @Override
    public void act(List<Entity> newEnt) {
        System.out.println("ACT FOX");
        old();
        hungry();
        if(isAlive()){
            move();
            giveBirth(newEnt);
            Location foodLoc = findFood();
            if(foodLoc == null){
                move();
            }
            if(foodLoc != null){
                goNewLocation(foodLoc);
            }
        }

    }
    public List<Entity> getObjectInSight() {
        return objectInSight;
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
            if(getLocation().compare(where) <= speed) {
                Sheep animal = (Sheep) field.getSpecificObject(where, Sheep.class);
                if (animal != null && animal instanceof Sheep) {
                    if (animal.isAlive()) {
                        animal.setDead();
                        foodLevel = SHEEP_FOOD_VALUE;
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
            Fox young = new Fox(false, field, loc);
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

    private void move(){
        List<Location> free = getField().getFreeAdjacentLocations(getLocation(),speed);
        if(free.size() > 0){
            goNewLocation(free.get(0));
        }

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


}

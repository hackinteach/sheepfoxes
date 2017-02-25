import java.util.List;

/**
 * Created by HackinteachK on 2/24/17.
 */
public abstract class Animal extends Entity {

    private boolean alive;
    private Field field;
    private Location location;

    public Animal(Field f, Location loc) {
        super(f, loc);
        alive = true;
        setLocation(loc);
        location = getLocation();
        field = f;
    }

    abstract public void act(List<Entity> newEnt);

    protected boolean isAlive(){return alive;}

    protected void setDead()
    {
        alive = false;
        if(location != null) {
            field.clear(location,this);
            location = null;
            field = null;
            setDeadEntity();
        }
    }

}

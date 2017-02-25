import java.util.List;

/**
 * Created by HackinteachK on 2/25/17.
 */
public abstract class Plant extends Entity {

    private boolean alive;
    private Field field;
    private Location location;

    public Plant(Field f, Location loc) {
        super(f, loc);
        alive = true;
        field = f;
        setLocation(loc);
        location = getLocation();
    }

    public boolean isAlive() {
        return alive;
    }

    abstract public void act(List<Entity> newEnt);

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

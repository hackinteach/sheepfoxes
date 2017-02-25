import java.util.List;

/**
 * Created by HackinteachK on 2/24/17.
 */
public abstract class Entity {

    private Location location;
    private Field field;

    public Entity(Field f, Location loc){
        this.field = f;

    }

    protected Location getLocation(){
        return location;
    }

    protected void setLocation(Location newLoc){
        if(location != null){
            field.clear(location,this);
        }
        location = newLoc;
        field.place(this,newLoc);
    }

    public Field getField() {
        return field;
    }

    abstract public void act(List<Entity> liv);

    public void setDeadEntity(){
        location = null;
        field = null;
    }
}

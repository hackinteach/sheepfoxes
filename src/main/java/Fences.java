import java.util.List;

/**
 * Created by HackinteachK on 2/25/17.
 */
public class Fences extends Entity {

    private Field field;
    private Location location;

    public Fences(Field f, Location loc) {
        super(f, loc);
        field = f;
        setLocation(loc);
        location = getLocation();
    }

    @Override
    public void act(List<Entity> liv) {

    }
}

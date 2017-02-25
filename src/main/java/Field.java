import java.util.*;

/**
 * Created by HackinteachK on 2/24/17.
 */
public class Field {

    private static final Random rand = new Random();

    private int depth,width;

    private List<Entity>[][] field;


    public Field(int d, int w){
        depth = d;
        width = w;
        field = new ArrayList[d][w];

        /* Initialise field */
        for(int i=0;i<d;i++){
            for(int j=0;j<w;j++){
                field[i][j] = new ArrayList<>();
            }
        }

    }


    public void clearAll(){
        for(int i=0;i<depth;i++){
            for(int j=0;j<width;i++){
                field[i][j].clear();
            }
        }
    }

    public void clear(Location loc, Entity ent){
        int row = loc.getRow();
        int col = loc.getCol();
        if(field[row][col].contains(ent)){
            field[row][col].remove(ent);
        }
    }

    public void place(Entity ent, int row, int col){
        place(ent,new Location(row,col));
    }

    public void place(Entity ent, Location loc){
        int row = loc.getRow();
        int col = loc.getCol();
        List<Entity> obj = field[row][col];
        if(obj.size() == 0){
            obj.add(ent);
        } else if(obj.size() == 1 && obj.get(0) instanceof GrassTufts){
            obj.add(ent);
        }
    }

    public Entity getSpecificObject(Location loc, Class entClass){
        List<Entity> search = field[loc.getRow()][loc.getCol()];
        for(Entity elt: search){
            if(elt.getClass().equals(entClass)){
                return elt;
            }
        }
        return null;
    }

    public List<Entity> getObjectAt(Location loc){
        return getObjectAt(loc.getRow(),loc.getCol());
    }

    public List<Entity> getObjectAt(int row, int col){
        return field[row][col];
    }

    public Location randomAdjacentLocation(Location loc,int range){
        List<Location> adjacent = adjacentLocations(loc,range);
        return adjacent.get(0);
    }

    public List<Location> getFreeAdjacentLocations(Location location, int range)
    {
        List<Location> free = new LinkedList<>();
        List<Location> adjacent = adjacentLocations(location,range);
        for(Location next : adjacent) {
            if(getObjectAt(next).size() == 0) {
                free.add(next);
            }
            else if(getObjectAt(next).size() == 1){
                // Grass tufts
                for(Entity elt: getObjectAt(next)){
                    if(elt instanceof GrassTufts){
                        free.add(next);
                    }
                }
            }
        }
        //System.out.println(free.size());
        //Collections.shuffle(free);
        return free;
    }

    public void clear(){
        for(int i=0;i<depth;i++){
            for(int j=0;j<width;j++){
                field[i][j].clear();
            }
        }
    }

//    public Location freeAdjacentLocation(Location location)
//    {
//        // The available free ones.
//        List<Location> free = getFreeAdjacentLocations(location);
//        if(free.size() > 0) {
//            return free.get(0);
//        }
//        else {
//            return null;
//        }
//    }

    public List<Location> adjacentLocations(Location location, int range)
    {
        assert location != null : "Null location passed to adjacentLocations";
        // The list of locations to be returned.
        List<Location> locations = new LinkedList<>();
        if(location != null) {
            int row = location.getRow();
            int col = location.getCol();
            for(int roffset = -1*range; roffset <= range; roffset++) {
                int nextRow = row + roffset;
                if(nextRow >= 0 && nextRow < depth) {
                    for(int coffset = -1*range; coffset <= range; coffset++) {
                        int nextCol = col + coffset;
                        // Exclude invalid locations and the original location.
                        if(nextCol >= 0 && nextCol < width && (roffset != 0 || coffset != 0)) {
                            locations.add(new Location(nextRow, nextCol));
                        }
                    }
                }
            }

            // Shuffle the list. Several other methods rely on the list
            // being in a random order.
            Collections.shuffle(locations, rand);
        }
        return locations;
    }

    public int getDepth() {
        return depth;
    }

    public int getWidth() {
        return width;
    }
}

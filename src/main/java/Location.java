/**
 * Created by HackinteachK on 2/24/17.
 */
public class Location {

    private int row;
    private int col;

    public Location(int r,int c){
        row = r;
        col = c;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    /**
     * Return a string of the form row,column
     * @return A string representation of the location.
     */
    public String toString()
    {
        return row + "," + col;
    }

    /**
     * Use the top 16 bits for the row value and the bottom for
     * the column. Except for very big grids, this should give a
     * unique hash code for each (row, col) pair.
     * @return A hashcode for the location.
     */
    public int hashCode()
    {
        return (row << 16) + col;
    }

    /**
     * Compare the maximum range of new location
     * to check whether animal's speed is enough
     * to move or not
     * @param newLoc
     * @return
     */
    public int compare(Location newLoc){
        int newRow = newLoc.getRow();
        int newCol = newLoc.getCol();
        int diffRow = Math.abs(newRow - row);
        int diffCol = Math.abs(newCol - col);
        if(diffRow > diffCol){
            return diffRow;
        }else{
            return diffCol;
        }
    }
}

public class Point {
    // x and y values
    private int x;
    private int y;

    // referenced from this class
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // functions to return x or y values seperately
    public int GetX() {
        return x;
    }

    public int GetY() {
        return y;
    }

    // took this from GeeksforGeeks overriding the toString Method to create a mask
    // for the point values.
    @Override
    public String toString() {
        String s = "(";
        s += x;
        s += ",";
        s += y;
        s += ")";
        return s;
    }
}
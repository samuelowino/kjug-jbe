public class Point {
    private int x;
    // or
    private int y;
    private Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void setX(int newX){
        this.x = newX;
    }
    @Override
    public String toString() {
        return "x is " + x + ": y is " + y;
    }
}

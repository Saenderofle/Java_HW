package HW_8;


public class Point {
    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceToCenter() {
        return Math.sqrt(x*x + y*y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + "), dist=" + String.format("%.3f", distanceToCenter());
    }
}

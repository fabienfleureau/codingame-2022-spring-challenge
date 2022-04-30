package codingame.spring;

public class Point2D {
    double x;
    double y;

    Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Point[x=" + x +" ,y=" + y +"]";
    }
}

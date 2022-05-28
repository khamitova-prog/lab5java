package modules;

/**
 * x и y координаты.
 */
public class Coordinates {
    private double x;
    private double y;

    public Coordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return x-coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * @return y-coordinate.
     */
    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "X:" + x + ", Y:" + y;
    }

}


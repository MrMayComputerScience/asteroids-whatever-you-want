package server;

public class Vector {
    private double x;
    private double y;
    public static final Vector ZERO = new Vector(0,0);
    public Vector(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    public Vector add(Vector other){
        return new Vector(x + other.getX(), y + other.getY());
    }
    public Vector subtract(Vector other){
        return new Vector(x - other.getX(), y - other.getY());
    }
    public double dot(Vector other){
        return x * other.getX() + y * other.getY();
    }
    public double magnitude(){
        return Math.sqrt(x*x + y*y);
    }
}

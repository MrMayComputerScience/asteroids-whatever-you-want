package server;

public class Vector {
    private double x;
    private double y;
    public static final Vector ZERO = new Vector(0,0);

    public static void main(String[] args){
        Vector v = Vector.fromMagAndAngle(10,0);
        System.out.println("v = " + v);
        v = fromMagAndAngle(10,45);
        System.out.println("v = " + v);
        System.out.println("v.magnitude() = " + v.magnitude());
        v = fromMagAndAngle(10, -90);
        System.out.println("v = " + v);
        System.out.println("v.magnitude() = " + v.magnitude());
    }

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
    public Vector scale(double scalar){
        return new Vector(x * scalar, y * scalar);
    }
    public double magnitude(){
        return Math.sqrt(x*x + y*y);
    }
    public static Vector fromMagAndAngle(double magnitude, double angle){
        Vector ret;
        double angleRads = angle * Math.PI/180;
        double x = magnitude * Math.cos(angleRads);
        double y = magnitude * Math.sin(angleRads);
        ret = new Vector(x,y);
        return ret;
    }
    public String toString(){
        return "<"+x+", "+y+">";
    }
}

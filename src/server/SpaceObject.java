package server;

import mayflower.Actor;
import mayflower.Direction;

public class SpaceObject extends Actor implements Comparable<SpaceObject>{
    private Vector velocity;
    private int id;
    protected int priority;

    public SpaceObject(String img){
        setImage(img);
        velocity = Vector.ZERO;
        priority = Integer.MAX_VALUE;
    }

    public int getPriority() {
        return priority;
    }


    public void setVelocity(Vector velocity){
        this.velocity = velocity;
    }
    public void accelerate(int velToAdd, double angle){
        if(angle != 180 && angle != 0){
            angle = -angle;
        }
        Vector accelVec = Vector.fromMagAndAngle(velToAdd, angle);
        velocity = velocity.add(accelVec);
    }
    public Vector getVelocity(){
        return velocity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void act() {
        if(isAtEdge()){
            int rot = getRotation();
            setRotation(0);
            if(getX() <= 0){
                setLocation(getWorld().getWidth(), getY());
            }
            else if(getX()+getImage().getWidth() >= getWorld().getWidth()){
                setLocation(0,getY());
            }
            else if(getY() <= 0){
                setLocation(getX(), getWorld().getHeight() - getImage().getHeight());
            }
            else if(getY()+getImage().getHeight() >= getWorld().getHeight()){
                setLocation(getX(), 0);
            }
            setRotation(rot);
            int count = 0;
            while(isAtEdge()){
                System.out.println("Loc Beofre: "+getX() + ", " + getY());
                move(5);
                System.out.println("Loc After: "+getX() + ", " + getY());
                if(++count >= 10){
                    break;
                }
            }

        }
    }
    public void setLocation(double x, double y){
        int rot = getRotation();
        super.setLocation(x,y);
        setRotation(rot);
    }


    @Override
    public int compareTo(SpaceObject o) {
        return o.priority - priority;
    }
}

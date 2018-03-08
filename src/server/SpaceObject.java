package server;

import mayflower.Actor;
import mayflower.Direction;

public class SpaceObject extends Actor implements Comparable<SpaceObject>{
    private Vector velocity;
    private int id;
    protected int priority;
    private boolean wasAtEdgeX;
    private boolean wasAtEdgeY;

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
        int rot = getRotation();
        if(needsToMoveX() && !wasAtEdgeX){
            setRotation(0);
            if(getX() < 0){
                setLocation(getWorld().getWidth(), getY());
            }
            else if(getX()+getImage().getWidth() > getWorld().getWidth()){
                setLocation(0,getY());
            }
            setRotation(rot);
            wasAtEdgeX = true;
        }
        else if(!needsToMoveX() && wasAtEdgeX){
            wasAtEdgeX = false;
        }
        if(needsToMoveY() && !wasAtEdgeY){
            if(getY() < 0){
                setLocation(getX(), getWorld().getHeight() - getImage().getHeight());
            }
            else if(getY()+getImage().getHeight() > getWorld().getHeight()){
                setLocation(getX(), 0);
            }
            setRotation(rot);
            int count = 0;
        }
    }
    public void setLocation(double x, double y){
        int rot = getRotation();
        super.setLocation(x,y);
        setRotation(rot);
    }
    public boolean needsToMoveX(){
        return getCenterX() > getWorld().getWidth() || getCenterX() < 0;
    }
    boolean needsToMoveY(){
        return getCenterY() > getWorld().getHeight() || getCenterY() < 0;
    }
    @Override
    public int compareTo(SpaceObject o) {
        return o.priority - priority;
    }
}

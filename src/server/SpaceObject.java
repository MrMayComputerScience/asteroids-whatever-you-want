package server;

import mayflower.Actor;
import mayflower.Direction;

public class SpaceObject extends Actor {
    private Vector velocity;
    private int id;

    public SpaceObject(String img){
        setImage(img);
        velocity = Vector.ZERO;
    }

    public void setVelocity(Vector velocity){
        this.velocity = velocity;
    }
    public void accelerate(int velToAdd, double angle){
        if(angle != 180 && angle != 0){
            angle = -angle;
        }
        angle *= (Math.PI/180);
        double x = velToAdd * Math.cos(angle);
        double y = velToAdd * Math.sin(angle);
        velocity = velocity.add(new Vector(x,y));
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
        }
    }
    public void setLocation(double x, double y){
        int rot = getRotation();
        super.setLocation(x,y);
        setRotation(rot);
    }


}

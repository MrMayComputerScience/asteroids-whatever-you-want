package server;

import mayflower.Actor;
import mayflower.Direction;

public class SpaceObject extends Actor {
    private int velocity;
    public SpaceObject(String img){
        setImage(img);
    }
    public void setVelocity(int velocity){
        this.velocity = velocity;
    }
    public int getVelocity(){
        return velocity;
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

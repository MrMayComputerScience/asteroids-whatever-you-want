package server;

import mayflower.Actor;

public class SpaceObject extends Actor {
    private int velocity;
    public SpaceObject(String img){
        setImage(img);
    }
    public void setVelocity(int velocity){
        this.velocity = velocity;
    }
    @Override
    public void act() {
        move(velocity);
        if(isAtEdge()){
            System.out.printf("Old: (%d, %d)\n", getX(), getY());
            if(getX() <= 0 || getX()+getImage().getWidth() >= getWorld().getWidth()){
                setLocation(getWorld().getWidth() - getX(), getY());
            }
            else{
                setLocation(getX(), getWorld().getHeight() - getY());
            }
            System.out.printf("New: (%d, %d)\n", getX(), getY());
        }
    }

}

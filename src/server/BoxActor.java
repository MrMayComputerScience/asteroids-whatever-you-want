package server;

import mayflower.Actor;

public class BoxActor extends Actor
{
    private String image;
    public BoxActor(String image)
    {
        this.image = image;
        System.out.println("rsrc/"+image+".png");
        setImage("rsrc/"+image+".png");
    }

    @Override
    public void act() {

    }
    public String toString()
    {
        return image+","+getX()+","+getY()+","+getRotation();
    }
}

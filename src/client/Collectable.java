package client;

import mayflower.Actor;

public class Collectable extends Actor
{
    public Collectable()
    {
        setImage("rsrc/Collectable.png");
        setLocation(Math.random()*1024,Math.random()*768);
    }

    @Override
    public void act()
    {
        //To-do replace Actor with something else
        Actor touching = this.getOneIntersectingObject(Actor.class);
        if(touching!=null)
        {
            //
        }
    }

    @Override
    public String toString() {
        return "Collectable:" +String.valueOf(getX())+" "+String.valueOf(getY());
    }
}

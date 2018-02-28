package server;

import mayflower.Actor;

public class Collectable extends SpaceObject
{
    public Collectable()
    {
        super("rsrc/Collectable.png");
        do {
            setLocation(Math.random() * 1024, Math.random() * 768);
            //to-do replace spaceObject with spaceActor
        }while(this.getObjectsInRange(50, SpaceObject.class).size()>0);
        setVelocity(0);
    }
    public void setVelocity(int v){
        //noop
    }
    @Override
    public void act()
    {
        //To-do replace Actor with something else
        Actor touching = this.getOneIntersectingObject(Actor.class);
        if(touching!=null)
        {



            this.getWorld();
        }
    }

    @Override
    public String toString() {
        return "Collectable:" + getId()+" "+String.valueOf(getX())+" "+String.valueOf(getY());
//        return "Collectable:"+String.valueOf(getX())+" "+String.valueOf(getY());

    }
}

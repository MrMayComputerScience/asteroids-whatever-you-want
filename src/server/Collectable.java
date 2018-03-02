package server;

import mayflower.Actor;

public class Collectable extends SpaceObject
{
    public Collectable()
    {
        super("rsrc/Collectable.png");
        do {
            setLocation(Math.random() * 800, Math.random() * 600);

        }while(this.getObjectsInRange(50, ShipActor.class).size()>0);
        setVelocity(0);
    }
    public void setVelocity(int v){
        //noop
    }
    @Override
    public void act()
    {
        //To-do replace Actor with something else
        ShipActor touching = this.getOneIntersectingObject(ShipActor.class);
        if(touching!=null)
        {
            touching.setScore();
            this.getWorld().removeObject(this);
        }
    }

    @Override
    public String toString() {
        return "Collectable:" + getId()+" "+String.valueOf(getX())+" "+String.valueOf(getY());
//        return "Collectable:"+String.valueOf(getX())+" "+String.valueOf(getY());

    }
}

package server;

import mayflower.Actor;

public class Collectable extends SpaceObject
{
    public Collectable()
    {
        super("rsrc/Collectable.png");
        do {
            setLocation(Math.random() * 1024, Math.random() * 768);

        }while(this.getObjectsInRange(50, ShipActor.class).size()>0);
        setVelocity(0);
    }
    public void setVelocity(int v){
        //noop
    }
    @Override
    public void act()
    {

        ShipActor touching = this.getOneIntersectingObject(ShipActor.class);
        if(touching!=null)
        {
            touching.addPoint();
            this.getWorld().removeObject(this);
        }
    }

    @Override
    public String toString() {
        return "Collectable:" + getId()+" "+String.valueOf(getX())+" "+String.valueOf(getY());
    }
}

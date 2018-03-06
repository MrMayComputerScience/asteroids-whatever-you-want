package server;

import mayflower.Actor;

public class SpaceLaser extends SpaceObject {
    private boolean isGoodToGo;
    private ShipActor dad;
    public SpaceLaser() {
        super("rsrc/Laser.png");
    }
    public String toString(){
        return String.format("lazer:%d %d %d %d", getId(), getX(), getY(), getRotation());
//        return String.format("lazer:%d %d %d", getX(), getY(), getRotation());

    }

    public void act(){
        super.act();
        //Should always work. Dont start lasers when theyre not on their ships
        if(dad == null)
            dad = getIntersectingObjects(ShipActor.class).get(0);
        while(!isGoodToGo){
            if(getBounds().intersects(dad.getBounds().getBounds()))
                move(10);
            else
                isGoodToGo = true;
        }
        for(Actor a : getIntersectingObjects(Actor.class)){
            if(a instanceof Explodable){
                Explodable e = (Explodable)a;
                e.explode();
                if(getWorld() != null)
                    getWorld().removeObject(this);
            }

        }

    }
}

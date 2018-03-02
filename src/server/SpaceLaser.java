package server;

import mayflower.Actor;

public class SpaceLaser extends SpaceObject {
    public SpaceLaser() {
        super("rsrc/Laser.png");
    }
    public String toString(){
        return String.format("lazer:%d %d %d %d", getId(), getX(), getY(), getRotation());
//        return String.format("lazer:%d %d %d", getX(), getY(), getRotation());

    }

    public void act(){
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

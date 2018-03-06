package server;

import java.util.List;

//needs to extend space object
public class Asteroid extends SpaceObject implements Explodable
{
    private boolean isLarge;
    @Override
    public void act()
    {

    }

    public Asteroid(boolean isBig)
    {
        super(isBig ? "rsrc/LargeAsteroid.png" : "rsrc/LargeAsteroid.png");
        isLarge = isBig;
        Vector velocity = new Vector((int)Math.random()*10, (int)Math.random()*10);
        setVelocity(velocity);
    }

    @Override
    public String toString() {

        return String.format("asteroid:%d %s %d %d %d", getId(), isLarge ? "large":"small", getX(),getY(),getRotation());
//        return String.format("asteroid:%s %d %d %d", isLarge ? "large":"small", getX(),getY(),getRotation());

    }
    public void explode(){
        if(isLarge){
            int numAstMinusOne = (int)(Math.random() * 3) + 1;
            //Momentum assuming big asteroid is 10x more massive than small
            Vector momentum = new Vector(getVelocity().getX() * 10, getVelocity().getY() * 10);
            Asteroid[] children = new Asteroid[numAstMinusOne + 1];
            for(int i = 0; i < numAstMinusOne; i++){
                children[i] = makeSmallExplodedAsteroid(momentum);
                momentum = momentum.subtract(children[i].getVelocity());
            }
            children[numAstMinusOne] = makeLastSmallExplodedAsteroid(momentum);
            for(Asteroid a : children){
                getWorld().addObject(a, getCenterX(), getCenterY());
            }
        }
        else{
            getWorld().removeObject(this);
        }
    }

    private Asteroid makeSmallExplodedAsteroid(Vector momentum){
        Asteroid small = new Asteroid(false);
        int angle = (int)(Math.random() * -360) + 180; //Rand range from -179 to 180
        double mag = Math.random() * momentum.magnitude();
        //Add 180 to the angle to convert from mayflower angle to a regular angle
        Vector astVel = Vector.fromMagAndAngle(mag, angle+180);
        small.setRotation(angle);
        small.setVelocity(astVel);
        return small;
    }
    private Asteroid makeLastSmallExplodedAsteroid(Vector momentum){
        Asteroid small = new Asteroid(false);
        int angle = (int)(Math.random() * -360) + 180; //Rand range from -179 to 180
        double mag = momentum.magnitude();
        Vector astVel = Vector.fromMagAndAngle(mag, angle+180);
        small.setRotation(angle);
        small.setVelocity(astVel);
        return small;
    }
    public List<SpaceObject> collides()
    {
        return this.getIntersectingObjects(SpaceObject.class);
    }
}

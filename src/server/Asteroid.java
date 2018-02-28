package server;

import java.util.List;

//needs to extend space object
public class Asteroid extends SpaceObject
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
        setVelocity((int)Math.random()*10);
    }

    @Override
    public String toString() {
        return String.format("asteroid:%s %d %d %d", isLarge ? "large":"small", getX(),getY(),getRotation());
    }
    public void explode(){
        if(isLarge){
            int numAst = (int)(Math.random() * 3) + 1;
            //TODO Add scaling size asteroids

        }
    }
    public List<SpaceObject> collides()
    {
        return this.getIntersectingObjects(SpaceObject.class);
    }
}

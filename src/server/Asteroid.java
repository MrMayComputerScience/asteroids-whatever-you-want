package server;

import java.util.List;

//needs to extend space object
public class Asteroid extends SpaceObject
{

    @Override
    public void act()
    {

    }

    public Asteroid(String s)
    {
        super(s);
        setVelocity((int)Math.random()*10);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public List<SpaceObject> collides()
    {
        return this.getIntersectingObjects(SpaceObject.class);
    }
}

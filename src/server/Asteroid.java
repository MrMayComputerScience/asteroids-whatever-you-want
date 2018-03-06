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
            int numAst = (int)(Math.random() * 3) + 1;
            //TODO Add scaling size asteroids
            double momentumx = getVelocity().getX() *10; //Assuming big asteroid is 10x more massive than small
            double momentumy = getVelocity().getY() *10;
            for(int i = 0; i < numAst; i++){
                Asteroid small = new Asteroid(false);
                double x = Math.random() * momentumx;
                double y = Math.random() * momentumy;
            }
        }
    }
    public List<SpaceObject> collides()
    {
        return this.getIntersectingObjects(SpaceObject.class);
    }
}

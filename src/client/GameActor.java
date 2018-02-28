package client;

import mayflower.Actor;

public class GameActor extends Actor
{
    public GameActor(String img, int x, int y, int r)
    {
        setImage(img);
        setLocation(x, y);
        setRotation(r);
    }

    public GameActor(Actor actor, int x1, int y1, int r1, int x2, int y2, int r2, double p)
    {
        //interpolate between two points
        double q = 1 - p;
        int x = (int)(x1 * q + x2 * p);
        int y = (int)(y1 * q + y2 * p);
        int r = (int)(r1 * q + r2 * p);

        setImage(actor.getImage());
        setLocation(x, y);
        setRotation(r);
    }

    public String toString()
    {
        return getImage().toString() + "_" + getX() + "_" + getY() + "_" + getRotation();
    }

    @Override
    public void act() {

    }
}

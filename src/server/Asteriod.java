package server;

import mayflower.Actor;

//needs to extend space object
public class Asteriod extends Actor
{
    int velocity;
    @Override
    public void act()
    {

    }

    public Asteriod( int rot)
    {

        this.setRotation(rot);
        velocity = (int)Math.random()*10;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

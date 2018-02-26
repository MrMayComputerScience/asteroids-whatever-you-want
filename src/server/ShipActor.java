package server;

import mayflower.World;

public class ShipActor extends SpaceObject{
    private SpaceCannon cannon;
    private EngineerSystem engie;
    private int points;



    public ShipActor() {
        super("rsrc/SpaceshipNoCannon.png");
        engie = new EngineerSystem();
        cannon = new SpaceCannon(engie);
        points = Integer.MIN_VALUE;

    }

    public SpaceCannon getCannon() {
        return cannon;
    }


    @Override
    protected void addedToWorld(World world) {
        super.addedToWorld(world);
        world.addObject(cannon, getX(), getY());
        world.addObject(engie, getX(), getY());

    }

    @Override
    public void act() {
        super.act();
        cannon.setLocation(getX(), getY());
    }

    public String toString(){
        return String.format("ship:%d %d %d %d/%d/%d", getX(), getY(), getRotation(),
                engie.getReserveEnergy(), engie.getShipEnergy(), engie.getCannonEnergy());
    }

    public int getPoints() {
        return points;
    }

    public EngineerSystem getEngie() {
        return engie;
    }

}

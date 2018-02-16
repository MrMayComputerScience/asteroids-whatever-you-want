package server;

import mayflower.World;

public class ShipActor extends SpaceObject{
    private SpaceCannon cannon;
    private EngineerSystem engie;

    public ShipActor(String img) {
        super(img);
        engie = new EngineerSystem();
        cannon = new SpaceCannon("rsrc/LaserCannon.png");
    }

    public SpaceCannon getCannon() {
        return cannon;
    }

    public EngineerSystem getEngie() {
        return engie;
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
}

package server;

import mayflower.World;

public class ShipActor extends SpaceObject{
    private SpaceCannon cannon;
    private EngineerSystem engie;

<<<<<<< HEAD
    public ShipActor(String img) {
        super(img);
        engie = new EngineerSystem();
        cannon = new SpaceCannon("rsrc/LaserCannon.png");
=======
    public ShipActor() {
        super("rsrc/SpaceshipNoCannon.png");
        engie = new EngineerSystem();
        cannon = new SpaceCannon(engie);
>>>>>>> Mason
    }

    public SpaceCannon getCannon() {
        return cannon;
    }

<<<<<<< HEAD
    public EngineerSystem getEngie() {
        return engie;
    }

=======
>>>>>>> Mason
    @Override
    protected void addedToWorld(World world) {
        super.addedToWorld(world);
        world.addObject(cannon, getX(), getY());
        world.addObject(engie, getX(), getY());
<<<<<<< HEAD
=======

>>>>>>> Mason
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
<<<<<<< HEAD
=======


    public EngineerSystem getEngie() {
        return engie;
    }
>>>>>>> Mason
}

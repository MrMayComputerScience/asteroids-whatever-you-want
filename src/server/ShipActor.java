package server;

import mayflower.World;

public class ShipActor extends SpaceObject implements Explodable{
    private SpaceCannon cannon;
    private EngineerSystem engie;
    private int score;



    public ShipActor() {
        super("rsrc/SpaceshipNoCannon.png");
        engie = new EngineerSystem();
        cannon = new SpaceCannon(engie);

        score = 0;
        priority = 1;

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
        return String.format("ship:%d %d %d %d %d %d/%d/%d", getId(), getX(), getY(), getRotation(), getScore(),
                engie.getReserveEnergy(), engie.getShipEnergy(), engie.getCannonEnergy());
//        return String.format("ship:%d %d %d %d/%d/%d", getX(), getY(), getRotation(),
//                engie.getReserveEnergy(), engie.getShipEnergy(), engie.getCannonEnergy());
    }

    public void explode(){
        //TODO
        System.out.println("EXPLODE!!");
    }

    public EngineerSystem getEngie() {
        return engie;
    }

    public void setScore() {
        this.score++;
    }

    public int getScore() {
        return score;
    }
}

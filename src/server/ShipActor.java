package server;

import mayflower.Timer;
import mayflower.World;

public class ShipActor extends SpaceObject implements Explodable{
    private SpaceCannon cannon;
    private EngineerSystem engie;
    private int score;
    private Timer immune;



    public ShipActor() {
        super("rsrc/SpaceshipNoCannon.png");
        engie = new EngineerSystem();
        cannon = new SpaceCannon(engie);


        score = 0;

        immune = new Timer(2000000000);

        priority = 1;

    }

    public SpaceCannon getCannon() {
        return cannon;
    }


    @Override
    protected void addedToWorld(World world) {
        super.addedToWorld(world);
        if(world != null) {
            world.addObject(cannon, getX(), getY());
            world.addObject(engie, getX(), getY());
        }
    }

    @Override
    public void act() {
        super.act();
        cannon.setLocation(getX(), getY());
        if(this.isTouching(Asteroid.class)&&immune.isDone()){
            System.out.println(engie.getTotalEnergy());
            engie.removeTotalEnergy();
            immune.reset();
        }
        if(engie.getTotalEnergy() <=0){
            if(getWorld() != null) {
                getWorld().removeObject(cannon);
                getWorld().removeObject(engie);
                getWorld().removeObject(this);
            }
        }
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

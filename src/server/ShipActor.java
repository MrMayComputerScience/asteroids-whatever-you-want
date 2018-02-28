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
        points = 0;

    }

    public SpaceCannon getCannon() {
        return cannon;
    }

    public void takeDamage()
    {
        engie.removeTotalEnergy();
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
        if(engie.getReserveEnergy()==-1)
        {
            int random =(int)Math.random()*2;
            if(random == 1)
            {
                if(engie.getShipEnergy()>0)
                    engie.removeShipEnergy();
                else
                    engie.removeCannonEnergy();
            }
            else if(random == 0&&engie.getCannonEnergy()>0)
            {
                if(engie.getCannonEnergy()>0)
                    engie.removeCannonEnergy();
                else
                    engie.removeShipEnergy();
            }

        }

    }

    public String toString(){

        System.out.println("ID:"+getId());
        return String.format("ship:%d %d %d %d %d %d/%d/%d", getId(), getX(), getY(), getRotation(), getPoints(),
                engie.getReserveEnergy(), engie.getShipEnergy(), engie.getCannonEnergy());


//        return String.format("ship:%d %d %d %d/%d/%d", getX(), getY(), getRotation(),
//                engie.getReserveEnergy(), engie.getShipEnergy(), engie.getCannonEnergy());
    }


    public int getPoints() {
        return points;
    }

    public void addPoint(){points++;}
    public EngineerSystem getEngie() {
        return engie;
    }

}

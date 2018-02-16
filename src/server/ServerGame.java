package server;

import mayflower.Actor;
import mayflower.Direction;
import mayflower.MayflowerHeadless;
import mayflower.World;
import mayflower.net.Server;

import java.util.HashMap;
import java.util.Map;

public class ServerGame extends MayflowerHeadless
{
    //spaceObject needs to be changed to SpaceActor
    private Map<Integer, ShipActor> actors;
    private ServerWorld world;

    public ServerGame(Server server)
    {
        super("Server", 800, 600);
        actors = new HashMap<Integer, ShipActor>();

        world = new ServerWorld(server);
        this.setWorld(world);
    }

    public void process(int i, String s)
    {
        String[] system = s.split(":");
        String[] direction = system[1].split(" ");
        ShipActor actor = actors.get((i-1)/3);

        if(actor.getClass().equals(EngineerSystem.class))
        {
            switch (direction[1])
            {
                case "addMovement":
                    if(actor.getEngie().getReserveEnergy()>0)
                    {
                        actor.getEngie().addShipEnergy();
                    }
                    break;
                case "addCannon":
                    if(actor.getEngie().getReserveEnergy()>0)
                    {
                        actor.getEngie().addCannonEnergy();
                    }
                    break;
                case "removeMovement":
                    if(actor.getEngie().getShipEnergy()>0)
                        actor.getEngie().removeShipEnergy();
                    break;
                case "removeCannon":
                    if(actor.getEngie().getCannonEnergy()>0)
                        actor.getEngie().removeCannonEnergy();
                    break;
            }
        }
        else if(actor.getClass().equals(ShipActor.class))
        {
            switch (direction[1])
            {
                case "TurnCCW":
                    actor.setRotation(actor.getRotation()-5);
                    break;
                case "TurnCW":
                    actor.setRotation(actor.getRotation()+5);
                    break;
                case "ChangeSpeed":
                    actor.setVelocity(actor.getVelocity()+2);
                    break;
            }
            if(direction[1].equals("ChangeSpeed"))
                actor.setVelocity(actor.getVelocity()+2);
            else
                actor.setVelocity(actor.getVelocity()-2);
        }
        else
        {

        }

        /*if(actor != null)
        {
            switch(system[0])
            {
                case "Movement":
                    if(direction[1].equals("TurnCCW"))
                        actor.setRotation(actor.getRotation()-5);
                    else
                        actor.setRotation(actor.getRotation()+5);
                    if(direction[1].equals("ChangeSpeed"))
                        actor.setVelocity(actor.getVelocity()+2);
                    else
                        actor.setVelocity(actor.getVelocity()-2);
                    break;
                case "Weapon":

                case "left":
                    actor.setRotation(Direction.WEST);
                    break;
                case "right":
                    actor.setRotation(Direction.EAST);
                    break;
            }

            //actor.move(10);
        }*/
    }

    public void join(int i, String image)
    {
        ShipActor actor = new ShipActor(image);
        int x = 5;//(int)(Math.random() * 700) + 50;
        int y = 5;//(int)(Math.random() * 500) + 50;
        world.addObject(actor, x, y);

        actors.put(i, actor);
    }

    public void leave(int i)
    {
        Actor actor = actors.get(i);
        if(null != actor)
        {
            world.removeObject(actor);
        }
    }

    @Override
    public void init()
    {
    }
}

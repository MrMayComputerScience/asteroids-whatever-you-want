package server;

import mayflower.Actor;
import mayflower.Direction;
import mayflower.MayflowerHeadless;
import mayflower.World;
import mayflower.net.Server;

import java.util.HashMap;
import java.util.Map;

public class ServerGame extends MayflowerHeadless {
    //spaceObject needs to be changed to SpaceActor
    private Map<Integer, ShipActor> actors;
    private ServerWorld world;

    public ServerGame(Server server) {
        super("Server", 800, 600);
        actors = new HashMap<Integer, ShipActor>();
        world = new ServerWorld(server);
        this.setWorld(world);
    }

    public void process(int i, String s) {
        String[] system = s.split(":");
        String[] direction = system[1].split(" ");
        ShipActor actor = actors.get((i - 1) / 3);

<<<<<<< HEAD
        if(i%3==1)
        {
            switch (direction[1])
            {
                case "addShip":
                    if(actor.getEngie().getReserveEnergy()>0)
                    {
                        actor.getEngie().addShipEnergy();
                    }
                    break;
                case "add":
                    if(actor.getEngie().getReserveEnergy()>0)
                    {
                        actor.getEngie().addCannonEnergy();
                    }
                    break;
                case "removeShip":
                    if(actor.getEngie().getShipEnergy()>0)
=======
        if (actor.getClass().equals(EngineerSystem.class)) {
            switch (direction[1]) {
                case "addMovement":
                    if (actor.getEngie().getReserveEnergy() > 0) {
                        actor.getEngie().addShipEnergy();
                    }
                    break;
                case "addCannon":
                    if (actor.getEngie().getReserveEnergy() > 0) {
                        actor.getEngie().addCannonEnergy();
                    }
                    break;
                case "removeMovement":
                    if (actor.getEngie().getShipEnergy() > 0)
>>>>>>> Mason
                        actor.getEngie().removeShipEnergy();
                    break;
                case "removeCannon":
                    if (actor.getEngie().getCannonEnergy() > 0)
                        actor.getEngie().removeCannonEnergy();
                    break;
            }
<<<<<<< HEAD
        }
        else if(i%3==2)
        {
            switch (direction[1])
            {
=======
        } else if (actor.getClass().equals(ShipActor.class)) {
            switch (direction[1]) {
>>>>>>> Mason
                case "TurnCCW":
                    actor.setRotation(actor.getRotation() - 5);
                    break;
                case "TurnCW":
                    actor.setRotation(actor.getRotation() + 5);
                    break;
                case "ChangeSpeed":
                    actor.setVelocity(actor.getVelocity() + 2);
                    break;
                case "DecreaseSpeed":
                    actor.setVelocity(actor.getVelocity()+2);
                    break;

            }
<<<<<<< HEAD
        }
        else
        {
            SpaceCannon spaceCannon = actor.getCannon();
            switch (direction[1])
            {
                case "TurnCCW":
                    spaceCannon.setRotation(spaceCannon.getRotation()-5);
                    break;
                case "TurnCW":
                    spaceCannon.setRotation(spaceCannon.getRotation()+5);
                    break;
                case "Fire":
                    //spaceCannon.fire();
                    break;
            }
        }
    }
=======
            if (direction[1].equals("ChangeSpeed"))
                actor.setVelocity(actor.getVelocity() + 2);
            else
                actor.setVelocity(actor.getVelocity() - 2);
        } else {

        }
    }


>>>>>>> Mason

    public void join(int i, String image)
    {
        ShipActor actor = new ShipActor(image);
<<<<<<< HEAD
        if(i%3==1) {
            world.addObject(actor, 5, 5);
            actors.put(i, actor);
        }
=======
        int x = 5;//(int)(Math.random() * 700) + 50;
        int y = 5;//(int)(Math.random() * 500) + 50;
//        world.addObject(actor, x, y);

//        actors.put(i, actor);
>>>>>>> Mason
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

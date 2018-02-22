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
                        actor.getEngie().removeShipEnergy();
                    break;
                case "removeCannon":
                    if (actor.getEngie().getCannonEnergy() > 0)
                        actor.getEngie().removeCannonEnergy();
                    break;
            }
        } else if (actor.getClass().equals(ShipActor.class)) {
            switch (direction[1]) {
                case "TurnCCW":
                    actor.setRotation(actor.getRotation() - 5);
                    break;
                case "TurnCW":
                    actor.setRotation(actor.getRotation() + 5);
                    break;
                case "ChangeSpeed":
                    actor.setVelocity(actor.getVelocity() + 2);
                    break;
            }
            if (direction[1].equals("ChangeSpeed"))
                actor.setVelocity(actor.getVelocity() + 2);
            else
                actor.setVelocity(actor.getVelocity() - 2);
        } else {

        }
    }
}

    public void join(int i, String role)
    {
        Actor actor = null;
        switch(role){
            case("Engineer"):
                actor = new EngineerSystem();break;
            case("Ship"):
                actor = new ShipActor();break;
            case("Weapon"):
                actor = new SpaceCannon();break;
        }

        int x = (int)(Math.random() * 700) + 50;
        int y = (int)(Math.random() * 500) + 50;
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

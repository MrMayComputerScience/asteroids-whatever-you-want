package server;

import mayflower.*;
import mayflower.net.Server;

import java.util.HashMap;
import java.util.Map;

public class ServerGame extends MayflowerHeadless {
    //spaceObject needs to be changed to SpaceActor
    private Map<Integer, ShipActor> actors;
    private Map<Integer, Actor> roles;
    private ServerWorld world;

    public ServerGame(Server server) {
        super("Server", 800, 600);
        actors = new HashMap<Integer, ShipActor>();
        world = new ServerWorld(server);

        roles = new HashMap<>();

        this.setWorld(world);
    }


    public void process(int i, String s) {

        Actor role = roles.get(i);

        if (role.getClass().equals(EngineerSystem.class)) {
            switch (s) {
                case "addMovement":
                    if (actors.get(i).getEngie().getReserveEnergy() > 0) {
                        actors.get(i).getEngie().addShipEnergy();
                    }
                    break;
                case "addCannon":
                    if (actors.get(i).getEngie().getReserveEnergy() > 0) {
                        actors.get(i).getEngie().addCannonEnergy();
                    }
                    break;
                case "removeMovement":
                    if (actors.get(i).getEngie().getShipEnergy() > 0)
                        actors.get(i).getEngie().removeShipEnergy();
                    break;
                case "removeCannon":
                    if (actors.get(i).getEngie().getCannonEnergy() > 0)
                        actors.get(i).getEngie().removeCannonEnergy();
                    break;
            }
        }
        else if (role.getClass().equals(ShipActor.class)) {
            switch (s) {
                case "turnCCW":
                    actors.get(i).setRotation(actors.get(i).getRotation() - 5);
                    break;
                case "turnCW":
                    actors.get(i).setRotation(actors.get(i).getRotation() + 5);
                    break;
                case "speedUp":
                    actors.get(i).setVelocity(actors.get(i).getVelocity() + 2);
                    break;
            }
        }
        else if (role.getClass().equals(SpaceCannon.class)) {
            switch (s) {
                case "turnCCW":
                    actors.get(i).getCannon().setRotation(actors.get(i).getCannon().getRotation() - 5);
                    break;
                case "turnCW":
                    actors.get(i).getCannon().setRotation(actors.get(i).getCannon().getRotation() + 5);
                    break;
                case "fire":
                    actors.get(i).getCannon().fire();
                    break;
            }
        }
    }


    public void join(int i, String role)
    {
        switch(role) {
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

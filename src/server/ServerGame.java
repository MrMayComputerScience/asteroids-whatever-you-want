package server;

import mayflower.*;
import mayflower.net.Server;

import javax.print.attribute.standard.Severity;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class ServerGame extends MayflowerHeadless {
    //spaceObject needs to be changed to SpaceActor
    private Stack<ShipActor> actors;
    private Map<Integer, Actor> roles;
    private ServerWorld world;

    public ServerGame(Server server) {
        super("Server", 800, 600);
        actors = new Stack<>();
        world = new ServerWorld(server);
        roles = new HashMap<>();
        this.setWorld(world);
    }


    public void process(int i, String s) {

        Actor role = roles.get(i);
        if (role.getClass().equals(EngineerSystem.class)) {
            EngineerSystem engie = (EngineerSystem) role;
            switch (s) {
                case "addMovement":
                    if (engie.getReserveEnergy() > 0) {
                        engie.addShipEnergy();
                    }
                    break;
                case "addCannon":
                    if (engie.getReserveEnergy() > 0) {
                        engie.addCannonEnergy();
                    }
                    break;
                case "removeMovement":
                    if (engie.getShipEnergy() > 0)
                        engie.removeShipEnergy();
                    break;
                case "removeCannon":
                    if (engie.getCannonEnergy() > 0)
                        engie.removeCannonEnergy();
                    break;
            }
        }
        else if (role.getClass().equals(ShipActor.class)) {
            ShipActor ship = (ShipActor) role;
            switch (s) {
                case "turnCCW":
                    ship.setRotation(ship.getRotation() - 5);
                    ship.getCannon().setRotation(ship.getCannon().getRotation() - 5);
                    break;
                case "turnCW":
                    ship.setRotation(ship.getRotation() + 5);
                    ship.getCannon().setRotation(ship.getCannon().getRotation() + 5);
                    break;
                case "speedUp":
                    ship.accelerate(2,ship.getRotation());
                    break;
            }
        }
        else if (role.getClass().equals(SpaceCannon.class)) {
            SpaceCannon cannon = (SpaceCannon) role;
            switch (s) {
                case "turnCCW":
                    cannon.setRotation(cannon.getRotation() - 5);
                    break;
                case "turnCW":
                    cannon.setRotation(cannon.getRotation() + 5);
                    break;
                case "fire":
                    cannon.fire();
                    break;
            }
        }
    }








    public void join(int i, String role)
    {
        if(roles.size() % 3 == 0){
            actors.push(new ShipActor());
        }
        ShipActor actor = actors.peek();
        switch(role){
            case "Engineer":
                roles.put(i, actor.getEngie());
                break;
            case "Ship":
                roles.put(i, actor);
                break;
            case "Weapon":
                roles.put(i, actor.getCannon());
                break;
        }

        if(roles.size() % 3 ==0){
            int randx = (int)(Math.random() * 800);
            int randy = (int)(Math.random() * 600);
            world.addObject(actors.peek(), randx, randy);
        }

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

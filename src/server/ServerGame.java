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


        if (i % 3 == 1) {
            switch (direction[1]) {
                case "addShip":
                    if (actor.getEngie().getReserveEnergy() > 0) {
                        actor.getEngie().addShipEnergy();
                    }
                    break;
                case "addWeapon":
                    if (actor.getEngie().getReserveEnergy() > 0) {
                        actor.getEngie().addCannonEnergy();
                    }
                    break;
                case "removeShip":
                    if (actor.getEngie().getShipEnergy() > 0){
                        actor.getEngie().removeShipEnergy();
                    }
                    break;
                case "removeWeapon":
                    if (actor.getEngie().getShipEnergy() > 0){
                        actor.getEngie().removeCannonEnergy();
                    }
                    break;


            }
        }
        else if (i % 3 == 2) {
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
                    case "DecreaseSpeed":
                        actor.setVelocity(actor.getVelocity() + 2);
                        break;

                }

            } else {
                SpaceCannon spaceCannon = actor.getCannon();
                switch (direction[1]) {
                    case "TurnCCW":
                        spaceCannon.setRotation(spaceCannon.getRotation() - 5);
                        break;
                    case "TurnCW":
                        spaceCannon.setRotation(spaceCannon.getRotation() + 5);
                        break;
                    case "Fire":
                        //spaceCannon.fire();
                        break;
                }
            }
        }



    public void join(int i, String image)
    {
        ShipActor actor = new ShipActor();

        if(i%3==1) {
            world.addObject(actor, 5, 5);
            actors.put(i, actor);
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

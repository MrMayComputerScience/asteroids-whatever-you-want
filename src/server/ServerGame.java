package server;

import mayflower.*;
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

    public void process(int i, String s)
    {

    }




    public void join(int i, String role)
    {
        Actor actor = null;
        switch(role){
            case("Engineer"):
                actor = new EngineerSystem();break;
            case("Ship"):
                actor = new ShipActor();break;
            //case("Weapon"):
                //actor = new SpaceCannon();break;
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

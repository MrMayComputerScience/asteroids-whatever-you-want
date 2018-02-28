package server;

import mayflower.Actor;
import mayflower.Timer;
import mayflower.World;
import mayflower.net.Server;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.SortedMap;

public class ServerWorld extends World
{
    private Server server;
    private Timer timer;
    //TODO EVENTUALLY: Server world knows which id's it has so it's not broadcasting to server

    public ServerWorld(Server server)
    {
        addObject(new Asteroid(true),10,10);
        timer = new Timer(300000000);
        this.server = server;
    }

    @Override
    public void addObject(Actor a, int x, int y)
    {
        super.addObject(a, x, y);
        System.out.println("Adding: "+ a + " to " + x +", " + y);
    }

    @Override
    public void act()
    {
        if(timer.isDone())
        {
            List<SpaceObject> actors = getObjects(SpaceObject.class);
            for(SpaceObject actor : actors)
            {
                actor.move(actor.getVelocity());
            }

            System.out.println("tick: " + this.getObjects().size());
            System.out.println("tick: " + server);
            timer.reset();
            if(null != server)
            {
                server.send(this.toString());
            }
        }
    }

    public String toString()
    {
        String str = "";

        List<SpaceObject> actors = getObjects(SpaceObject.class);
        for(Actor actor : actors)
        {
            str += actor.toString() + ",";
        }

        return str;
    }
}

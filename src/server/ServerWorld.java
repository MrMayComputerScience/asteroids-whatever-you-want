package server;

import mayflower.Actor;
import mayflower.Timer;
import mayflower.World;
import mayflower.net.Server;

import java.util.*;

public class ServerWorld extends World
{
    private Server server;
    private Timer timer;
    private int currId;
    //TODO EVENTUALLY: Server world knows which id's it has so it's not broadcasting to server

    public ServerWorld(Server server)
    {
        timer = new Timer(300000);
        this.server = server;
        currId = 1;
    }

    @Override
    public void addObject(Actor a, int x, int y)
    {
        super.addObject(a, x, y);
        if(a instanceof SpaceObject){
            SpaceObject o = (SpaceObject)a;
            o.setId(currId++); //Postfix so it uses initial value
            System.out.println("ID:"+o.getId());
        }
        System.out.println("Adding: "+ a + " to " + x +", " + y);
    }

    @Override
    public void act()
    {
        if(timer.isDone())
        {
            List<SpaceObject> actors = new ArrayList<>();
            for(SpaceObject spaceObject:getObjects(SpaceObject.class))
                actors.add(spaceObject);

            for(SpaceObject actor : actors)
            {
                double x = actor.getVelocity().getX();
                double y = actor.getVelocity().getY();
                actor.setLocation(actor.getX() + x, actor.getY() + y);
            }


            timer.reset();
            if(null != server)
            {
                server.send(this.toString());
            }
        }
        if(getObjects(Collectable.class).size()==0)
        {
            Collectable collectable = new Collectable();
            addObject(collectable,collectable.getX(),collectable.getY());
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

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
    private Map<Integer, Actor> actors;
    private ServerWorld world;

    public ServerGame(Server server)
    {
        super("Server", 800, 600);
        actors = new HashMap<Integer, Actor>();

        world = new ServerWorld(server);
        this.setWorld(world);
    }

    public void process(int i, String s)
    {
        Actor actor = actors.get(i);
        if(actor != null)
        {
            switch(s)
            {
                case "turn":
                    actor.setRotation(Direction.NORTH);
                    break;
                case "down":
                    actor.setRotation(Direction.SOUTH);
                    break;
                case "left":
                    actor.setRotation(Direction.WEST);
                    break;
                case "right":
                    actor.setRotation(Direction.EAST);
                    break;
            }
            //actor.move(10);
        }
    }

    public void join(int i, String image)
    {
        Actor actor = new BoxActor(image);
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

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
    private Map<Integer, SpaceObject> actors;
    private ServerWorld world;

    public ServerGame(Server server)
    {
        super("Server", 800, 600);
        actors = new HashMap<Integer, SpaceObject>();

        world = new ServerWorld(server);
        this.setWorld(world);
    }

    public void process(int i, String s)
    {
        String[] system = s.split(":");
        String[] direction = system[1].split(" ");
        SpaceObject actor = actors.get(i);
        //replace the classes with system classes
//        if(actor.getClass().equals(null))
        if(actor != null)
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
                case "turn":
                    actor.setRotation(Direction.NORTH);
                    break;
                case "down":
                    actor.setRotation(Direction.SOUTH);
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
        }
    }

    public void join(int i, String image)
    {

        SpaceObject actor = new SpaceObject(image);
        int x = 5;//(int)(Math.random() * 700) + 50;
        int y = 5;//(int)(Math.random() * 500) + 50;
//        world.addObject(actor, x, y);

//        actors.put(i, actor);
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

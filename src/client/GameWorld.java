package client;

import mayflower.Actor;
import mayflower.World;
import server.smallAsteriod;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class GameWorld extends World
{
    private InputManager im;
    private Queue<List<Actor>> updates;

    public GameWorld(InputManager im)
    {
        this.im = im;
        setBackground("rsrc/MPBackground.jpg");
        updates = new LinkedList<>();
        addObject(new smallAsteriod(10),10,20);
    }

    public void update(List<Actor> actors)
    {
        updates.add(actors);

    }

    private void redraw(){
        if(updates.isEmpty()){
            return;
        }

        List<Actor> actors = updates.remove();

        //remove all GameActor objects
        this.removeObjects(this.getObjects(GameActor.class));

        //add new objects
        for(Actor actor : actors)
        {
            this.addObject(actor, actor.getX(), actor.getY());
        }

    }

    @Override
    public void act()
    {
        im.scan();
        redraw();
    }
}

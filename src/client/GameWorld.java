package client;

import mayflower.Actor;
import mayflower.World;

import java.util.ArrayList;
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
        updates = new LinkedList<>();
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
        if(updates.size()==0) {

            //add new objects
            for (Actor actor : actors) {
                this.addObject(actor, actor.getX(), actor.getY());
            }
            return;
        }
        List<Actor> actors1 = updates.remove();
        for(Actor actor :actors)
        {
            Actor actor1 = actors1.get(actors.indexOf(actor));
            if(actor1!=null)
            {
                new GameActor(actor,actor.getX(),actor.getY(),actor.getRotation(),actor1.getX(),actor1.getY(),actor1.getRotation(),0.5);
            }
        }

    }

    @Override
    public void act()
    {
        im.scan();
        redraw();
    }
}

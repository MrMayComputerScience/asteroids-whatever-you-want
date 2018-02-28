package client;

import mayflower.Actor;
import mayflower.World;


import java.util.*;


public class GameWorld extends World
{
    private InputManager im;
    private Queue<Map<Integer, Actor>> updates;

    public GameWorld(InputManager im)
    {
        this.im = im;
        updates = new LinkedList<>();
    }

    public void update(Map<Integer, Actor> actors)
    {
        updates.add(actors);

    }

    private void redraw(){
        if(updates.isEmpty()){
            return;
        }
        Map<Integer, Actor> actors = updates.remove();
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
            int index = actors1.indexOf(actor);
            Actor actor1;
            if(index!=-1)
            {
                actor1 = actors1.get(index);
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

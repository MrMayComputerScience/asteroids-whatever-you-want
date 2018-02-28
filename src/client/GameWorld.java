package client;

import mayflower.Actor;
import mayflower.World;


import java.util.*;


public class GameWorld extends World
{
    private InputManager im;
    private Queue<Map<Integer, Actor>> updates;
    private Map<Map<Integer, Actor>, Long> timeOfUpdate;
    private Long time;
    private Map<Integer, Actor> actors;
    private Map<Integer, Actor> actors1;

    public GameWorld(InputManager im)
    {
        this.im = im;
        updates = new LinkedList<>();
        timeOfUpdate = new HashMap<>();
        time = System.nanoTime();
    }

    public void update(Map<Integer, Actor> actors)
    {
        updates.add(actors);
        timeOfUpdate.put(actors, System.nanoTime());

    }

    private void redraw(){
        this.removeObjects(this.getObjects(GameActor.class));
        if(actors == null && actors1 == null){

            if(updates.size()<2){

                return;
            }
            else{
                actors = updates.remove();
                actors1 = updates.remove();
            }
        }

        Long time1 = timeOfUpdate.get(actors);
        Long time2 = timeOfUpdate.get(actors1);
        Long timeDiff = time2 - time1;
//        System.out.println((double) (System.nanoTime() - timeDiff - time1) / timeDiff + " "+time1+" "+time2);



        if((double) (System.nanoTime() - timeDiff - time1)<timeDiff) {
            for (Integer id : actors.keySet()) {
                Actor actor = actors.get(id);
                Actor actor1 = actors1.get(id);
                if(actor1 == null){
                    GameActor add = new GameActor(actor, actor.getX(), actor.getY(), actor.getRotation(), actor.getX(), actor.getY(), actor.getRotation(), 0);
                    addObject(add, add.getX(), add.getY());
                }

                GameActor add = new GameActor(actor, actor.getX(), actor.getY(), actor.getRotation(), actor1.getX(), actor1.getY(), actor1.getRotation(), (double) (System.nanoTime() - timeDiff - time1)/timeDiff);
                addObject(add, add.getX(), add.getY());
            }
        }
        else if(!updates.isEmpty()){
            actors = actors1;
            actors1 = updates.remove();
        }
        else{return;}

    }

    @Override
    public void act()
    {
        im.scan();
        redraw();
    }
}

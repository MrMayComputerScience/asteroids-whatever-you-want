package client;


import mayflower.Actor;
import mayflower.Color;
import mayflower.Mayflower;
import mayflower.World;
import server.ShipActor;


import java.util.*;


public class GameWorld extends World
{
    private InputManager im;
    private Queue<List<Actor>> updates;
    private String role;
    private int energy;

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
        ShipActor shipActor = new ShipActor();
        shipActor.getEngie().addCannonEnergy();
        if(updates.size()==0) {

            //add new objects
            for (Actor actor : actors) {
                this.addObject(actor, actor.getX(), actor.getY());
                if(actor.getClass().equals(ShipActor.class))
                    shipActor = (ShipActor)actor;
            }

        }
        else
        {
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
                if(actor.getClass().equals(ShipActor.class))
                    shipActor = (ShipActor)actor;
            }
        }
        Set keys = new HashSet();
        for(String s: getTexts().keySet())
            keys.add(s);

        for(Object s:keys)
        {
            getTexts().remove(s);
        }
        Color supercalafragilistic;

        if(role==null)
        {

            supercalafragilistic = Color.MEGENTA;
        }
        else if(role.equals("Ship"))
        {

            supercalafragilistic = Color.BLUE;
        }
        else if(role.equals("Weapon"))
        {

            supercalafragilistic = Color.RED;
        }
        else
        {

            supercalafragilistic = Color.GREEN;
        }
        showText(String.valueOf("Points:"+0),32,650,32, supercalafragilistic);
        showText("Energy:"+energy,32,650,64, supercalafragilistic);
    }

    @Override
    public void act()
    {
        im.scan();
        redraw();
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

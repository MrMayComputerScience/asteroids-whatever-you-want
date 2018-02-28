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

    private String role;
    private int energy;
    private int score;

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
        showText(String.valueOf("Points:"+score),32,650,32, supercalafragilistic);
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
    public void setScore(int score)
    {
        this.score = score;
    }
}

package client;

import mayflower.Actor;
import mayflower.Color;
import mayflower.World;
import server.Collectable;


import java.util.*;


public class GameWorld extends World {
    private InputManager im;
    private Queue<Map<Integer, Actor>> updates;
    private Map<Map<Integer, Actor>, Long> timeOfUpdate;
    private Long time;
    private Map<Integer, Actor> actors;
    private Map<Integer, Actor> actors1;
    private String role;
    private int energy;
    private int score;

    public GameWorld(InputManager im) {
        this.im = im;
        updates = new LinkedList<>();
        timeOfUpdate = new HashMap<>();
        time = System.nanoTime();
        energy = 0;
        score = 0;
        setPaintOrder(PriorityActor.class, GameActor.class);
    }

    public void update(Map<Integer, Actor> actors) {
        updates.add(actors);
        timeOfUpdate.put(actors, System.nanoTime());

    }

    private void redraw() {
        List objects = new ArrayList();
        for (Object object : getObjects(GameActor.class))
            objects.add(object);
        if (actors == null && actors1 == null) {

            if (updates.size() < 2) {

                return;
            } else {
                actors = updates.remove();
                actors1 = updates.remove();
            }
        }

        Long time1 = timeOfUpdate.get(actors);
        Long time2 = timeOfUpdate.get(actors1);
        Long timeDiff = time2 - time1;
//        System.out.println((double) (System.nanoTime() - timeDiff - time1) / timeDiff + " "+time1+" "+time2);
        Set keys = new HashSet();
        for (String s : getTexts().keySet())
            keys.add(s);

        for (Object s : keys) {
            getTexts().remove(s);
        }
        Color supercalafragilistic;

        if (role == null) {

            supercalafragilistic = Color.MEGENTA;
        } else if (role.equals("Ship")) {
            supercalafragilistic = Color.BLUE;
        } else if (role.equals("Weapon")) {

            supercalafragilistic = Color.RED;
        } else {

            supercalafragilistic = Color.GREEN;
        }
        showText(String.valueOf("Points:" + score), 32, 650, 32, supercalafragilistic);
        showText("Energy:" + energy, 32, 650, 64, supercalafragilistic);
        showText(role, 32, 0, 32, supercalafragilistic);


        if ((double) (System.nanoTime() - timeDiff - time1) < timeDiff) {
            for (Integer id : actors.keySet()) {
                Actor actor = actors.get(id);
                Actor actor1 = actors1.get(id);
                if (actor1 == null) {
                    GameActor add = new GameActor(actor, actor.getX(), actor.getY(), actor.getRotation(), actor.getX(), actor.getY(), actor.getRotation(), 0);
                    addObject(add, add.getX(), add.getY());
                } else {
                    GameActor add = new GameActor(actor, actor.getX(), actor.getY(), actor.getRotation(), actor1.getX(), actor1.getY(), actor1.getRotation(), (double) (System.nanoTime() - timeDiff - time1) / timeDiff);
                    addObject(add, add.getX(), add.getY());
                }
            }
            removeObjects(objects);

        } else if (!updates.isEmpty()) {
            actors = actors1;
            actors1 = updates.remove();
        } else {
            return;
        }

    }

    public void setRole(String role) {
        this.role = role;
        System.out.println(role + ":" + this.role);
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void act() {
        im.scan();
        redraw();
    }

    public String getRole() {
        return role;
    }
}


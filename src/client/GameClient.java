package client;

import mayflower.Actor;
import mayflower.Mayflower;
import mayflower.net.Client;
import java.util.*;


public class GameClient extends Client implements GameMode
{
    private GameWorld gameWorld;
    private Queue<String> serverUpdates;

    public GameClient()
    {
        this("localhost");
    }

    public GameClient(String ip)
    {
        System.out.println("Connecting");
        this.connect(ip, 1234);
        System.out.println("Connected");
        serverUpdates = new LinkedList();
    }

    public void setGameWorld(GameWorld world){
        this.gameWorld = world;
    }


    @Override
    public void process(String s)
    {
        Map<Integer, Actor> actors = new HashMap<>();
        String[] allActors = s.split(",");
        for(String actor : allActors)
        {
            if(!"".equals(actor)) {
                String individualActor = actor.split(":")[0];
                int id;
                GameActor a;
                switch(individualActor){
                    case("ship"):
                        String[] shipParams = actor.split(":")[1].split(" ");
                        id = Integer.parseInt(shipParams[0]);
                        int shipX = Integer.parseInt(shipParams[1]);
                        int shipY = Integer.parseInt(shipParams[2]);
                        int shipR = Integer.parseInt(shipParams[3]);

                        String[] energy = shipParams[4].split("/");
                        int reserve = Integer.parseInt(energy[0]);
                        int ship = Integer.parseInt(energy[1]);
                        int weapon = Integer.parseInt(energy[2]);
                        a = new GameActor("rsrc/SpaceshipNoCannon.png", shipX, shipY, shipR);
                        actors.put(id, a);
                        break;
                    case("asteroid"):
                        String[] asteroidParams = actor.split(":")[1].split(" ");
                        id = Integer.parseInt(asteroidParams[0]);
                        String size = asteroidParams[1];
                        int asteroidX = Integer.parseInt(asteroidParams[2]);
                        int asteroidY = Integer.parseInt(asteroidParams[3]);
                        int asteroidR = Integer.parseInt(asteroidParams[4]);

                        if(size.equals("small")){
                            actors.put(id, new GameActor("rsrc/SmallAsteroid.png", asteroidX, asteroidY, asteroidR));
                        }
                        else{
                            actors.put(id, new GameActor("rsrc/LargeAsteroid.png", asteroidX, asteroidY, asteroidR));
                        }

                        break;
                    case("collectable"):
                        String[] collectableParams = actor.split(":")[1].split(" ");
                        id = Integer.parseInt(collectableParams[0]);
                        int collectableX = Integer.parseInt(collectableParams[1]);
                        int collectableY = Integer.parseInt(collectableParams[2]);

                        actors.put(id, new GameActor("rsrc/Collectable.png", collectableX, collectableY,0));

                        break;
                    case("lazer"):
                        String[] lazarParams = actor.split(":")[1].split(" ");
                        id = Integer.parseInt(lazarParams[0]);
                        int lazerX = Integer.parseInt(lazarParams[1]);
                        int lazerY = Integer.parseInt(lazarParams[2]);
                        int lazerR = Integer.parseInt(lazarParams[3]);

                        actors.put(id, new GameActor("rsrc/Laser.png", lazerX, lazerY,lazerR));

                        break;
                    case("cannon"):
                        String[] cannonParams = actor.split(":")[1].split(" ");
                        id = Integer.parseInt(cannonParams[0]);
                        int cannonX = Integer.parseInt(cannonParams[1]);
                        int cannonY = Integer.parseInt(cannonParams[2]);
                        int cannonR = Integer.parseInt(cannonParams[3]);

                        actors.put(id, new GameActor("rsrc/LaserCannon.png", cannonX, cannonY, cannonR));

                        break;
                    case "debug":
                        System.out.println("DEBUG MSG FROM SERVER: "+actor.split(":")[1]);
                        break;
                }
            }
        }
        if(null != gameWorld && null != actors) {
            gameWorld.update(actors);
        }
    }

    @Override
    public void onDisconnect(String s) {
        System.out.println("Disconnected from server");
    }

    @Override
    public void onConnect() {
        System.out.println("Connected to server!");
    }


    public void processPress(String action) {
        System.out.println("Sending: " + action);
        send(action);
    }


    public void processRelease(String action) {

    }
}

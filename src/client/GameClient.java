package client;

import mayflower.Actor;
import mayflower.Mayflower;
import mayflower.net.Client;

import java.util.LinkedList;
import java.util.List;

public class GameClient extends Client implements GameMode
{
    private GameWorld gameWorld;



    public GameClient()
    {
        this("localhost");
    }

    public GameClient(String ip)
    {
        System.out.println("Connecting");
        this.connect(ip, 1234);
        System.out.println("Connected");
    }

    public void setGameWorld(GameWorld world){
        this.gameWorld = world;
    }


    @Override
    public void process(String s)
    {

        List<Actor> actors = new LinkedList<Actor>();
        String[] allActors = s.split(",");
        for(String actor : allActors)
        {
            if(!"".equals(actor)) {
                String individualActor = actor.split(":")[0];
                switch(individualActor){
                    case("ship"):
                        String[] shipParams = actor.split(":")[1].split(" ");
                        int shipX = Integer.parseInt(shipParams[0]);
                        int shipY = Integer.parseInt(shipParams[1]);
                        int shipR = Integer.parseInt(shipParams[2]);

                        String[] energy = shipParams[3].split("/");
                        int reserve = Integer.parseInt(energy[0]);
                        int ship = Integer.parseInt(energy[1]);
                        int weapon = Integer.parseInt(energy[2]);

                        actors.add(new GameActor("rsrc/SpaceshipNoCannon.png", shipX, shipY, shipR));

                        break;
                    case("asteroid"):
                        String[] asteroidParams = actor.split(":")[1].split(" ");
                        String size = asteroidParams[0];
                        int asteroidX = Integer.parseInt(asteroidParams[1]);
                        int asteroidY = Integer.parseInt(asteroidParams[2]);
                        int asteroidR = Integer.parseInt(asteroidParams[3]);

                        if(size.equals("small")){
                            actors.add(new GameActor("rsrc/SmallAsteroid.png", asteroidX, asteroidY, asteroidR));
                        }
                        else{
                            actors.add(new GameActor("rsrc/LargeAsteroid.png", asteroidX, asteroidY, asteroidR));
                        }

                        break;
                    case("collectable"):
                        String[] collectableParams = actor.split(":")[1].split(" ");
                        int collectableX = Integer.parseInt(collectableParams[0]);
                        int collectableY = Integer.parseInt(collectableParams[1]);

                        actors.add(new GameActor("rsrc/Collectable.png", collectableX, collectableY,0));

                        break;
                    case("lazer"):
                        String[] lazarParams = actor.split(":")[1].split(" ");
                        int lazerX = Integer.parseInt(lazarParams[0]);
                        int lazerY = Integer.parseInt(lazarParams[1]);
                        int lazerR = Integer.parseInt(lazarParams[2]);

                        actors.add(new GameActor("rsrc/Lazer.png", lazerX, lazerY,lazerR));

                        break;
                    case("cannon"):
                        String[] cannonParams = actor.split(":")[1].split(" ");
                        int cannonX = Integer.parseInt(cannonParams[0]);
                        int cannonY = Integer.parseInt(cannonParams[1]);
                        int cannonR = Integer.parseInt(cannonParams[2]);

                        actors.add(new GameActor("rsrc/LazerWorld.png", cannonX, cannonY, cannonR));

                        break;
                }
            }
        }
        if(null != gameWorld) {
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

    @Override
    public void processPress(String action) {
        System.out.println("Sending: " + action);
        send(action);
    }

    @Override
    public void processRelease(String action) {

    }
}

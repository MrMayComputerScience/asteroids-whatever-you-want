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
                        int shipV = Integer.parseInt(shipParams[3]);

                        String[] energy = shipParams[4].split("/");
                        int reserve = Integer.parseInt(energy[0]);
                        int ship = Integer.parseInt(energy[1]);
                        int weapon = Integer.parseInt(energy[2]);
                        break;
                    case("asteroid"):
                        String[] asteroidParams = actor.split(":")[1].split(" ");
                        String size = asteroidParams[0];
                        int asteroidX = Integer.parseInt(asteroidParams[0]);
                        int asteroidY = Integer.parseInt(asteroidParams[1]);
                        int asteroidR = Integer.parseInt(asteroidParams[2]);
                        int asteroidV = Integer.parseInt(asteroidParams[3]);
                        break;
                    case("collectable"):
                        String[] collectableParams = actor.split(":")[1].split(" ");
                        int collectableX = Integer.parseInt(collectableParams[0]);
                        int collectableY = Integer.parseInt(collectableParams[1]);
                        break;
                    case("lazer"):
                        String[] lazarParams = actor.split(":")[1].split(" ");
                        int lazerX = Integer.parseInt(lazarParams[0]);
                        int lazerY = Integer.parseInt(lazarParams[1]);
                        int lazerR = Integer.parseInt(lazarParams[2]);
                        int lazerV = Integer.parseInt(lazarParams[3]);
                        break;
                    case("turret"):
                        String[] turretParams = actor.split(":")[1].split(" ");
                        int turretR = Integer.parseInt(turretParams[0]);
                        break;
                }
            }

            String[] parts2 = part.split(",");
            String img = "rsrc/"+parts2[0]+".png";
            int x = Integer.parseInt(parts2[1]);
            int y = Integer.parseInt(parts2[2]);
            int r = Integer.parseInt(parts2[3]);

            actors.add(new GameActor(img, x, y, r));
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

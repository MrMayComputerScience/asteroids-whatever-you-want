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
        String[] parts = s.split(":");
        for(String part : parts)
        {
            if(!"".equals(part)) {
                String[] parts2 = part.split(",");
                String img = "rsrc/"+parts2[0]+".png";
                int x = Integer.parseInt(parts2[1]);
                int y = Integer.parseInt(parts2[2]);
                int r = Integer.parseInt(parts2[3]);

                actors.add(new GameActor(img, x, y, r));
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

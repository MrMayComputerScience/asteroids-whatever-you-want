package client;

import mayflower.Keyboard;
import mayflower.Mayflower;

import java.util.HashMap;
import java.util.Map;

public class ClientRunner extends Mayflower
{
    public ClientRunner()
    {
        super("Client", 800, 600);
    }
    @Override
    public void init()
    {
        String ip = Mayflower.ask("Connect to what IP address?");
        if("".equals(ip))
            ip = "localhost";

        GameClient client = new GameClient(ip);
        InputManager im = new InputManager(client);

        Map<Integer, String> keys = new HashMap<Integer, String>();
        keys.put(Keyboard.KEY_LEFT, "turnCCW");
        keys.put(Keyboard.KEY_RIGHT, "turnCW");
        keys.put(Keyboard.KEY_UP, "speedUp");
        keys.put(Keyboard.KEY_DOWN, "speedDown");

        keys.put(Keyboard.KEY_SPACE, "fire");

        keys.put(Keyboard.KEY_W, "addWeapon");
        keys.put(Keyboard.KEY_S, "removeWeapon");

        keys.put(Keyboard.KEY_E, "addShip");
        keys.put(Keyboard.KEY_D, "removeShip");



        im.setKeyMap(keys);


        GameWorld gameWorld = new GameWorld(im);
        client.setGameWorld(gameWorld);

        Mayflower.setWorld(gameWorld);
    }

    public static void main(String[] args)
    {
        new ClientRunner();
    }
}

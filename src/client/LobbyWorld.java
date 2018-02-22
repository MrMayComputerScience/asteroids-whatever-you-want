package client;

import mayflower.World;

public class LobbyWorld extends World {

    public LobbyWorld(String bg){
        setBackground(bg);
        showText("Waiting...", getWidth()/2, getHeight()/2);
        showText("0/3", getWidth()/2, getHeight()/2 + 48);
    }

    @Override
    public void act() {

    }
}

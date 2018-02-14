package client;

import mayflower.World;
import mayflower.ui.Button;

import mayflower.event.EventListener;

/**
 * Created by s581467 on 2/6/2018.
 */

public class ChooseWorld extends World implements EventListener{

    private GameClient client;

    public ChooseWorld(){
        setBackground("rsrc/MPBackground.jpg");

        Button green = new Button("rsrc/green.png", "PickedColor green");
        green.addEventListener(this);

        Button red = new Button("rsrc/red.png", "PickedColor red");
        red.addEventListener(this);

        Button black = new Button("rsrc/black.png", "PickedColor black");
        black.addEventListener(this);

        Button blue = new Button("rsrc/blue.png", "PickedColor blue");
        blue.addEventListener(this);

        addObject(green, 120+50, 400);
        addObject(red, 240+50, 400);
        addObject(black, 360+50, 400);
        addObject(blue, 480+50, 400);
    }


    public void act(){

    }

    public void setClient(GameClient client){
        this.client = client;
    }

    @Override
    public void onEvent(String s) {
        System.out.println(s);
        client.send(s);
    }
}

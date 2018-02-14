package client;

import mayflower.Label;
import mayflower.World;

/**
 * Created by s581467 on 2/8/2018.
 */
public class WaitingWorld extends World {
    private int counter;
    public WaitingWorld(){
        counter = 4;
        setBackground("rsrc/MPBackground.jpg");
        System.out.println(counter);
    }

    public void act(){

    }

    public void setCounter(int counter) {
        this.counter = counter;
        System.out.println("lol");
        showText("Waiting For "+counter+" More Players To Join", 100, 100);
    }
}

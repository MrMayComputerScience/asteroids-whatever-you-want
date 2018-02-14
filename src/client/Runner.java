package client;

import mayflower.Mayflower;
import mayflower.World;
import mayflower.test.TestWorld;

public class Runner {

    public static void main(String[] args){
        World start = new TestWorld();
        Mayflower.setWorld(start);
    }
}

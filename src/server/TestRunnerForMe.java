package server;

import mayflower.Direction;
import mayflower.Mayflower;
import mayflower.World;

public class TestRunnerForMe extends Mayflower{
    public TestRunnerForMe(String title, int width, int height) {
        super(title, width, height);
    }

    public static void main(String[] args){
        new TestRunnerForMe("Test", 800,600);
    }

    @Override
    public void init() {
        World world = new ServerWorld(null);
        SpaceObject o = new SpaceObject("rsrc/Spaceship.png");
        o.setVelocity(5);
        world.addObject(o,50,50);
        o.setRotation(45);
        Mayflower.setWorld(world);
    }
}
package server;

public class SpaceLaser extends SpaceObject {
    public SpaceLaser() {
        super("rsrc/Laser.png");
    }
    public String toString(){
        return String.format("lazer:%d %d %d %d", getId(), getX(), getY(), getRotation());
//        return String.format("lazer:%d %d %d", getX(), getY(), getRotation());

    }

    @Override
    public void act() {
        
    }
}

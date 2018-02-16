package server;

public class SpaceCannon extends SpaceObject{
    private int energy;
    public SpaceCannon(String img) {
        super(img);
        setVelocity(0);
    }
    public String toString(){
        return String.format("cannon:%d %d %d", getX(),getY(),getRotation());
    }
}

package server;

public class ShipActor extends SpaceObject{
    private SpaceCannon cannon;
    private EngineerSystem engie;

    public ShipActor(String img) {
        super(img);
    }

    @Override
    public void act() {
        super.act();
        cannon.setLocation(getX(), getY());
    }

    public String toString(){
        return String.format("ship:%d %d %d %d/%d/%d", getX(), getY(), getRotation(),
                engie.getReserveEnergy(), engie.getShipEnergy(), engie.getCannonEnergy());
    }

    public EngineerSystem getEngie() {
        return engie;
    }
}

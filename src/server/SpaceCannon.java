package server;

public class SpaceCannon extends SpaceObject{
    private EngineerSystem engie;
    public SpaceCannon(EngineerSystem engie) {
        super("rsrc/LaserCannon.png");
        this.engie = engie;
        setVelocity(Vector.ZERO);
        priority = 0;
    }
    public void act(){
        //No-op
    }
    public SpaceLaser fire(){
        SpaceLaser laser = new SpaceLaser();

        if(getWorld() != null && engie.getCannonEnergy() > 0){
            //Add the laser at our center TODO Fix it
            getWorld().addObject(laser, getCenterX(), getCenterY());
            laser.setRotation(getRotation());
            laser.accelerate(80, getRotation());//TODO: Find good way to do energy-based velocity(???)
            return laser;
        }
        else{
            System.err.printf("Failure to fire:\n\t-Cannon Energy: %d\n\t-getWorld()==null: %s\n",
                    engie.getCannonEnergy(), Boolean.toString(getWorld() == null));
            return null;
        }
    }
    public String toString(){
        return String.format("cannon:%d %d %d %d", getId(), getX(),getY(),getRotation());
//        return String.format("cannon:%d %d %d", getX(),getY(),getRotation());
    }
}

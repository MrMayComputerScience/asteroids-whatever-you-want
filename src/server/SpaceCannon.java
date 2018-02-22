package server;

public class SpaceCannon extends SpaceObject{
    private EngineerSystem engie;
    public SpaceCannon(String img, EngineerSystem engie) {
        super(img);
        this.engie = engie;
        setVelocity(0);
    }
    public SpaceLaser fire(){
        SpaceLaser laser = new SpaceLaser("rsrc/Laser.png");
        if(getWorld() != null && engie.getCannonEnergy() > 0){
            getWorld().addObject(laser, getCenterX(), getCenterY());
            laser.setRotation(getRotation());
            laser.setVelocity(engie.getCannonEnergy() * 5);//TODO: Find good way to do energy-based velocity(???)
            laser.move(10); //TODO: Get good radius to fire from
            return laser;
        }
        else{
            System.err.printf("Failure to fire:\n\t-Cannon Energy: %d\n\t-getWorld()==null: %s",
                    engie.getCannonEnergy(), Boolean.toString(getWorld() == null));
            return null;
        }
    }
    public String toString(){
        return String.format("cannon:%d %d %d", getX(),getY(),getRotation());
    }
}

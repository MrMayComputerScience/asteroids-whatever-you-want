package server;

import javafx.util.Pair;
import mayflower.Actor;
import mayflower.MayflowerImage;

import java.util.Map;

public class EngineerSystem extends Actor{
    private int shipEnergy;
    private int cannonEnergy;
    private int totalEnergy;

    public EngineerSystem(){
        totalEnergy = 5;
        setImage(new MayflowerImage("rsrc/laser.png"));
        getImage().setTransparency(0);
    }

    @Override
    public void act() {
        //no-op
    }

    public int getShipEnergy() {
        return shipEnergy;
    }

    public int getCannonEnergy() {
        return cannonEnergy;
    }
    public void addShipEnergy(){
        if(getReserveEnergy() > 0){
            shipEnergy++;
        }
    }
    public void addCannonEnergy(){
        if(getReserveEnergy() > 0){
            cannonEnergy++;
        }
    }
    public void removeShipEnergy(){
        if(getReserveEnergy() < totalEnergy){
            shipEnergy--;
        }
    }
    public void removeCannonEnergy(){
        if(getReserveEnergy() < totalEnergy){
            cannonEnergy--;
        }
    }
    public int getReserveEnergy(){
        if(totalEnergy - cannonEnergy - shipEnergy < 0){
            int random = (int) Math.random()*2;
            if(random == 0){
                cannonEnergy--;
            }
            else{
                shipEnergy--;
            }
            return 0;
        }
        return totalEnergy - cannonEnergy - shipEnergy;
    }

    public void removeTotalEnergy()
    {
        totalEnergy--;
    }

    public int getTotalEnergy(){return totalEnergy;}
}

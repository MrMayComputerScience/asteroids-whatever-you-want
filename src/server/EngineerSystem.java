package server;

import javafx.util.Pair;
import mayflower.Actor;

import java.util.Map;

public class EngineerSystem extends Actor{
    private int shipEnergy;
    private int cannonEnergy;

    public EngineerSystem(){
<<<<<<< HEAD

=======
>>>>>>> Mason
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
        if(getReserveEnergy() < 5){
            shipEnergy++;
        }
    }
    public void removeCannonEnergy(){
        if(getReserveEnergy() < 5){
            cannonEnergy++;
        }
    }
    public int getReserveEnergy(){
        return 5 - cannonEnergy - shipEnergy;
    }
}

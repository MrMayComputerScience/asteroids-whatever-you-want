package server;

import mayflower.Actor;

public class EngineerSystem extends Actor{
    private int shipEnergy;
    private int cannonEnergy;
    private int totalEnergy;

    public EngineerSystem()
    {
        totalEnergy = 5;
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
        return totalEnergy - cannonEnergy - shipEnergy;
    }
    public void removeTotalEnergy()
    {
        totalEnergy--;
    }

    public int getTotalEnergy() {
        return totalEnergy;
    }
}

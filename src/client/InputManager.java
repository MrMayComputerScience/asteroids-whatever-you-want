package client;

import mayflower.Actor;
import mayflower.Mayflower;

import java.util.Map;

public class InputManager extends Actor {
    private Map<Integer, String> keyMap;

    public InputManager(){

    }
    public void setKeyMap(Map<Integer, String> map){
        keyMap = map;
    }
    public void scan(){
        for(int key : keyMap.keySet()){
            if(Mayflower.isKeyDown(key)){
                //TODO something.process
            }
        }
    }

    @Override
    public void act() {

    }
}

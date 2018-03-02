
package server;


import client.GameActor;
import client.GameWorld;
import mayflower.Actor;

import mayflower.net.Server;

import java.util.*;

public class MultiplayerServer extends Server
{
    private ServerGame lobby;
    private List<ServerGame> games;
    private Queue<Integer> players;
    private Map<Integer, ServerGame> inttogame;
    private Map<Integer, String> inttorole;
    private List<String> rolesLeft;

    public MultiplayerServer()
    {
        super(1234);
        System.out.println("Server started.");

        players = new ArrayDeque<>();
        games = new ArrayList<>();
        inttogame = new HashMap<>();
        inttorole = new HashMap<>();
        rolesLeft = new ArrayList<>();

        rolesLeft.add("Engineer");
        rolesLeft.add("Ship");
        rolesLeft.add("Weapon");
    }
//10.11.1.106
    @Override
    public void process(int i, String s)
    {
        if(s.equals("Role plz"))
        {
            send(i,"Role"+":"+inttorole.get(i));
            return;
        }
        inttogame.get(i).process(i, s);
    }

    @Override
    public void onJoin(int i)
    {
        System.out.println("Joined: " + i);
        players.add(i);
        String role = rolesLeft.get((int) (Math.random()*rolesLeft.size()));
        inttorole.put(i, role);
        rolesLeft.remove(role);
        send(i, "debug:"+role);
        if(players.size()>=3){
            lobby = new ServerGame(this);
            for(int j=0; j<3; j++){
                int player = players.remove();
                lobby.join(player, inttorole.get(player));
                inttogame.put(player, lobby);
            }
            rolesLeft.add("Engineer");
            rolesLeft.add("Ship");
            rolesLeft.add("Weapon");
        }

    }

    @Override
    public void onExit(int i) {
        //TODO Fix(Support for multiple lobbies)
        int left = 4-players.size();
        for(int player: players){
            if(player==i){
                players.remove(player);
            }
            send(player, "PlayersLeft "+(left+1));
        }
        //lobby.leave(i);
        System.out.println("Left: " + i);

    }
}

<<<<<<< HEAD
=======

>>>>>>> Mason
package server;

import client.GameActor;
import client.GameWorld;
<<<<<<< HEAD
import mayflower.Actor;
=======
>>>>>>> Mason
import mayflower.net.Server;

import java.util.*;

public class MultiplayerServer extends Server
{
    private ServerGame lobby;
    private List<ServerGame> games;
    private Queue<Integer> players;
    private Map<Integer, ServerGame> inttogame;
<<<<<<< HEAD
    private Map<Integer, String> inttorole;
    private List<String> rolesLeft;
=======
    private Map<Integer, String> inttocolor;
>>>>>>> Mason

    public MultiplayerServer()
    {
        super(1234);
        System.out.println("Server started.");

        players = new ArrayDeque<>();
        games = new ArrayList<>();
        inttogame = new HashMap<>();
<<<<<<< HEAD
        inttorole = new HashMap<>();
        rolesLeft = new ArrayList<>();

        rolesLeft.add("Engineer");
        rolesLeft.add("Ship");
        rolesLeft.add("Weapon");

=======
        inttocolor = new HashMap<>();
>>>>>>> Mason
    }

    @Override
    public void process(int i, String s)
    {
<<<<<<< HEAD
        inttogame.get(i).process(i, s);
=======
        if(inttogame.get(i)!=null){
            inttogame.get(i).process(i, s);
            return;
        }
>>>>>>> Mason
    }

    @Override
    public void onJoin(int i)
    {
        System.out.println("Joined: " + i);

<<<<<<< HEAD
        players.add(i);
        String role = rolesLeft.get((int) Math.random()*rolesLeft.size());
        inttorole.put(i, role);
        rolesLeft.remove(role);

        if(players.size()>=3){
            lobby = new ServerGame(this);
            for(int j=0; j<3; j++){
                int player = players.remove();
                lobby.join(player, inttorole.get(player));
            }
            rolesLeft.add("Engineer");
            rolesLeft.add("Ship");
            rolesLeft.add("Weapon");
        }
=======
        //starting game

>>>>>>> Mason
    }

    @Override
    public void onExit(int i) {
        int left = 4-players.size();
        for(int player: players){
            if(player==i){
                players.remove(player);
            }
            send(player, "PlayersLeft "+(left+1));
        }
        lobby.leave(i);
        System.out.println("Left: " + i);

    }
}

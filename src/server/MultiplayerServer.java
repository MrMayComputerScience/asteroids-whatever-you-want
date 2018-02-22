
package server;

<<<<<<< HEAD

=======
import client.GameActor;
import client.GameWorld;
import mayflower.Actor;
>>>>>>> David's-Branch
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

    @Override
    public void process(int i, String s)
    {
        inttogame.get(i).process(i, s);
    }

    @Override
    public void onJoin(int i)
    {
        System.out.println("Joined: " + i);
<<<<<<< HEAD
        games.get(0).join(i,"rsrc/Spaceship.png");
        //starting game

=======
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
>>>>>>> David's-Branch
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

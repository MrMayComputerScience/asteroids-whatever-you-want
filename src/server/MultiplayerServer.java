
package server;

import client.GameActor;
import client.GameWorld;
import mayflower.net.Server;

import java.util.*;

public class MultiplayerServer extends Server
{
    private ServerGame lobby;
    private List<ServerGame> games;
    private Queue<Integer> players;
    private Map<Integer, ServerGame> inttogame;
    private Map<Integer, String> inttocolor;

    public MultiplayerServer()
    {
        super(1234);
        System.out.println("Server started.");

        players = new ArrayDeque<>();
        games = new ArrayList<>();
        inttogame = new HashMap<>();
        inttocolor = new HashMap<>();
    }

    @Override
    public void process(int i, String s)
    {
        if(inttogame.get(i)!=null){
            inttogame.get(i).process(i, s);
            return;
        }
    }

    @Override
    public void onJoin(int i)
    {
        System.out.println("Joined: " + i);

        //starting game

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

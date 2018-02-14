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

        //when the player selects a color
        if(s.split(" ")[0].equals("PickedColor")){
            inttocolor.put(i, s.split(" ")[1]);
            players.add(i);
            send(i, "ChangeMode Waiting");
            int left = 4-players.size();
            send("PlayersLeft "+left);

            if(players.size()>=4){
                lobby = new ServerGame(this);
                games.add(lobby);

                List<Integer> addPlayers = new LinkedList<>();

                for(int j=0; j<4; j++){
                    addPlayers.add(players.remove());
                }

                for(Integer player: addPlayers){
                    inttogame.put(player, lobby);
                    lobby.join(player, inttocolor.get(player));
                    send(player, "ChangeMode Play");
                }
            }
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

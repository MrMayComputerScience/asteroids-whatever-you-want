package server;

import mayflower.net.Server;

/**
 * Created by s581467 on 2/16/2018.
 */
public class TestServer extends Server {

    public TestServer(int port) {
        super(port);
    }

    @Override
    public void process(int i, String s) {

    }

    @Override
    public void onJoin(int i) {
        send("ship:100 200 25 1/2/3,asteroid:large 300 500 67");
    }

    @Override
    public void onExit(int i) {

    }
}

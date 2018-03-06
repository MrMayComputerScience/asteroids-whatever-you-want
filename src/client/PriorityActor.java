package client;

import mayflower.Actor;

public class PriorityActor extends GameActor{
    public PriorityActor(String img, int x, int y, int r) {
        super(img, x, y, r);
    }

    public PriorityActor(Actor actor, int x1, int y1, int r1, int x2, int y2, int r2, double p) {
        super(actor, x1, y1, r1, x2, y2, r2, p);
    }
}

package edu.hitsz.aircraft;

import edu.hitsz.strategy.ShootStrategy;

public abstract class OwnAircraft extends AbstractAircraft{

    public OwnAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }
}

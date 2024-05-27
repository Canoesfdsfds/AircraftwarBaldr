package edu.hitsz.factory.For_Aircraft;

import edu.hitsz.aircraft.Elite2Enemy;
import edu.hitsz.strategy.ScatterShootStrategy;
import edu.hitsz.aircraft.AbstractAircraft;

public class Elite2EnemyFactory extends EnemyAircraftFactory {
    @Override
    public AbstractAircraft createAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        AbstractAircraft aircraft = new Elite2Enemy(locationX, locationY, speedX, speedY, hp);
        aircraft.setShootStrategy(new ScatterShootStrategy());
        return aircraft;
    }
}
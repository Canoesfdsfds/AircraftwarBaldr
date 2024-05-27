package edu.hitsz.factory.For_Aircraft;

import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.strategy.DirectShootStrategy;
import edu.hitsz.aircraft.AbstractAircraft;

public class EliteEnemyFactory extends EnemyAircraftFactory {
    @Override
    public AbstractAircraft createAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        AbstractAircraft aircraft = new EliteEnemy(locationX, locationY, speedX, speedY, hp);
        aircraft.setShootStrategy(new DirectShootStrategy());
        return aircraft;
    }
}

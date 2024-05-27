package edu.hitsz.factory.For_Aircraft;

import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.strategy.NoneShootStrategy;
import edu.hitsz.aircraft.AbstractAircraft;

public class MobEnemyFactory extends EnemyAircraftFactory {
    @Override
    public AbstractAircraft createAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        AbstractAircraft aircraft = new MobEnemy(locationX, locationY, speedX, speedY, hp);
        aircraft.setShootStrategy(new NoneShootStrategy());
        return aircraft;
    }
}
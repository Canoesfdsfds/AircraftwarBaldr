package edu.hitsz.factory.For_Aircraft;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.strategy.DirectShootStrategyHero;
import edu.hitsz.aircraft.AbstractAircraft;

public class HeroAircraftFactory extends OwnAircraftFactory {
    @Override
    public AbstractAircraft createAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        AbstractAircraft aircraft = HeroAircraft.getInstance(locationX, locationY, speedX, speedY, hp);
        aircraft.setShootStrategy(new DirectShootStrategyHero());
        System.out.println("hero created");
        return aircraft;
    }
}
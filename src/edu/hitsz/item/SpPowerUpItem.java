package edu.hitsz.item;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.strategy.RingShootStrategyHero;

public class SpPowerUpItem extends BaseItem{
    public SpPowerUpItem(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void make_influence(HeroAircraft heroAircraft, int data) {
        heroAircraft.setShootStrategy(new RingShootStrategyHero());
    }
}

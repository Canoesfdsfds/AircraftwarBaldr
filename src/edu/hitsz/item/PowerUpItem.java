package edu.hitsz.item;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.strategy.ScatterShootStrategyHero;

public class PowerUpItem extends BaseItem{
    public PowerUpItem(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }
    public void make_influence(HeroAircraft heroAircraft, int dpower)
    {
        heroAircraft.setShootStrategy(new ScatterShootStrategyHero());
        return;
    }
}

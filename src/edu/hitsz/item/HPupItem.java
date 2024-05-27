package edu.hitsz.item;

import edu.hitsz.aircraft.HeroAircraft;

public class HPupItem extends BaseItem{
    public HPupItem(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void make_influence(HeroAircraft heroAircraft, int dhp) {
        heroAircraft.HPup(dhp);
        return;
    }
}

package edu.hitsz.item;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.basic.AbstractFlyingObject;

public class BoomItem extends BaseItem{
    public BoomItem(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }
    public void make_influence(HeroAircraft heroAircraft, int time)
    {
        return;
    }
}

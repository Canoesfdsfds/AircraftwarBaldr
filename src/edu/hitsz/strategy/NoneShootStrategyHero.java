package edu.hitsz.strategy;

import java.util.LinkedList;
import java.util.List;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;

public class NoneShootStrategyHero implements ShootStrategy{

    @Override
    public List<BaseBullet> shoot(AbstractAircraft aircraft, int power) {
        return new LinkedList<>();
    }

}

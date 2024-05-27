package edu.hitsz.factory.For_Aircraft;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.strategy.ShootStrategy;

interface AircraftFactory {
    public abstract AbstractAircraft createAircraft(int locationX, int locationY, int speedX, int speedY, int hp);
}
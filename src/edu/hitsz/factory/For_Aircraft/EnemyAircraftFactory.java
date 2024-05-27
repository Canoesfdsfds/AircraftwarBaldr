package edu.hitsz.factory.For_Aircraft;

import edu.hitsz.aircraft.AbstractAircraft;

public abstract class EnemyAircraftFactory implements AircraftFactory{
    public abstract AbstractAircraft createAircraft(int locationX, int locationY, int speedX, int speedY, int hp);
}
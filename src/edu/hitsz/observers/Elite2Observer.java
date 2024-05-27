package edu.hitsz.observers;

import edu.hitsz.aircraft.Elite2Enemy;

public class Elite2Observer implements Observer{
    private Elite2Enemy elite2Enemy;
    public Elite2Observer(Elite2Enemy elite2Enemy)
    {
        this.elite2Enemy = elite2Enemy;
    }
    public void update_bomb(){
        elite2Enemy.decreaseHp(elite2Enemy.getHp()/2);
    }
}

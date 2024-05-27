package edu.hitsz.observers;

import edu.hitsz.aircraft.EliteEnemy;

public class Elite1Observer implements Observer{
    private EliteEnemy eliteEnemy;
    public Elite1Observer(EliteEnemy eliteEnemy)
    {
        this.eliteEnemy = eliteEnemy;
    }
    @Override
    public void update_bomb(){
        eliteEnemy.decreaseHp(eliteEnemy.getHp());
    }
}

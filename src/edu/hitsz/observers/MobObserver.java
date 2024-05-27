package edu.hitsz.observers;

import edu.hitsz.aircraft.MobEnemy;

public class MobObserver implements Observer{
    private MobEnemy mobEnemy;
    public MobObserver(MobEnemy mobEnemy)
    {
        this.mobEnemy = mobEnemy;
    }
    @Override
    public void update_bomb(){
        mobEnemy.decreaseHp(mobEnemy.getHp());
    }
}

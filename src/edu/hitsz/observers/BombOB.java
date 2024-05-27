package edu.hitsz.observers;

import java.util.ArrayList;
import java.util.List;

public class BombOB{
    private List<Observer> observerList = new ArrayList<>();
    public void add_observer(Observer observer)
    {
        observerList.add(observer);
    }
    public void remove_observer(Observer observer)
    {
        observerList.remove(observer);
    }
    public void notifyAllobserver()
    {
        //System.out.println("into notify");
        for(Observer observer :observerList)
        {
            //System.out.println("notifying");
            observer.update_bomb();
        } 
    }
    public void get_bomb()
    {
        notifyAllobserver();
    }
}

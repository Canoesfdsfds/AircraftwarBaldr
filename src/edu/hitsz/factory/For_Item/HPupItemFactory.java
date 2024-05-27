package edu.hitsz.factory.For_Item;

import edu.hitsz.item.BaseItem;
import edu.hitsz.item.HPupItem;

public class HPupItemFactory extends ItemFactory{
    @Override
    public BaseItem CreateItem(int locationX, int locationY, int speedX, int speedY) {
        speedX += (int)(Math.random()*10) % 7;
        speedX = ((Math.random()) > 0.5 ? speedX : -speedX);
        speedY += (int)(Math.random()*10) % 4;
        return new HPupItem(locationX, locationY, speedX, speedY);
    }    
}

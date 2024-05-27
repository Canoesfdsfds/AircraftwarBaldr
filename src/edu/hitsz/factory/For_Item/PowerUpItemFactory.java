package edu.hitsz.factory.For_Item;

import edu.hitsz.item.BaseItem;
import edu.hitsz.item.PowerUpItem;

public class PowerUpItemFactory extends ItemFactory{

    @Override
    public BaseItem CreateItem(int locationX, int locationY, int speedX, int speedY) {
        speedX += (int)(Math.random()*10) % 7;
        speedX = ((Math.random()) > 0.5 ? speedX : -speedX);
        speedY += (int)(Math.random()*10) % 4;
        return new PowerUpItem(locationX, locationY, speedX, speedY);
    }    
}

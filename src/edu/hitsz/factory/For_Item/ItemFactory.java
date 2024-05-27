package edu.hitsz.factory.For_Item;

import edu.hitsz.item.BaseItem;

public abstract class ItemFactory {
    public abstract BaseItem CreateItem(int locationX, int locationY, int speedX, int speedY);
}

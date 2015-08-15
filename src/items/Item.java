package items;

import system.ID;

public abstract class Item
{
    private ID id;
    private String name;
    
    public Item(ID id, String name)
    {
        this.id = id;
        this.name = name;
    }
    
}
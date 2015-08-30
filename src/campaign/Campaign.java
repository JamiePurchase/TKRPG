package campaign;

import items.Inventory;
import java.util.ArrayList;

public class Campaign
{
    private Inventory inventory;
    //private ArrayList<QuestSomething> questList;
    // NOTE: need a party object that holds characters and knows who is where
    
    public Campaign()
    {
        // NOTE: need to consider that when a campaign is loaded we don't want to setup these default objects
        // might be wise to have a separate constuctor that doesn't do this and set various bits of data after?
        this.inventory = new Inventory();
    }
    
    public Inventory getInventory()
    {
        return this.inventory;
    }
    
    public void save()
    {
        // NOTE: may want to consider automatically autosaving to a separate file every now and then
    }
    
}
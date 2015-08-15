package battle.units;

import java.awt.Graphics;
import system.ID;

public abstract class Unit
{
    private ID id;
    private String name;
    
    public Unit(ID id, String name)
    {
        this.id = id;
        this.name = name;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void render(Graphics g)
    {
        
    }
    
    public void tick()
    {
        
    }
    
}
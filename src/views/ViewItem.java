package views;

import java.awt.Graphics;

public abstract class ViewItem
{
    private String ref;
    
    public ViewItem(String ref)
    {
        this.ref = ref;
    }
    
    public String getRef()
    {
        return this.ref;
    }
    
    public abstract void render(Graphics g);
    
}
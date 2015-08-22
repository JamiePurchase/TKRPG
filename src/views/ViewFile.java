package views;

import java.awt.Graphics;
import java.util.ArrayList;

public class ViewFile
{
    private String project;
    private String viewName;
    private ArrayList<ViewItem> viewItem;
    
    public ViewFile(String project, String name)
    {
        this.project = project;
        this.viewName = name;
        this.viewItem = new ArrayList();
    }
    
    public void addItem(ViewItem item)
    {
        this.viewItem.add(item);
    }
    
    public void render(Graphics g)
    {
        
    }
    
    public void tick()
    {
        //
    }
    
}
package message;

import debug.Console;
import gfx.Drawing;
import gfx.Fonts;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class WorldMessage
{
    //private System messageState;
    private final Rectangle messageArea = new Rectangle(45, 523, 1276, 200);
    private ArrayList<String> messageText;
    
    public WorldMessage()
    {
        // DEBUG
        Console.print("WORLD MESSAGE -> INIT");
        
        //this.messageState = state;
        this.messageText = new ArrayList();
        this.messageText.add("HELLO");
    }
    
    public void render(Graphics g)
    {
        // DEBUG
        Console.print("WORLD MESSAGE -> RENDER");
        
        this.renderFrame(g);
        this.renderText(g);
    }
    
    private void renderFrame(Graphics g)
    {
        // Background
        Drawing.fadeRect(g, this.messageArea, Color.BLUE, 0.25f);
        
        // Border
        Drawing.drawRect(g, this.messageArea, Color.BLUE);
    }
    
    private void renderText(Graphics g)
    {
        for(int x = 0; x < this.messageText.size(); x++)
        {
            Text.write(g, this.messageText.get(x), this.messageArea.x + 50, this.messageArea.y + 50 + (50 * x), "LEFT", Fonts.getFont("STANDARD"), Color.BLACK);
        }
    }
    
}
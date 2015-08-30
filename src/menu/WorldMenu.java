package menu;

import gfx.Drawing;
import gfx.Fonts;
import gfx.Text;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class WorldMenu
{
    private final Rectangle menuArea = new Rectangle(50, 50, 1266, 668);
    private WorldMenuState menuState;
    
    public WorldMenu()
    {
        this.menuState = WorldMenuState.MAIN;
    }
    
    public void render(Graphics g)
    {
        // NOTE: we might want to take a snapshot of the world behind the menu and draw over that
        
        // Background
        Drawing.fadeRect(g, menuArea, Color.GRAY, 0.5f);
        
        // State
        if(this.menuState == WorldMenuState.MAIN) {this.renderMain(g);}
        
        // Border
        Drawing.drawRect(g, menuArea, Color.BLACK);
    }
    
    private void renderMain(Graphics g)
    {
        // Options
        Drawing.drawRect(g, 75, 75, 300, 553, Color.WHITE);
        Text.write(g, "OPTIONS", 85, 95, "LEFT", Fonts.getFont("STANDARD"), Color.WHITE);
        
        // Party
        Drawing.drawRect(g, 400, 75, 891, 553, Color.WHITE);
        Text.write(g, "PARTY", 410, 95, "LEFT", Fonts.getFont("STANDARD"), Color.WHITE);
        
        // Details
        Drawing.drawRect(g, 75, 643, 1216, 50, Color.WHITE);
        Text.write(g, "DETAILS", 85, 663, "LEFT", Fonts.getFont("STANDARD"), Color.WHITE);
    }
    
    public void tick()
    {
        
    }
    
}
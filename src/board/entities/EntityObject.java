package board.entities;

import board.BoardFile;
import debug.Console;
import gfx.Drawing;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import message.WorldMessage;
import states.System;

public class EntityObject extends Entity
{
    
    public EntityObject(BoardFile board, String ref, int posX, int posY)
    {
        super(board, ref, posX, posY, EntityType.OBJECT, false);
    }
    
    private BufferedImage getRenderImage()
    {
        return Drawing.getImageFile("C:/Users/Jamie/Documents/My Workshop/Java/TKRPG/Builder/Data/Mushroom/TEMP_SHEET/barrel.png");
    }
    
    public void interact(System system)
    {
        // DEBUG
        Console.print("ENTITY OBJECT -> INTERACT");
        
        // TEMP
        system.message(new WorldMessage());
    }
    
    public void render(Graphics g, int posX, int posY)
    {
        this.renderEntity(g, this.getRenderImage(), posX, posY);
    }
    
}
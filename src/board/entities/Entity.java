package board.entities;

import board.BoardFile;
import gfx.Drawing;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Entity
{
    private BoardFile entityBoard;
    private String entityRef;
    private int entityPosX, entityPosY;
    private EntityType entityType;
    
    public Entity(BoardFile board, String ref, int posX, int posY, EntityType type)
    {
        this.entityBoard = board;
        this.entityRef = ref;
        this.entityPosX = posX;
        this.entityPosY = posY;
        this.entityType = type;
    }
    
    public String getData()
    {
        return this.entityRef + "|" + this.entityPosX + "|" + entityPosY + "|" + this.entityType.toString();
        // may also include data on what happens when the player collides/interacts with the entity
    }
    
    public int getPositionX()
    {
        return this.entityPosX;
    }
    
    public int getPositionY()
    {
        return this.entityPosY;
    }
    
    public abstract void render(Graphics g, int posX, int posY);
    
    public void renderEntity(Graphics g, BufferedImage image, int posX, int posY)
    {
        Drawing.drawImage(g, image, posX, posY);
    }
    
    public void setPosition(int posX, int posY)
    {
        this.entityPosX = posX;
        this.entityPosY = posY;
    }
    
    public void tick()
    {
        //
    }
    
}
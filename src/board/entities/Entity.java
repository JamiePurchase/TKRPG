package board.entities;

import board.BoardFile;
import gfx.Drawing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import states.System;

public abstract class Entity
{
    private BoardFile entityBoard;
    private String entityRef;
    private int entityPosX, entityPosY;
    private int entitySizeX, entitySizeY;
    private EntityType entityType;
    private boolean entitySolid, entityPlayer;
    
    public Entity(BoardFile board, String ref, int posX, int posY, EntityType type, boolean player)
    {
        this.entityBoard = board;
        this.entityRef = ref;
        this.entityPosX = posX;
        this.entityPosY = posY;
        this.entitySizeX = 32;
        this.entitySizeY = 32;
        this.entityType = type;
        this.entitySolid = true;
        this.entityPlayer = player;
    }
    
    public BoardFile getBoard()
    {
        return this.entityBoard;
    }
    
    public Rectangle getCollisionArea()
    {
        return new Rectangle(this.entityPosX, this.entityPosY, this.entitySizeX, this.entitySizeY);
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
    
    public abstract void interact(states.System system);
    
    public boolean isInteractive()
    {
        return true;
        // NOTE: come back to this later
    }
    
    public boolean isPlayer()
    {
        return this.entityPlayer;
    }
    
    public boolean isSolid()
    {
        return this.entitySolid;
    }
    
    public abstract void render(Graphics g, int posX, int posY);
    
    public void renderEntity(Graphics g, BufferedImage image, int posX, int posY)
    {
        // DEBUG
        Drawing.drawRect(g, this.getCollisionArea(), Color.RED);
            
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
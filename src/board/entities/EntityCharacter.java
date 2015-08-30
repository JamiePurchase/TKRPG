package board.entities;

import app.Engine;
import board.BoardFile;
import board.Direction;
import framework.files.FileManager;
import gfx.Drawing;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import party.CharacterFile;

public class EntityCharacter extends Entity
{
    private CharacterFile entityCharacter;
    private EntityAction entityAction;
    private Direction entityDirection;
    private int entityAnimTickNow, entityAnimTickMax;
    private int entityAnimFrameNow, entityAnimFrameMax;
    
    public EntityCharacter(BoardFile board, String ref, int posX, int posY, CharacterFile character)
    {
        super(board, ref, posX, posY, EntityType.CHARACTER);
        this.entityCharacter = character;
        this.entityAction = EntityAction.IDLE;
        this.entityDirection = Direction.SOUTH;
        this.entityAnimTickNow = 0;
        this.entityAnimTickMax = 0;
        this.entityAnimFrameNow = 0;
        this.entityAnimFrameMax = 0;
    }
    
    public void render(Graphics g, int posX, int posY)
    {
        this.renderEntity(g, this.getRenderImage(), posX, posY);
    }
    
    private BufferedImage getRenderImage()
    {
        int subX = 32;
        if(this.entityAction == EntityAction.WALK)
        {
            if(this.entityAnimFrameNow == 1) {subX = 0;}
            if(this.entityAnimFrameNow == 3) {subX = 64;}
        }
        int subY = 96;
        if(this.entityDirection == Direction.EAST) {subY = 64;}
        if(this.entityDirection == Direction.SOUTH) {subY = 0;}
        if(this.entityDirection == Direction.WEST) {subY = 32;}
        return Drawing.getImageFile("C:/Users/Jamie/Documents/My Workshop/Java/TKRPG/Builder/Data/Mushroom/TEMP_SHEET/sprite.png").getSubimage(subX, subY, 32, 32);
    }
    
    public void tick()
    {
        if(this.entityAction == EntityAction.WALK)
        {
            this.entityAnimTickNow += 1;
            if(this.entityAnimTickNow >= this.entityAnimTickMax)
            {
                // TEMP
                int moveX = this.getPositionX();
                int moveY = this.getPositionY();
                if(this.entityDirection == Direction.EAST) {moveX += 8;}
                if(this.entityDirection == Direction.NORTH) {moveY -= 8;}
                if(this.entityDirection == Direction.SOUTH) {moveY += 8;}
                if(this.entityDirection == Direction.WEST) {moveX -= 8;}
                this.setPosition(moveX, moveY);
                    
                this.entityAnimTickNow = 0;
                this.entityAnimFrameNow += 1;
                if(this.entityAnimFrameNow >= this.entityAnimFrameMax)
                {            
                    this.entityAnimFrameNow = 0;
                    this.entityAction = EntityAction.IDLE;
                }
            }
        }
    }
    
    public void walk(Direction direction)
    {
        if(this.entityAction == EntityAction.IDLE)
        {
            this.entityDirection = direction;
            this.entityAction = EntityAction.WALK;
            this.entityAnimTickNow = 0;
            this.entityAnimTickMax = 4;
            this.entityAnimFrameNow = 0;
            this.entityAnimFrameMax = 4;
        }
    }
    
}
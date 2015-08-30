package board;

import board.entities.Entity;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BoardEngine
{
    private BoardFile boardFile;
    private Rectangle boardArea;
    private int boardOffsetX, boardOffsetY;
    
    public BoardEngine(Rectangle area, BoardFile board)
    {
        this.boardFile = board;
        this.boardArea = area;
        this.boardOffsetX = 0;
        this.boardOffsetY = 0;
    }
    
    private int getRenderPosX(int posX)
    {
        return this.boardArea.x + posX - this.boardOffsetX;
    }
    
    private int getRenderPosY(int posY)
    {
        return this.boardArea.y + posY - this.boardOffsetY;
    }
    
    public void render(Graphics g)
    {
        this.renderTerrain(g);
        this.renderEntity(g);
    }
    
    private void renderEntity(Graphics g)
    {
        for(int x = 0; x < this.boardFile.getEntityList().size(); x++)
        {
            Entity entity = this.boardFile.getEntityList().get(x);
            entity.render(g, this.getRenderPosX(entity.getPositionX()), this.getRenderPosY(entity.getPositionY()));
        }
    }
    
    private void renderTerrain(Graphics g)
    {
        this.boardFile.render(g, this.boardArea, this.boardOffsetX, this.boardOffsetY, false);
        // NOTE: change this to the buffered image with entities laid over the top
    }
    
    public void tick()
    {
        for(int x = 0; x < this.boardFile.getEntityList().size(); x++)
        {
            this.boardFile.getEntityList().get(x).tick();
        }
    }
    
}
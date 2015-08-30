package board;

import app.Engine;
import board.entities.Entity;
import framework.files.FileAbstract;
import framework.files.FileDate;
import gfx.Drawing;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import framework.files.FileType;
import java.awt.Color;

public class BoardFile extends FileAbstract
{
    private String boardProject, boardFile, boardName;
    private int boardSizeX, boardSizeY;
    private BoardTerrain[][] boardTerrain;
    private ArrayList<Entity> boardEntity;
    
    public BoardFile(String path, String project, FileDate update, String file, String name, int sizeX, int sizeY)
    {
        super(file, path, project, FileType.BOARD, update);
        this.boardProject = project;
        this.boardFile = file;
        this.boardName = name;
        this.boardSizeX = sizeX;
        this.boardSizeY = sizeY;
        this.boardTerrain = new BoardTerrain[sizeX][sizeY];
        this.boardEntity = new ArrayList();
        
        // NOTE: when a board is being loaded for the game engine, it would be wise to create a massive BufferedImage
        // made up of all of the terrain images, that can provide subimages as the player moves around
    }
    
    public void addEntity(Entity entity)
    {
        this.boardEntity.add(entity);
    }
    
    public Entity getEntity(int position)
    {
        return this.boardEntity.get(position);
    }
    
    public ArrayList<Entity> getEntityList()
    {
        return this.boardEntity;
    }
    
    public String getName()
    {
        return this.boardName;
    }
    
    public int getSizeX()
    {
        return this.boardSizeX;
    }
    
    public int getSizeY()
    {
        return this.boardSizeY;
    }
    
    public BoardTerrain[][] getTerrain()
    {
        return this.boardTerrain;
    }
    
    public BoardTerrain getTerrainAt(int posX, int posY)
    {
        return this.boardTerrain[posX][posY];
    }
    
    public void render(Graphics g, Rectangle area, int offsetX, int offsetY, boolean grid)
    {
        this.renderTerrain(g, area, offsetX, offsetY, grid);
        //this.renderEntity(g, area, offsetX, offsetY);
    }
    
    private void renderEntity(Graphics g, Rectangle area, int offsetX, int offsetY)
    {
        /*for(int x = 0; x < this.boardEntity.size(); x++)
        {
            this.boardEntity.get(x).render(g, area, offsetX, offsetY);
        }*/
    }
    
    private void renderTerrain(Graphics g, Rectangle area, int offsetX, int offsetY, boolean grid)
    {
        // NOTE: we need to build a large BufferedImage that represents the terrain of the entire map
        // we can use a subimage of that to draw the necessary section of the map
        for(int x = 0; x < this.getSizeX(); x++)
        {
            for(int y = 0; y < this.getSizeY(); y++)
            {
                Drawing.drawImage(g, this.boardTerrain[x + offsetX][y + offsetY].getImage(), area.x + (x * 32), area.y + (y * 32));
                if(grid) {Drawing.drawRect(g, area.x + (x * 32), area.y + (y * 32), 32, 32, Color.BLACK);}
            }
        }
    }
    
    public void save()
    {
        // Create an array of data
        ArrayList<String> data = new ArrayList();
        
        // Data: Board
        data.add(this.getName());
        data.add(this.getFileDate().getData());
        
        // NOTE: need to add more data for board options
        
        // Data: Terrain
        data.add(this.getSizeX() + "|" + this.getSizeY());
        for(int y = 0; y < this.getSizeY(); y++)
        {
            for(int x = 0; x < this.getSizeX(); x++)
            {
                data.add(this.getTerrainAt(x, y).getData());
            }
        }
        
        // Data: Entities
        data.add("" + this.boardEntity.size());
        for(int e = 0; e < this.boardEntity.size(); e++)
        {
            data.add(this.boardEntity.get(e).getData());
        }
        
        // Data: Zones
        //
        
        // Data: Lighting
        //
        
        // Save the array of data
        this.saveFile(data);
    }
    
    public String savePath()
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Data/" + this.boardProject + "/Boards/" + this.boardFile + ".tk7brd";
    }
    
    public void setBoardName(String newName)
    {
        this.boardName = newName;
    }
    
    public void setBoardSize(int x, int y)
    {
        final int oldX = this.boardSizeX;
        final int oldY = this.boardSizeY;
        this.boardSizeX = x;
        this.boardSizeY = y;
        this.setTerrainNew(oldX, oldY);
    }
    
    public void setTerrainAll(BoardTerrain terrain)
    {
        for(int x = 0; x < this.getSizeX(); x++)
        {
            for(int y = 0; y < this.getSizeY(); y++)
            {
                this.setTerrainAt(x, y, terrain);
            }
        }
    }
    
    public void setTerrainAt(int posX, int posY, BoardTerrain terrain)
    {
        this.boardTerrain[posX][posY] = terrain;
    }
    
    public void setTerrainNew(int oldX, int oldY)
    {
        // Create a new multidimensional array for the newly sized board
        BoardTerrain[][] newArray = new BoardTerrain[this.getSizeX()][this.getSizeY()];
        
        // Fill the entire array with basic terrain (if either dimension is bigger now, we will be missing some tiles)
        if(oldX < this.getSizeX() || oldY < this.getSizeY())
        {
            for(int x = 0; x < this.getSizeX(); x++)
            {
                for(int y = 0; y < this.getSizeY(); y++)
                {
                    newArray[x][y] = this.boardTerrain[0][0];
                }
            }
        }
        
        // Copy the old terrain into the new array (may be too many or too few but we've handled that)
        int maxX = this.getSizeX();
        int maxY = this.getSizeY();
        if(maxX > oldX) {maxX = oldX;}
        if(maxY > oldY) {maxY = oldY;}
        for(int x = 0; x < maxX; x++)
        {
            for(int y = 0; y < maxY; y++)
            {
                newArray[x][y] = this.boardTerrain[x][y];
            }
        }
        
        // Replace the old array with the new
        this.boardTerrain = newArray;
    }
    
}
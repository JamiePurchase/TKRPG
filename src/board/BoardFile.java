package board;

import app.Engine;
import board.entities.Entity;
import board.entities.EntityObject;
import board.zones.Zone;
import debug.Console;
import framework.files.FileAbstract;
import framework.files.FileDate;
import gfx.Drawing;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import framework.files.FileType;
import java.awt.Color;
import java.awt.Point;

public class BoardFile extends FileAbstract
{
    private String boardProject, boardFile, boardName;
    private int boardSizeX, boardSizeY;
    private BoardTerrain[][] boardTerrain;
    private ArrayList<Entity> boardEntity;
    private ArrayList<Zone> boardZone;
    
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
        this.boardZone = new ArrayList();
        
        // NOTE: when a board is being loaded for the game engine, it would be wise to create a massive BufferedImage
        // made up of all of the terrain images, that can provide subimages as the player moves around
        
        // TEMP
        this.addEntity(new EntityObject(this, "BARREL1", 192, 256));
    }
    
    public void addEntity(Entity entity)
    {
        this.boardEntity.add(entity);
    }
    
    public void addZone(Zone zone)
    {
        this.boardZone.add(zone);
    }
    
    public boolean getAreaFree(Rectangle rect)
    {
        // Check Entities
        if(this.getEntityIntersect(rect, true, false) != null) {return false;}
        
        // Check Zones
        if(this.getZoneIntersect(rect, true) != null) {return false;}
        
        // Area Free
        return true;
    }
    
    public Entity getEntity(int position)
    {
        return this.boardEntity.get(position);
    }
    
    public Entity getEntityIntersect(Rectangle rect, boolean solid, boolean player)
    {
        int entity = this.getEntityIntersectIndex(rect, solid, player);
        if(entity >= 0)
        {
            return this.boardEntity.get(entity);
        }
        return null;
    }
    
    public int getEntityIntersectIndex(Rectangle rect, boolean solid, boolean player)
    {
        for(int x = 0; x < this.boardEntity.size(); x++)
        {
            if(this.boardEntity.get(x).getCollisionArea().intersects(rect))
            {
                if(solid)
                {
                    if(this.boardEntity.get(x).isSolid())
                    {
                        if(player == false)
                        {
                            if(this.boardEntity.get(x).isPlayer() == false) {return x;}
                        }
                        else {return x;}
                    }
                }
                else
                {
                    if(player == false)
                    {
                        if(this.boardEntity.get(x).isPlayer() == false) {return x;}
                    }
                    else {return x;}
                }
            }
        }
        return -1;
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
    
    public Zone getZoneContain(Point point)
    {
        int zone = this.getZoneContainIndex(point);
        if(zone >= 0) {return this.boardZone.get(zone);}
        return null;
    }
    
    public int getZoneContainIndex(Point point)
    {
        for(int x = 0; x < this.boardZone.size(); x++)
        {
            if(this.boardZone.get(x).getArea().contains(point)) {return x;}
        }
        return -1;
    }
    
    public Zone getZoneIntersect(Rectangle rect, boolean solid)
    {
        int zone = this.getZoneIntersectIndex(rect, solid);
        if(zone >= 0)
        {
            return this.boardZone.get(zone);
        }
        return null;
    }
    
    public int getZoneIntersectIndex(Rectangle rect, boolean solid)
    {
        for(int x = 0; x < this.boardZone.size(); x++)
        {
            if(this.boardZone.get(x).getArea().intersects(rect))
            {
                if(solid)
                {
                    if(this.boardZone.get(x).isSolid()) {return x;}
                }
                else {return x;}
            }
        }
        return -1;
    }
    
    public ArrayList<Zone> getZoneList()
    {
        return this.boardZone;
    }
    
    public void removeZone(int index)
    {
        this.boardZone.remove(index);
    }
    
    public void render(Graphics g, Rectangle area, int offsetX, int offsetY, boolean grid, boolean zone)
    {
        this.renderTerrain(g, area, offsetX, offsetY, grid);
        //this.renderEntity(g, area, offsetX, offsetY);
        if(zone) {this.renderZone(g, area, offsetX, offsetY);}
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
    
    private void renderZone(Graphics g, Rectangle area, int offsetX, int offsetY)
    {
        // DEBUG
        //Console.print("BOARD -> RENDER ZONE");
        
        for(int x = 0; x < this.boardZone.size(); x++)
        {
            // DEBUG
            //Console.print(" " + x + " drawing at {" + this.boardZone.get(x).getArea().xpoints + "} {" + this.boardZone.get(x).getArea().ypoints + "}");
            
            this.boardZone.get(x).render(g, area, offsetX, offsetY);
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
        data.add("" + this.boardZone.size());
        for(int e = 0; e < this.boardZone.size(); e++)
        {
            data.add(this.boardZone.get(e).getData());
        }
        
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
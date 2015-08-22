package board;

import app.Engine;
import file.FileService;
import framework.files.FileAbstract;
import gfx.Drawing;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Date;
import tiles.TilesetManager;
import framework.files.FileType;

public class BoardFile extends FileAbstract
{
    private String boardProject, boardFile, boardName;
    private int boardSizeX, boardSizeY;
    private BoardTerrain[][] boardTerrain;
    
    public BoardFile(String path, String project, Date update, String file, String name, int sizeX, int sizeY)
    {
        super(file, path, project, FileType.BOARD, update);
        this.boardProject = project;
        this.boardFile = file;
        this.boardName = name;
        this.boardSizeX = sizeX;
        this.boardSizeY = sizeY;
        this.boardTerrain = new BoardTerrain[sizeX][sizeY];
        
        // NOTE: when a board is being loaded for the game engine, it would be wise to create a massive BufferedImage
        // made up of all of the terrain images, that can provide subimages as the player moves around
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
    
    public ArrayList<String> saveData()
    {
        System.out.println("BOARD FILE -> SAVE");
        
        ArrayList<String> data = new ArrayList();
        data.add(this.getName());
        data.add(this.getSizeX() + "|" + this.getSizeY());
        for(int x = 0; x < this.getSizeX(); x++)
        {
            for(int y = 0; y < this.getSizeY(); y++)
            {
                System.out.println(this.getTerrainAt(x, y).getData());
                data.add(this.getTerrainAt(x, y).getData());
            }
        }
        return data;
    }
    
    public String savePath()
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Data/" + this.boardProject + "/Boards/" + this.boardFile + ".tk7brd";
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
    
    public void render(Graphics g, Rectangle area, int offsetX, int offsetY)
    {
        for(int x = 0; x < this.getSizeX(); x++)
        {
            for(int y = 0; y < this.getSizeY(); y++)
            {
                Drawing.drawImage(g, this.boardTerrain[x + offsetX][y + offsetY].getImage(), area.x + (x * 32), area.y + (y * 32));
            }
        }
    }
    
}
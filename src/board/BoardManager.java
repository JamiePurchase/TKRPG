package board;

import app.Engine;
import file.FileService;
import framework.files.FileDate;
import framework.files.FileItem;
import framework.files.FileManager;
import java.io.File;
import java.util.ArrayList;
import states.State;
import framework.files.FileType;
import java.util.Date;
import tiles.TilesetManager;

public class BoardManager
{
    private FileManager manager;
    private String project;
    
    public BoardManager(FileManager manager, String project)
    {
        this.manager = manager;
        this.project = project;
    }
    
    public ArrayList<FileItem> getBoardArray()
    {
        ArrayList<File> files = FileService.getFolder(getBoardDirectory(), true, false, "tk7brd");
        ArrayList<FileItem> items = new ArrayList();
        for(int x = 0; x < files.size(); x++)
        {
            items.add(new FileItem(files.get(x), FileType.ITEM, files.get(x).lastModified()));
        }
        return items;
    }
    
    private String getBoardDirectory()
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Data/" + this.project + "/Boards/";
    }
    
    public String getPath(String file)
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Data/" + this.project + "/Boards/" + file + ".tk7brd";
    }
    
    public BoardFile loadBoard(File file)
    {
        return loadBoard(file.getPath());
    }
    
    public BoardFile loadBoard(String file)
    {
        // Load the Board File
        ArrayList<String> data = FileService.loadFile(getPath(file));
        
        // Create the Board Object
        int sizeX = Integer.parseInt(data.get(2).split("\\|")[0]);
        int sizeY = Integer.parseInt(data.get(2).split("\\|")[1]);
        BoardFile board = new BoardFile(getPath(file), project, new FileDate(data.get(1)), file, data.get(0), sizeX, sizeY);
        
        // Set the Board Terrain
        int tileX = 0;
        int tileY = 0;
        String tileSet = "";
        int tileCol = 0;
        int tileRow = 0;
        for(int tile = 0; tile < (sizeX * sizeY); tile++)
        {
            // Set Terrain
            tileSet = data.get(tile + 3).split("\\|")[0];
            tileCol = Integer.parseInt(data.get(tile + 3).split("\\|")[1]);
            tileRow = Integer.parseInt(data.get(tile + 3).split("\\|")[2]);
            board.setTerrainAt(tileX, tileY, new BoardTerrain(this.manager.Tileset().loadTileset(tileSet), tileCol, tileRow));
            
            // Next Tile
            tileX ++;
            if(tileX >= sizeX)
            {
                tileX = 0;
                tileY ++;
            }
        }
        
        // Set the Board Entities
        // ??
        
        // Set the Board Zones
        // ??
        
        // Set the Board Lighting
        // ??
        
        // Return the Board Object
        return board;
    }
    
}
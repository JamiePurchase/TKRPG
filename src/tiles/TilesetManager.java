package tiles;

import app.Engine;
import file.FileService;
import items.ItemFile;
import java.io.File;
import java.util.ArrayList;
import states.State;
import framework.files.FileItem;
import framework.files.FileType;
import java.util.Date;

public class TilesetManager
{
    private State state;
    private String project;
    
    public TilesetManager(State state, String project)
    {
        this.state = state;
        this.project = project;
    }
    
    public ArrayList<FileItem> getItemArray()
    {
        ArrayList<File> files = FileService.getFolder(getTilesetDirectory(), true, false, "tk7tst");
        ArrayList<FileItem> tilesets = new ArrayList();
        for(int x = 0; x < files.size(); x++)
        {
            tilesets.add(new FileItem(files.get(x), FileType.TILESET));
        }
        return tilesets;
    }
    
    public String getPath(String file)
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Data/" + project + "/Tilesets/" + file + ".tk7tst";
    }
    
    public String getPathSheet(String file)
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Data/" + project + "/TEMP_SHEET/" + file + ".png";
    }
    
    private String getTilesetDirectory()
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Data/" + this.project + "/Tilesets/";
    }
    
    public TilesetFile loadTileset(String file)
    {
        // Load the Tileset File
        ArrayList<String> data = FileService.loadFile(getPath(file));
        
        // Debug
        System.out.println(data);
        
        // Create the Tileset Object
        int sizeTile = Integer.parseInt(data.get(2).split("\\|")[0]);
        int sizeCols = Integer.parseInt(data.get(2).split("\\|")[1]);
        int sizeRows = Integer.parseInt(data.get(2).split("\\|")[2]);
        Date update = new Date();
        return new TilesetFile(getPath(file), project, file, data.get(0), update, data.get(1), sizeTile, sizeCols, sizeRows);
        
        // NOTE: need to get the update value as a string (from the file) and parse it into a date
    }
    
}
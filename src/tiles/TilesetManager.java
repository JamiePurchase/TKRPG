package tiles;

import app.Engine;
import file.FileService;
import framework.files.FileDate;
import items.ItemFile;
import java.io.File;
import java.util.ArrayList;
import framework.files.FileItem;
import framework.files.FileManager;
import framework.files.FileType;
import java.util.Date;

public class TilesetManager
{
    private FileManager manager;
    private String project;
    
    public TilesetManager(FileManager manager, String project)
    {
        this.manager = manager;
        this.project = project;
    }
    
    public ArrayList<FileItem> getTilesetArray()
    {
        ArrayList<File> files = FileService.getFolder(getTilesetDirectory(), true, false, "tk7tst");
        ArrayList<FileItem> tilesets = new ArrayList();
        for(int x = 0; x < files.size(); x++)
        {
            tilesets.add(new FileItem(files.get(x), FileType.TILESET, files.get(x).lastModified()));
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
        
        // Create the Tileset Object
        int sizeTile = Integer.parseInt(data.get(3).split("\\|")[0]);
        int sizeCols = Integer.parseInt(data.get(3).split("\\|")[1]);
        int sizeRows = Integer.parseInt(data.get(3).split("\\|")[2]);
        Date update = new Date();
        return new TilesetFile(getPath(file), project, file, data.get(0), new FileDate(data.get(1)), data.get(2), sizeTile, sizeCols, sizeRows);
        
        // NOTE: need to get the update value as a string (from the file) and parse it into a date
    }
    
}
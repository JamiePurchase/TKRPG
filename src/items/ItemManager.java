package items;

import app.Engine;
import file.FileService;
import framework.files.FileDate;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import projects.ProjectFile;
import projects.ProjectManager;
import framework.files.FileItem;
import framework.files.FileManager;
import framework.files.FileType;

public class ItemManager
{
    private FileManager manager;
    private String project;
    
    public ItemManager(FileManager manager, String project)
    {
        this.manager = manager;
        this.project = project;
    }
    
    public ArrayList<FileItem> getItemArray()
    {
        ArrayList<File> files = FileService.getFolder(getItemDirectory(), true, false, "tk7itm");
        ArrayList<FileItem> items = new ArrayList();
        for(int x = 0; x < files.size(); x++)
        {
            items.add(new FileItem(files.get(x), FileType.ITEM, files.get(x).lastModified()));
        }
        return items;
    }
    
    private String getItemDirectory()
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Data/" + this.project + "/Items/";
    }
    
    public ItemType getType(String type)
    {
        return ItemType.WEAPON;
    }
    
    public String getPath(String file)
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Data/" + this.project + "/Items/" + file + ".tk7itm";
    }
    
    public ItemFile loadItem(File file)
    {
        return loadItem(file.getPath());
    }
    
    public ItemFile loadItem(String file)
    {
        // Load the Item File
        ArrayList<String> data = FileService.loadFile(getPath(file));
        
        // Create the Item Object
        boolean key = false;
        if(data.get(3).equals("TRUE")) {key = true;}
        ItemFile item = new ItemFile(getPath(file), this.project, file, data.get(0), new FileDate(data.get(1)), data.get(2), key);
        
        // Return the Item Object
        return item;
    }
    
}
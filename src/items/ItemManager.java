package items;

import app.Engine;
import file.FileService;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import projects.ProjectFile;
import static projects.ProjectManager.getProject;
import states.State;
import framework.files.FileItem;
import framework.files.FileType;

public class ItemManager
{
    private State state;
    private String project;
    
    public ItemManager(State state, String project)
    {
        this.state = state;
        this.project = project;
    }
    
    public ArrayList<FileItem> getItemArray()
    {
        ArrayList<File> files = FileService.getFolder(getItemDirectory(), true, false, "tk7itm");
        ArrayList<FileItem> items = new ArrayList();
        for(int x = 0; x < files.size(); x++)
        {
            items.add(new FileItem(files.get(x), FileType.ITEM));
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
        if(data.get(2).equals("TRUE")) {key = true;}
        Date update = new Date();
        ItemFile item = new ItemFile(getPath(file), this.project, file, data.get(0), update, data.get(1), key);
        
        // NOTE: need to get the update value as a string (from the file) and parse it into a date
        
        // Return the Item Object
        return item;
    }
    
}
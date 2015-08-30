package lava;

import app.Engine;
import file.FileService;
import framework.files.FileDate;
import java.io.File;
import java.util.ArrayList;
import lava.LavaFile;
import projects.ProjectFile;
import projects.ProjectManager;
import framework.files.FileItem;
import framework.files.FileManager;
import framework.files.FileType;
import java.util.Date;

public class LavaManager
{
    private FileManager manager;
    private String project;
    
    public LavaManager(FileManager manager, String project)
    {
        this.manager = manager;
        this.project = project;
    }
    
    public ArrayList<FileItem> getLavaArray()
    {
        ArrayList<File> files = FileService.getFolder(getLavaDirectory(), true, false, "tk7lva");
        ArrayList<FileItem> lava = new ArrayList();
        for(int x = 0; x < files.size(); x++)
        {
            lava.add(new FileItem(files.get(x), FileType.LAVA, files.get(x).lastModified()));
        }
        return lava;
    }
    
    private String getLavaDirectory()
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Data/" + this.project + "/Lava/";
    }
    
    public String getPath(String file)
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Data/" + this.project + "/Lava/" + file + ".tk7lva";
    }
    
    public LavaFile loadLava(File file)
    {
        return loadLava(file.getPath());
    }
    
    public LavaFile loadLava(String file)
    {
        // Load the Lava File
        ArrayList<String> data = FileService.loadFile(file);
        
        // Create the Lava Object
        LavaFile lava = new LavaFile(getPath(file), this.project, file, data.get(0), new FileDate(data.get(1)));
        
        // Return the Lava Object
        return lava;
    }
    
}
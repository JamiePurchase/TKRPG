package lava;

import app.Engine;
import file.FileService;
import java.io.File;
import java.util.ArrayList;
import lava.LavaFile;
import projects.Project;
import static projects.ProjectManager.getProject;
import states.StateBuilder;
import tools.files.FileItem;
import tools.files.FileType;

public class LavaManager
{
    private StateBuilder state;
    private String project;
    
    public LavaManager(StateBuilder state, String project)
    {
        this.state = state;
        this.project = project;
    }
    
    public ArrayList<FileItem> getLavaArray()
    {
        ArrayList<File> files = FileService.getFolder(getLavaDirectory(), true, false, "tk7lva");
        ArrayList<FileItem> lava = new ArrayList();
        for(int x = 0; x < files.size(); x++)
        {
            lava.add(new FileItem(files.get(x), FileType.LAVA));
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
        LavaFile lava = new LavaFile(this.project, file, data.get(0));
        
        // Return the Lava Object
        return lava;
    }
    
}
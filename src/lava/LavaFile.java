package lava;

import app.Engine;
import file.FileService;
import framework.files.FileAbstract;
import java.util.ArrayList;
import java.util.Date;
import tools.files.FileType;

public class LavaFile extends FileAbstract
{
    private String lavaProject, lavaFile, lavaName;
    
    public LavaFile(String path, String project, String file, String name, Date update)
    {
        super(path, project, FileType.LAVA, update);
        this.lavaProject = project;
        this.lavaFile = file;
        this.lavaName = name;
    }
    
    public String getName()
    {
        return this.lavaName;
    }
    
    private ArrayList<String> saveData()
    {
        ArrayList<String> data = new ArrayList();
        data.add("");
        return data;
    }
    
    private String savePath()
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Data/" + this.lavaProject + "/Lava/" + this.lavaFile + ".tk7lva";
    }
    
}
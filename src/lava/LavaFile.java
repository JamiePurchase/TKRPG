package lava;

import app.Engine;
import file.FileService;
import framework.files.FileAbstract;
import framework.files.FileDate;
import java.util.ArrayList;
import java.util.Date;
import framework.files.FileType;

public class LavaFile extends FileAbstract
{
    private String lavaProject, lavaFile, lavaName;
    
    public LavaFile(String path, String project, String file, String name, FileDate update)
    {
        super(name, path, project, FileType.LAVA, update);
        this.lavaProject = project;
        this.lavaFile = file;
        this.lavaName = name;
    }
    
    public String getName()
    {
        return this.lavaName;
    }
    
    public void save()
    {
        ArrayList<String> data = new ArrayList();
        data.add(this.getFileName());
        data.add(this.getFileDate().getData());
        this.saveFile(data);
    }
    
    private String savePath()
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Data/" + this.lavaProject + "/Lava/" + this.lavaFile + ".tk7lva";
    }
    
}
package framework.files;

import file.FileService;
import java.util.ArrayList;
import java.util.Date;

public abstract class FileAbstract
{
    private String fileName, filePath, fileProject;
    private FileType fileType;
    private Date fileUpdate;
    
    public FileAbstract(String name, String path, String project, FileType type, Date update)
    {
        this.fileName = name;
        this.filePath = path;
        this.fileProject = project;
        this.fileType = type;
        this.fileUpdate = update;
    }
    
    public String getFileName()
    {
        return this.fileName;
    }
    
    public String getFilePath()
    {
        return this.filePath;
    }
    
    public String getFileProject()
    {
        return this.fileProject;
    }
    
    public Date getFileUpdate()
    {
        return this.fileUpdate;
    }
    
    public abstract void save();
    
    public void saveFile(ArrayList<String> data)
    {
        FileService.saveFile(data, this.getFilePath());
    }
    
    public void setFileUpdate()
    {
        this.fileUpdate = new Date();
    }
    
}
package projects;

import file.FileService;
import framework.files.FileAbstract;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import tools.files.FileType;

public class ProjectFile extends FileAbstract
{
    private String updated;
    
    public ProjectFile(String path, String name, Date update)
    {
        super(name, path, name, FileType.PROJECT, update);
        //this.updated = 
    }
    
    public String getInfo()
    {
        // TEMP (version? file size? number of files? date saved?)
        // Note: could even iterate through all of the above (five seconds each?)
        return "V 1.0 - 15/08/2015";
    }
    
    public String getUpdated()
    {
        return this.updated;
    }
    
    public void save()
    {
        FileService.saveFile(this.saveData(), this.savePath());
    }
    
    public ArrayList<String> saveData()
    {
        ArrayList<String> data = new ArrayList();
        data.add(this.getFileName());
        data.add(this.getUpdated());
        return data;
    }
    
    public String savePath()
    {
        return this.getFilePath();
    }
    
    public void setUpdated()
    {
        this.updated = new SimpleDateFormat("dd/mm/yyyy").format(Calendar.getInstance().getTime());
    }
    
}
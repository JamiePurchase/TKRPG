package items;

import app.Engine;
//import config.ConfigManager;
import file.FileService;
import framework.files.FileAbstract;
import framework.files.FileDate;
import java.util.ArrayList;
import java.util.Date;
import framework.files.FileType;

public class ItemFile extends FileAbstract
{
    private String itemProject, itemFile, itemName;
    private ItemType itemType;
    private boolean itemKey;
    
    public ItemFile(String path, String project, String file, String name, FileDate update, String type, boolean key)
    {
        super(name, path, project, FileType.ITEM, update);
        this.itemProject = project;
        this.itemFile = file;
        this.itemName = name;
        this.itemType = ItemType.WEAPON;
        this.itemKey = key;
    }
    
    public String getName()
    {
        return this.itemName;
    }
    
    public void save()
    {
        FileService.saveFile(this.saveData(), this.savePath());
    }
    
    private ArrayList<String> saveData()
    {
        ArrayList<String> data = new ArrayList();
        data.add(this.itemName);
        data.add(this.getFileDate().getData());
        data.add(this.itemType.toString());
        String key = "FALSE";
        if(this.itemKey) {key = "TRUE";}
        data.add(key);
        return data;
    }
    
    private String savePath()
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Data/" + this.itemProject + "/Items/" + this.itemFile + ".tk7itm";
    }
    
    public void setName(String name)
    {
        this.itemName = name;
    }
    
}
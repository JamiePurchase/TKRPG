package party;

import debug.Console;
import framework.files.FileAbstract;
import framework.files.FileDate;
import java.util.ArrayList;
import framework.files.FileType;

public class CharacterFile extends FileAbstract
{
    private String characterTitle;
    
    public CharacterFile(String path, String project, String file, String name, FileDate update, String title)
    {
        super(name, path, project, FileType.LAVA, update);
        this.characterTitle = title;
        
        // DEBUG
        Console.print("CHARACTER FILE (" + path + ", " + project + ", " + file + ", " + name + ", " + update.getDisplay() + ", " + title);
    }
    
    public String getTitle()
    {
        return this.characterTitle;
    }
    
    public void save()
    {
        ArrayList<String> data = new ArrayList();
        data.add(this.getFileName());
        data.add(this.getFileDate().getData());
        this.saveFile(data);
    }
    
}
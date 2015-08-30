package party;

import app.Engine;
import file.FileService;
import framework.files.FileDate;
import java.io.File;
import java.util.ArrayList;
import framework.files.FileItem;
import framework.files.FileManager;
import framework.files.FileType;

public class CharacterManager
{
    private FileManager manager;
    private String project;
    
    public CharacterManager(FileManager manager, String project)
    {
        this.manager = manager;
        this.project = project;
    }
    
    public ArrayList<FileItem> getCharacterArray()
    {
        ArrayList<File> files = FileService.getFolder(getCharacterDirectory(), true, false, "tk7chr");
        ArrayList<FileItem> characters = new ArrayList();
        for(int x = 0; x < files.size(); x++)
        {
            characters.add(new FileItem(files.get(x), FileType.CHARACTER, files.get(x).lastModified()));
        }
        return characters;
    }
    
    private String getCharacterDirectory()
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Data/" + this.project + "/Characters/";
    }
    
    public String getPath(String file)
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Data/" + this.project + "/Characters/" + file + ".tk7chr";
    }
    
    public CharacterFile loadCharacter(File file)
    {
        return loadCharacter(file.getPath());
    }
    
    public CharacterFile loadCharacter(String file)
    {
        // Load the Character File
        ArrayList<String> data = FileService.loadFile(getPath(file));
        
        // Create the Character Object
        CharacterFile character = new CharacterFile(getPath(file), this.project, file, data.get(0), new FileDate(data.get(1)), data.get(2));
        
        // Return the Character Object
        return character;
    }
    
}
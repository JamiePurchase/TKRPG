package audio;

import framework.files.FileAbstract;
import java.util.ArrayList;
import java.util.Date;
import tools.files.FileType;

public class AudioPlaylist extends FileAbstract
{
    
    public AudioPlaylist(String path, String project, String name, Date update)
    {
        super(path, project, FileType.PLAYLIST, update);
    }
    
    private ArrayList<String> saveData()
    {
        
    }
    
    private String savePath()
    {
        
    }
    
}
package audio;

import framework.files.FileAbstract;
import java.util.ArrayList;
import java.util.Date;
import framework.files.FileType;

public class AudioPlaylist extends FileAbstract
{
    
    public AudioPlaylist(String path, String project, String name, Date update)
    {
        super(name, path, project, FileType.PLAYLIST, update);
    }
    
    public void save()
    {
        ArrayList<String> data = new ArrayList();
        this.saveFile(data);
    }
    
}
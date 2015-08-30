package audio;

import framework.files.FileAbstract;
import framework.files.FileDate;
import java.util.ArrayList;
import framework.files.FileType;

public class AudioPlaylist extends FileAbstract
{
    
    public AudioPlaylist(String path, String project, String name, FileDate update)
    {
        super(name, path, project, FileType.PLAYLIST, update);
    }
    
    public void save()
    {
        ArrayList<String> data = new ArrayList();
        this.saveFile(data);
    }
    
}
package framework.files;

import gfx.Drawing;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

public class FileItem
{
    private File filePath;
    private FileType fileType;
    private FileDate fileDate;
    
    public FileItem(File path, FileType type, long date)
    {
        this.filePath = path;
        this.fileType = type;
        this.fileDate = new FileDate(new Date(date));
    }
    
    public FileDate getDate()
    {
        return this.fileDate;
    }
    
    public BufferedImage getIcon()
    {
        return Drawing.getImage("icon/io_" + this.fileType.toString() + ".png");
    }
    
    public String getName()
    {
        String name = this.filePath.getName();
        return name.substring(0, name.length() - 7);
    }
    
    public File getPath()
    {
        return this.filePath;
    }
    
    public FileType getType()
    {
        return this.fileType;
    }
    
}
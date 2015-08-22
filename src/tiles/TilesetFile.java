package tiles;

import app.Engine;
import file.FileService;
import framework.files.FileAbstract;
import gfx.Drawing;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import tools.files.FileType;

public class TilesetFile extends FileAbstract
{
    private String tilesetName, tilesetSheetFile;
    private BufferedImage tilesetSheetImage;
    private int tilesetDimension;
    private int tilesetCountX;
    private int tilesetCountY;
    
    public TilesetFile(String path, String project, String file, String name, Date update, String sheet, int size, int cols, int rows)
    {
        super(path, project, FileType.TILESET, update);
        this.tilesetName = name;
        this.tilesetSheetFile = sheet;
        this.tilesetSheetImage = Drawing.getImageFile(sheet);
        this.tilesetDimension = size;
        this.tilesetCountX = cols;
        this.tilesetCountY = rows;
    }
    
    public int getCountX()
    {
        return this.tilesetCountX;
    }
    
    public int getCountY()
    {
        return this.tilesetCountY;
    }
    
    public BufferedImage getTileAt(int col, int row)
    {
        if(col <= this.tilesetCountX && row <= this.tilesetCountY)
        {
            return this.tilesetSheetImage.getSubimage(col * this.tilesetDimension, row * this.tilesetDimension, this.tilesetDimension, this.tilesetDimension);
        }
        return this.tilesetSheetImage.getSubimage(0, 0, this.tilesetDimension, this.tilesetDimension);
    }
    
    public int getTileSize()
    {
        return this.tilesetDimension;
    }
    
    public ArrayList<String> saveData()
    {
        ArrayList<String> data = new ArrayList();
        data.add(this.tilesetName);
        data.add(this.tilesetSheetFile);
        data.add(this.tilesetCountX + "|" + this.tilesetCountY + "|" + this.tilesetDimension);
        return data;
    }
    
    private String savePath()
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Data/" + this.getFileProject() + "/Tilesets/" + this.getFileName() + ".tk7tst";
    }
    
}
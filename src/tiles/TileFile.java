package tiles;

import java.awt.Graphics;

public class TileFile
{
    private TilePixel[][] tilePixelMap;
    
    public TileFile()
    {
        this.tilePixelMap = new TilePixel[32][32];
        this.setAll();
    }
    
    public TilePixel getPixel(int x, int y)
    {
        return this.tilePixelMap[x][y];
    }
    
    public void setAll()
    {
        this.setPixelAll(new TilePixel());
    }
    
    public void setAll(int r, int g, int b)
    {
        this.setPixelAll(new TilePixel(r, g, b));
    }
    
    public void setPixel(int x, int y, TilePixel pixel)
    {
        this.tilePixelMap[x][y] = pixel;
    }
    
    public void setPixelAll(TilePixel pixel)
    {
        for(int x = 0; x < 32; x++)
        {
            for(int y = 0; y < 32; y++)
            {
                this.setPixel(x, y, pixel);
            }
        }
    }
    
}
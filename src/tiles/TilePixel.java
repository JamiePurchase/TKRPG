package tiles;

import gfx.Colour;
import java.awt.Color;

public class TilePixel
{
    public int r;
    public int g;
    public int b;
    private boolean transparent;
    
    public TilePixel()
    {
        this.r = 0;
        this.g = 0;
        this.b = 0;
        this.transparent = true;
    }
    
    public TilePixel(int r, int g, int b)
    {
        this.transparent = false;
    }
    
    public Color getColor()
    {
        return Colour.getColourRGB(this.r, this.g, this.b);
    }
    
    public boolean isTransparent()
    {
        return this.transparent;
    }
    
}
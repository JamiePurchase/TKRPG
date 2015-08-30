package board.zones;

import board.BoardFile;
import gfx.Drawing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public abstract class Zone
{
    private BoardFile zoneBoard;
    private Polygon zoneArea;
    private ZoneType zoneType;
    
    public Zone(BoardFile board, Polygon area, ZoneType type)
    {
        this.zoneBoard = board;
        this.zoneArea = area;
        this.zoneType = type;
    }
    
    public Polygon getArea()
    {
        return this.zoneArea;
    }
    
    public abstract String getData();
    
    public void render(Graphics g)
    {
        Drawing.drawPolygon(g, this.zoneArea, renderColor());
    }
    
    private Color renderColor()
    {
        if(this.zoneType == ZoneType.COLLISION) {return Color.BLACK;}
        if(this.zoneType == ZoneType.SCRIPT) {return Color.GRAY;}
        return Color.BLACK;
    }
    
}
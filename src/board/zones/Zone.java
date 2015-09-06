package board.zones;

import board.BoardFile;
import gfx.Drawing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import shapes.PolygonTools;

public abstract class Zone
{
    private BoardFile zoneBoard;
    private Polygon zoneArea;
    private String zoneRef;
    private ZoneType zoneType;
    
    public Zone(BoardFile board, Polygon area, String ref, ZoneType type)
    {
        this.zoneBoard = board;
        this.zoneArea = area;
        this.zoneRef = ref;
        this.zoneType = type;
    }
    
    public Polygon getArea()
    {
        return this.zoneArea;
    }
    
    public abstract String getData();
    
    public String getRef()
    {
        return this.zoneRef;
    }
    
    private Polygon getRenderArea(Rectangle area, int offsetX, int offsetY)
    {
        return PolygonTools.translate(this.zoneArea, area.x - offsetX, area.y - offsetY);
    }
    
    public String getType()
    {
        return this.zoneType.toString();
    }
    
    public boolean isSolid()
    {
        if(this.zoneType == ZoneType.COLLISION) {return true;}
        return false;
        
        // NOTE: there may be some other zones that are solid? consider this
    }
    
    public void render(Graphics g, Rectangle area, int offsetX, int offsetY)
    {
        Polygon polygon = this.getRenderArea(area, offsetX, offsetY);
        Drawing.fadePolygon(g, polygon, renderColor(), 0.25f);
        Drawing.drawPolygon(g, polygon, renderColor());
    }
    
    private Color renderColor()
    {
        if(this.zoneType == ZoneType.COLLISION) {return Color.BLACK;}
        if(this.zoneType == ZoneType.SCRIPT) {return Color.GRAY;}
        return Color.BLACK;
    }
    
}
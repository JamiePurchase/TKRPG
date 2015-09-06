package board.zones;

import board.BoardFile;
import java.awt.Polygon;

public class ZoneCollision extends Zone
{
    
    public ZoneCollision(BoardFile board, Polygon area, String ref)
    {
        super(board, area, ref, ZoneType.COLLISION);
    }
    
    public String getData()
    {
        int[] areaX = this.getArea().xpoints;
        int[] areaY = this.getArea().ypoints;
        return this.getRef() + "|COLLISION|" + areaX.length + "|" + this.getDataPoints(areaX) + "|" + this.getDataPoints(areaY);
        
        // NOTE: investigate the value of areaX.toString()
    }
    
    private String getDataPoints(int[] points)
    {
        String data = "";
        for(int p = 0; p < points.length; p++)
        {
            if(p > 0) {data += "-";}
            data += "" + points[p];
        }
        return data;
    }
    
}
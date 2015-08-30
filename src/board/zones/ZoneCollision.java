package board.zones;

import board.BoardFile;
import java.awt.Polygon;

public class ZoneCollision extends Zone
{
    
    public ZoneCollision(BoardFile board, Polygon area)
    {
        super(board, area, ZoneType.COLLISION);
    }
    
    public String getData()
    {
        int[] areaX = this.getArea().xpoints;
        int[] areaY = this.getArea().ypoints;
        return "COLLISION|" + areaX.toString() + "|" + areaY.toString();
        
        // NOTE: investigate the value of areaX.toString()
    }
    
}
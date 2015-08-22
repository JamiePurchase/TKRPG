package board;

import java.awt.image.BufferedImage;
import tiles.TilesetFile;
import tiles.TilesetManager;

public class BoardTerrain
{
    private TilesetFile terrainTileset;
    private int terrainTileCol, terrainTileRow;
    
    public BoardTerrain(TilesetFile tileset, int col, int row)
    {
        this.terrainTileset = tileset;
        this.terrainTileCol = col;
        this.terrainTileRow = row;
    }
    
    public String getData()
    {
        return this.terrainTileset.getFileName() + "|" + this.terrainTileCol + "|" + this.terrainTileRow;
    }
    
    public BufferedImage getImage()
    {
        return this.terrainTileset.getTileAt(this.terrainTileCol, this.terrainTileRow);
    }
    
}
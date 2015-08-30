package framework.files;

import board.BoardManager;
import items.ItemManager;
import party.CharacterManager;
import lava.LavaManager;
import tiles.TilesetManager;

public class FileManager
{
    private final BoardManager managerBoard;
    private final CharacterManager managerCharacter;
    private final ItemManager managerItem;
    private final LavaManager managerLava;
    private final TilesetManager managerTileset;
    
    public FileManager(String project)
    {
        this.managerBoard = new BoardManager(this, project);
        this.managerCharacter = new CharacterManager(this, project);
        this.managerItem = new ItemManager(this, project);
        this.managerLava = new LavaManager(this, project);
        this.managerTileset = new TilesetManager(this, project);
    }
    
    public BoardManager Board()
    {
        return this.managerBoard;
    }
    
    public CharacterManager Character()
    {
        return this.managerCharacter;
    }
    
    public ItemManager Item()
    {
        return this.managerItem;
    }
    
    public LavaManager Lava()
    {
        return this.managerLava;
    }
    
    public TilesetManager Tileset()
    {
        return this.managerTileset;
    }
    
}
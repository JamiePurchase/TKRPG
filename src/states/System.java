package states;

import app.Engine;
import board.BoardEngine;
import board.BoardFile;
import board.Direction;
import board.entities.Entity;
import board.entities.EntityCharacter;
import campaign.Campaign;
import debug.Console;
import framework.files.FileManager;
import gfx.Drawing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import menu.WorldMenu;
import message.WorldMessage;
import party.CharacterFile;
import projects.ProjectFile;
import projects.ProjectManager;
import states.System;

public class System extends State
{
    private ProjectFile engineProject;
    private FileManager engineManager;
    private Campaign engineCampaign;
    
    // TEMP
    private BoardFile engineBoard;
    private BoardEngine engineWorld;
    private boolean engineWorldPause;
    private boolean engineWorldMenuActive;
    private WorldMenu engineWorldMenuObject;
    private CharacterFile engineCharacter;
    private EntityCharacter enginePlayer;
    private SystemState engineState;
    private boolean engineWorldMessageActive;
    private WorldMessage engineWorldMessageObject;
    
    public System()
    {
        this.engineProject = ProjectManager.getProject("" + Engine.getAppVariable("ENGINE_PROJECT"));
        Engine.setAppVariable("ENGINE_MANAGER", new FileManager(this.engineProject.getFileName()));
        
        // TEMP
        this.engineCampaign = new Campaign();
        
        // TEMP
        this.engineBoard = this.getManager().Board().loadBoard(this.engineProject.getInitialBoard());
        this.engineWorld = new BoardEngine(this, new Rectangle(0, 0, 1366, 768), this.engineBoard);
        this.engineWorldPause = false;
        this.engineWorldMenuActive = false;
        this.engineWorldMenuObject = new WorldMenu();
        this.engineCharacter = this.getManager().Character().loadCharacter(this.engineProject.getInitialCharacter());
        this.enginePlayer = new EntityCharacter(this.engineBoard, "PLAYER", 0, 0, this.engineCharacter, true);
        this.engineBoard.addEntity(this.enginePlayer);
        this.engineState = SystemState.WORLD;
        
        // TEMP
        message();
    }
    
    private EntityCharacter getEntityPlayer()
    {
        return this.enginePlayer;
    }
    
    private FileManager getManager()
    {
        return (FileManager) Engine.getAppVariable("ENGINE_MANAGER");
    }

    public void inputKeyPress(String key)
    {
        // DEBUG
        Console.print("SYSTEM -> INPUT KEY PRESS (" + key + ")");
        
        if(this.engineState == SystemState.WORLD)
        {
            if(this.engineWorldPause) {inputKeyPressPause(key);}
            else if(this.engineWorldMenuActive) {this.inputKeyPressMenu(key);}
            else if(this.engineWorldMessageActive) {this.inputKeyPressMessage(key);}
            else {this.inputKeyPressPlayer(key);}
        }
    }
    
    private void inputKeyPressMenu(String key)
    {
        // Close Menu
        if(key.equals("ESCAPE"))
        {
            this.worldMenu(false);
            return;
        }
    }
    
    private void inputKeyPressMessage(String key)
    {
        //
    }
    
    private void inputKeyPressPause(String key)
    {
        // Unpause
        if(key.equals("ESCAPE"))
        {
            this.worldPause(false);
            return;
        }
    }
    
    private void inputKeyPressPlayer(String key)
    {
        // Menu
        if(key.equals("ENTER"))
        {
            this.worldMenu(true);
            return;
        }
        
        // Pause
        if(key.equals("ESCAPE"))
        {
            this.worldPause(true);
        }
        
        // Interact
        if(key.equals("SPACE"))
        {
            Entity entity = this.engineWorld.getCollideEntity(this.getEntityPlayer().getInteractRect());
            if(entity != null)
            {
                if(entity.isInteractive()) {entity.interact(this);}
            }
            return;
        }
        
        // Movement
        if(key.equals("DOWN"))
        {
            this.getEntityPlayer().walk(Direction.SOUTH);
            return;
        }
        if(key.equals("LEFT"))
        {
            this.getEntityPlayer().walk(Direction.WEST);
            return;
        }
        if(key.equals("RIGHT"))
        {
            this.getEntityPlayer().walk(Direction.EAST);
            return;
        }
        if(key.equals("UP"))
        {
            this.getEntityPlayer().walk(Direction.NORTH);
            return;
        }
    }

    public void inputKeyRelease(String key)
    {
        // DEBUG
        Console.print("SYSTEM -> INPUT KEY RELEASE (" + key + ")");
    }

    public void inputKeyType(String key)
    {
        // DEBUG
        Console.print("SYSTEM -> INPUT KEY TYPE (" + key + ")");
    }

    public void inputMouseClickL(MouseEvent e)
    {
        //
    }

    public void inputMouseClickR(MouseEvent e)
    {
        //
    }

    public void inputMouseMove(MouseEvent e)
    {
        //
    }
    
    public void message()
    {
        this.engineWorldMessageActive = false;
        this.engineWorldMessageObject = null;
    }
    
    public void message(WorldMessage message)
    {
        // DEBUG
        Console.print("SYSTEM -> MESSAGE");
        
        this.engineWorldMessageActive = true;
        this.engineWorldMessageObject = message;
    }
    
    public void render(Graphics g)
    {
        if(this.engineState == SystemState.WORLD) {this.renderWorld(g);}
    }
    
    private void renderWorld(Graphics g)
    {
        this.engineWorld.render(g);
        if(this.engineWorldPause) {this.renderWorldPause(g);}
        if(this.engineWorldMenuActive) {this.engineWorldMenuObject.render(g);}
        if(this.engineWorldMessageActive) {this.engineWorldMessageObject.render(g);}
        // NOTE: save the world to a bufferedImage to use as a background
    }
    
    private void renderWorldPause(Graphics g)
    {
        Drawing.fadeScreen(g, Color.GRAY);
        //
    }

    public void tick()
    {
        if(this.engineState == SystemState.WORLD) {this.tickWorld();}
    }
    
    private void tickWorld()
    {
        this.engineWorld.tick();
    }
    
    private void worldMenu(boolean enable)
    {
        if(enable) {this.engineWorldMenuActive = true;}
        else {this.engineWorldMenuActive = false;}
    }
    
    private void worldPause(boolean enable)
    {
        if(enable) {this.engineWorldPause = true;}
        else {this.engineWorldPause = false;}
    }
    
}
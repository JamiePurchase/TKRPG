package quests;

import java.util.ArrayList;
import system.ID;

public class Quest
{
    private ID id;
    private String name;
    private boolean stateFound, stateActive, stateComplete;
    private int stageNow;
    private ArrayList<Stage> stageArray;
    
    public Quest(ID id, String name, boolean found, boolean active, boolean complete, int stageNow, ArrayList<Stage> stageArray)
    {
        this.id = id;
        this.name = name;
        this.stateFound = found;
        this.stateActive = active;
        this.stateComplete = complete;
        this.stageNow = stageNow;
        this.stageArray = stageArray;
    }
    
}
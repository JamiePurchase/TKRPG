package quests;

import java.util.ArrayList;
import java.util.Date;
import system.ID;

public class QuestFile extends FileAbstract
{
    private ID id;
    private String name;
    private boolean stateFound, stateActive, stateComplete;
    private int stageNow;
    private ArrayList<Stage> stageArray;
    
    public QuestFile(ID id, String name, boolean found, boolean active, boolean complete, int stageNow, ArrayList<Stage> stageArray)
    {
        super(path, project, FileType.QUEST, update)
        this.id = id;
        this.name = name;
        this.stateFound = found;
        this.stateActive = active;
        this.stateComplete = complete;
        this.stageNow = stageNow;
        this.stageArray = stageArray;
    }
    
}
package quests;

import framework.files.FileAbstract;
import framework.files.FileDate;
import framework.files.FileType;
import java.util.ArrayList;
import java.util.Date;

public class QuestFile extends FileAbstract
{
    private String name;
    private boolean stateFound, stateActive, stateComplete;
    private int stageNow;
    private ArrayList<QuestStage> stageArray;
    
    public QuestFile(String path, String project, FileDate update, String name, boolean found, boolean active, boolean complete, int stageNow, ArrayList<QuestStage> stageArray)
    {
        super(name, path, project, FileType.QUEST, update);
        this.name = name;
        this.stateFound = found;
        this.stateActive = active;
        this.stateComplete = complete;
        this.stageNow = stageNow;
        this.stageArray = stageArray;
    }
    
    public void save()
    {
        ArrayList<String> data = new ArrayList();
        // data.add();
        this.saveFile(data);
    }
    
}
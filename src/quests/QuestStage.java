package quests;

public class QuestStage
{
    private QuestFile quest;
    private String objective;
    private String description;
    
    public QuestStage(QuestFile quest, String objective, String description)
    {
        this.quest = quest;
        this.objective = objective;
        this.description = description;
        
        // NOTE: should this be saved separately? extend FileAbstract?
    }
    
}
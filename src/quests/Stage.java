package quests;

import system.ID;

public class Stage
{
    private ID id;
    private String objective;
    private String description;
    private Marker marker;
    
    public Stage(ID id, String objective, String description, Marker marker)
    {
        this.id = id;
        this.objective = objective;
        this.description = description;
        this.marker = marker;
    }
}
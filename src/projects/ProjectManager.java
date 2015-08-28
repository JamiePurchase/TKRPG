package projects;

import app.Engine;
import file.FileService;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class ProjectManager
{
    
    public static void createProject(String name)
    {
        // Create the project file
        FileService.saveFile(name, getProjectDirectory(name + ".tk7pro"));
        
        // Create the project directory
        FileService.createFolder(Engine.getAppVariable("BUILDER_PATH") + "Data/" + name);
        
        // Create the data subdirectories
        for(int x = 0; x < getProjectFolders().size(); x++)
        {
            FileService.createFolder(Engine.getAppVariable("BUILDER_PATH") + "Data/" + name + "/" + getProjectFolders().get(x));
        }
        
        // Update ??? (list of recent projects?)
    }
    
    public static String getPath(String name)
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Projects/" + name + ".tk7pro";
    }
    
    public static ProjectFile getProject(File file)
    {
        String path = "";
        Date update = new Date();
        return new ProjectFile(path, FileService.getFileName(file), update);
        
        // NOTE: need to insert the path here
        // NOTE: need to get the update value as a string (from the file) and parse it into a date
    }
    
    public static ArrayList<ProjectFile> getProjectArray()
    {
        ArrayList<File> files = FileService.getFolder(getProjectDirectory(), true, false, "tk7pro");
        ArrayList<ProjectFile> projects = new ArrayList();
        for(int x = 0; x < files.size(); x++)
        {
            projects.add(getProject(files.get(x)));
        }
        return projects;
    }
    
    private static String getProjectDirectory()
    {
        return Engine.getAppVariable("BUILDER_PATH") + "Projects/";
    }
    
    private static String getProjectDirectory(String extend)
    {
        return getProjectDirectory() + extend;
    }
    
    private static ArrayList<String> getProjectFolders()
    {
        ArrayList<String> folders = new ArrayList();
        folders.add("Boards");
        folders.add("Characters");
        folders.add("Items");
        folders.add("Quests");
        folders.add("Scenes");
        folders.add("Terrain");
        // NOTE: this isn't everything!
        return folders;
    }
    
}
package projects;

import app.Engine;
import file.FileService;
import framework.files.FileDate;
import framework.files.FileItem;
import framework.files.FileManager;
import framework.files.FileType;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
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
        // Load the Project File
        ArrayList<String> data = FileService.loadFile(file.getPath());
        return new ProjectFile(file.getPath(), data.get(0), new FileDate(data.get(1)), data.get(2), data.get(3));
        
        // NOTE: need to get the update value as a string (from the file) and parse it into a date
    }
    
    public static ProjectFile getProject(FileItem file)
    {
        return getProject(file.getPath());
    }
    
    public static ProjectFile getProject(String file)
    {
        return getProject(new File(getPath(file)));
    }
    
    //public ArrayList<ProjectFile> getProjectArray()
    public static ArrayList<FileItem> getProjectArray()
    {
        ArrayList<File> files = FileService.getFolder(getProjectDirectory(), true, false, "tk7pro");
        //ArrayList<ProjectFile> projects = new ArrayList();
        ArrayList<FileItem> projects = new ArrayList();
        for(int x = 0; x < files.size(); x++)
        {
            //projects.add(getProject(files.get(x)));
            projects.add(new FileItem(files.get(x).getAbsoluteFile(), FileType.PROJECT, files.get(x).lastModified()));
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
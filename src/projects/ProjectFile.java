package projects;

import framework.files.FileAbstract;
import framework.files.FileDate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import framework.files.FileType;

public class ProjectFile extends FileAbstract
{
    private String initBoard, initCharacter;
    
    public ProjectFile(String path, String name, FileDate update, String board, String character)
    {
        super(name, path, name, FileType.PROJECT, update);
        this.initBoard = board;
        this.initCharacter = character;
    }
    
    public String getInfo()
    {
        // TEMP (version? file size? number of files? date saved?)
        // Note: could even iterate through all of the above (five seconds each?)
        return "V 1.0 - 15/08/2015";
    }
    
    public String getInitialBoard()
    {
        return this.initBoard;
    }
    
    public String getInitialCharacter()
    {
        return this.initCharacter;
    }
    
    public void save()
    {
        // Create an array of data
        ArrayList<String> data = new ArrayList();
        
        // Data: Project
        data.add(this.getFileName());
        data.add(this.getFileDate().getData());
        
        // Data: Initial Files
        data.add(this.getInitialBoard());
        data.add(this.getInitialCharacter());
        
        // Save the array of data
        this.saveFile(data);
    }
    
    public String savePath()
    {
        return this.getFilePath();
    }
    
    public void setInitialBoard(String board)
    {
        this.initBoard = board;
    }
    
    public void setInitialCharacter(String character)
    {
        this.initCharacter = character;
    }
    
}
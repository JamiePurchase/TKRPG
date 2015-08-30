package framework.files;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileDate
{
    private Date date;
    
    public FileDate()
    {
        this.date = new Date();
    }
    
    public FileDate(String data)
    {
        try {this.date = this.getFormat().parse(data);}
        catch (ParseException e) {System.out.println(e);}
    }
    
    public FileDate(Date date)
    {
        this.date = date;
    }
    
    public String getData()
    {
        return this.getFormat().format(this.date);
    }
    
    public String getDisplay()
    {
        return new SimpleDateFormat("dd/MM/yyyy").format(this.date);
    }
    
    public String getDisplay(String format)
    {
        return new SimpleDateFormat(format).format(this.date);
    }
    
    private SimpleDateFormat getFormat()
    {
        return new SimpleDateFormat("yyyy|MM|dd|HH|mm|ss");
    }
    
}
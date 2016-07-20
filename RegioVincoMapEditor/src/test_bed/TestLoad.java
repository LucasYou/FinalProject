
package test_bed;

import java.io.IOException;
import regiovincomapeditor.data.DataManager;
import regiovincomapeditor.file.FileManager;
import saf.AppTemplate;


public class TestLoad 
{
    AppTemplate app;
    public static FileManager filemanager = new FileManager();
    public static DataManager dataManager = new DataManager();
    
    
    public static void main(String[] args) throws IOException
    {
        filemanager.loadData(dataManager, "./work/Andorra");
        
        dataManager.Print();
        
        System.out.println();
        System.out.println();
        
        filemanager.loadData(dataManager, "./work/San Marino");
        dataManager.Print();
        
        System.out.println();
        System.out.println();
        
        filemanager.loadData(dataManager, "./work/Slovakia");
        dataManager.Print();

    }
    
    
    }
    


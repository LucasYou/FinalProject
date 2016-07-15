package test_bed;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




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
        filemanager.loadUI(dataManager, "./work/Andorra");
        
        dataManager.Print();
        
        System.out.println();
        System.out.println();
        
        filemanager.loadUI(dataManager, "./work/San Marino");
        dataManager.Print();
        
        System.out.println();
        System.out.println();
        
        filemanager.loadUI(dataManager, "./work/Slovakia");
        dataManager.Print();

    }
    
    
    }
    



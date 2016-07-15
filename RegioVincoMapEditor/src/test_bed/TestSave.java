package test_bed;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.File;
import java.io.IOException;
import java.io.Reader;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import properties_manager.PropertiesManager;
import regiovincomapeditor.data.DataManager;
import regiovincomapeditor.data.Subregions;
import regiovincomapeditor.file.FileManager;
import saf.AppTemplate;
import static saf.settings.AppPropertyType.SAVE_WORK_TITLE;
import static saf.settings.AppPropertyType.WORK_FILE_EXT;
import static saf.settings.AppPropertyType.WORK_FILE_EXT_DESC;
import static saf.settings.AppStartupConstants.PATH_WORK;


/**
 *
 * @author li zhao
 */
public class TestSave
{
    AppTemplate app;
    File currentWorkFile = null;
    public static FileManager filemanager = new FileManager();
    public static DataManager dataManager = new DataManager();
    
    static final String JSON_NAME = "name";
    static final String JSON_CAPITAL = "capital";
    static final String JSON_LEADER = "leader";
    
    
    static final String JSON_ParentDirectory = "parent directory";
    static final String JSON_BackGroundColor = "back-ground color";
    static final String JSON_BorderColor = "border color";
    static final String JSON_MapPath = "map path";
    static final String JSON_Width = "width";
    static final String JSON_Height = "height";
    static final String JSON_BorderThickness = "border thickness";
    static final String JSON_ZoomLevel = "zoom level";
    static final String JSON_SUBREGIONS = "subregions";
    
    public TestSave() {
        // NOTHING YET
        
    }
    
    public static void CreateAndorra()
    {
        //HARD CODE FOR MAP
        dataManager.reset();
        dataManager.setName("Andorra");
        dataManager.setParentDirectory("./export/The World/Europe/Andorra");
        dataManager.setBackGroundColor("#ff6600");
        dataManager.setBorderColor("#000000");
        dataManager.setMapPath("./raw_map_data/Andorra.json");
        dataManager.setImageX(50);
        dataManager.setImageY(50);
        dataManager.setImagePath("./export/The World/Europe/Andorra");
        dataManager.setWidth(802);
        dataManager.setHeight(536);
        dataManager.setBorderThickness(1);
        dataManager.setZoomLevel(1);
        dataManager.setScrollX(30);
        dataManager.setScrollY(50);
        
        Subregions subregion1 = new Subregions("Ordino", "Ordino (town)", "Ventura Espot", 200,200,200);
        Subregions subregion2 = new Subregions("Canillo", "Canillo (town)", "Enric Casadevall Medrano", 198,198,198);
        Subregions subregion3 = new Subregions("Encamp", "Encamp (town)", "Miquel Alís Font", 196,196,196);
        Subregions subregion4 = new Subregions("Escaldes-Engordany", "Escaldes-Engordany (town)", "Montserrat Capdevila Pallarés", 194,194,194);
        Subregions subregion5 = new Subregions("La Massana", "La Massana (town)", "Josep Areny", 192,192,192);
        Subregions subregion6 = new Subregions("Andorra la Vella", "Andorra la Vella (city)", "Maria Rosa Ferrer Obiols", 190,190,190);
        Subregions subregion7 = new Subregions("Sant Julia de Loria", "Sant Julia de Loria (town)", "Josep Pintat Forné", 188,188,18);
        dataManager.addItem(subregion1);
        dataManager.addItem(subregion2);
        dataManager.addItem(subregion3);
        dataManager.addItem(subregion4);
        dataManager.addItem(subregion5);
        dataManager.addItem(subregion6);
        dataManager.addItem(subregion7);
    }
    
    public static void CreateSanMarino()
    {
        dataManager.reset();
        dataManager.setName("San Marino");
        dataManager.setParentDirectory("./export/The World/Europe/San Marino");
        dataManager.setBackGroundColor("#ff6600");
        dataManager.setBorderColor("#000000");
        dataManager.setMapPath("./raw_map_data/San Marino.json");
        dataManager.setImageX(100);
        dataManager.setImageY(100);
        dataManager.setImagePath("./export/The World/Europe/San Marino");
        dataManager.setWidth(802);
        dataManager.setHeight(536);
        dataManager.setBorderThickness(1);
        dataManager.setZoomLevel(1);
        dataManager.setScrollX(50);
        dataManager.setScrollY(70);
        
        Subregions subregion1 = new Subregions("Acquaviva", "Lucia Tamagnini", "", 25,25,25);

        
    }
    
    public static void CreateSlovakia()
    {
        dataManager.reset();
        dataManager.setName("Slovakia");
        dataManager.setParentDirectory("./export/The World/Europe/Slovakia");
        dataManager.setBackGroundColor("#ff6600");
        dataManager.setBorderColor("#000000");
        dataManager.setMapPath("./raw_map_data/Slovakia.json");
        dataManager.setImagePath("./export/The World/Europe/Slovakia");
        dataManager.setImageX(150);
        dataManager.setImageY(150);
        dataManager.setWidth(802);
        dataManager.setHeight(536);
        dataManager.setBorderThickness(1);
        dataManager.setZoomLevel(1);
        dataManager.setScrollX(70);
        dataManager.setScrollY(90);
        

        
        
    }
    public static void main(String[] args) throws IOException
    {
        JFrame frame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        //fileChooser.showOpenDialog(frame);
        File selFile = fileChooser.getSelectedFile();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        CreateAndorra();
        filemanager.saveData(dataManager, "./work/Andorra");
        //filemanager.saveData(dataManager, selFile.getPath());
 
        CreateSanMarino();
        filemanager.saveData(dataManager, "./work/San Marino");
        //filemanager.saveData(dataManager, selFile.getPath());
        CreateSlovakia();
        filemanager.saveData(dataManager, "./work/Slovakia");
        //filemanager.saveData(dataManager, selFile.getPath());
    }
}

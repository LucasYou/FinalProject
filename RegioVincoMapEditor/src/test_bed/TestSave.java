
package test_bed;

import java.io.File;
import java.io.IOException;
import regiovincomapeditor.data.DataManager;
import regiovincomapeditor.data.Subregions;
import regiovincomapeditor.file.FileManager;
import saf.AppTemplate;


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
        dataManager.setParentDirectory("./HW5SampleData/export/The World/Europe/Andorra");
        dataManager.setBackGroundColor("#ff6600");
        dataManager.setBorderColor("#000000");
        dataManager.setMapPath("./HW5SampleData/raw_map_data/Andorra.json");
        dataManager.setImagePath("./HW5SampleData/export/The World/Europe/Andorra");
        dataManager.setImageX(10.0);
        dataManager.setImageY(20.0);
        dataManager.setWidth(802);
        dataManager.setHeight(536);
        dataManager.setBorderThickness(1);
        dataManager.setZoomLevel(1);
        dataManager.setScrollX(5);
        dataManager.setScrollY(10);
        
        
        
        Subregions subregion1 = new Subregions("Ordino", "Ordino (town)", "Ventura Espot", 200,200,200);
        Subregions subregion2 = new Subregions("Carillo", "Carillo (town)", "Enric Casadevall Medrano", 198,198,198);
        Subregions subregion3 = new Subregions("Encamp", "Encamp (town)", "Miquel Als Font", 196,196,196);
        Subregions subregion4 = new Subregions("Escaldes-Engordany", "Escaldes-Engordany (town)", "Montserrat Capdevila Pallarés", 194,194,194);
        Subregions subregion5 = new Subregions("La Massana", "La Massana (town)", "Josep Areny", 192,192,192);
        Subregions subregion6 = new Subregions("Andorra la Vella", "Andorra la Vella (city)", "Maria Rosa Ferrer Obiols", 190,190,190);
        Subregions subregion7 = new Subregions("Sant Julia de Loria", "Sant Julia de Loria (town)", "Josep Pintat Forné", 188,188,188);

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
        dataManager.setParentDirectory("./HW5SampleData/export/The World/Europe/San Marino");
        dataManager.setBackGroundColor("#ff6600");
        dataManager.setBorderColor("#000000");
        dataManager.setMapPath("./HW5SampleData/raw_map_data/San Marino.json");
        dataManager.setImagePath("./HW5SampleData/export/The World/Europe/San Marino");
        dataManager.setImageX(50.0);
        dataManager.setImageY(60.0);
        dataManager.setWidth(802);
        dataManager.setHeight(536);
        dataManager.setBorderThickness(1);
        dataManager.setZoomLevel(1);
        dataManager.setScrollX(15);
        dataManager.setScrollY(20);
        
        Subregions subregion1 = new Subregions("Acquaviva", "Lucia Tamagnini", 225,225,225);
        Subregions subregion2 = new Subregions("Borgo Maggiore", "Sergio Nanni", 200,20,200);
        Subregions subregion3 = new Subregions("Chiesanuova", "Franco Santi", 175,175,175);
        Subregions subregion4 = new Subregions("Domagnano", "Gabriel Guidi", 150,150,150);
        Subregions subregion5 = new Subregions("Faetano", "Pier Mario Bedetti", 125,125,125);
        Subregions subregion6 = new Subregions("Fiorentino", "Gerri Fabbri", 100,100,100);
        Subregions subregion7 = new Subregions("Montegiardino", "Marta Fabbri", 75,75,75);
        Subregions subregion8 = new Subregions("City of San Marino", "Maria Teresa Beccari", 50,50,50);
        Subregions subregion9 = new Subregions("Serravalle", "Leandro Maiani", 25,25,25);
        
        dataManager.addItem(subregion1);
        dataManager.addItem(subregion2);
        dataManager.addItem(subregion3);
        dataManager.addItem(subregion4);
        dataManager.addItem(subregion5);
        dataManager.addItem(subregion6);
        dataManager.addItem(subregion7);
        dataManager.addItem(subregion8);
        dataManager.addItem(subregion9);
        


        
    }
    
    public static void CreateSlovakia()
    {
        dataManager.reset();
        dataManager.setName("Slovakia");
        dataManager.setParentDirectory("./HW5SampleData/export/The World/Europe/Slovakia");
        dataManager.setBackGroundColor("#ff6600");
        dataManager.setBorderColor("#000000");
        dataManager.setMapPath("./HW5SampleData/raw_map_data/Slovakia.json");
        dataManager.setImagePath("./HW5SampleData/export/The World/Europe/Slovakia");
        dataManager.setImageX(50.0);
        dataManager.setImageY(60.0);
        dataManager.setWidth(802);
        dataManager.setHeight(536);
        dataManager.setBorderThickness(1);
        dataManager.setZoomLevel(1);
        dataManager.setScrollX(25);
        dataManager.setScrollY(30);
        
        
        Subregions subregion1 = new Subregions("Bratislava", 250,250,250);
        Subregions subregion2 = new Subregions("Trnava", 249,249,249);
        Subregions subregion3 = new Subregions("Trencin", 248,248,248);
        Subregions subregion4 = new Subregions("Nitra", 247,247,247);
        Subregions subregion5 = new Subregions("Zilina", 246,246,246);
        Subregions subregion6 = new Subregions("Banska Bystrica", 245,245,245);
        Subregions subregion7 = new Subregions("Presov", 244,244,244);
        Subregions subregion8 = new Subregions("Kosice", 243,243,243);
        dataManager.addItem(subregion1);
        dataManager.addItem(subregion2);
        dataManager.addItem(subregion3);
        dataManager.addItem(subregion4);
        dataManager.addItem(subregion5);
        dataManager.addItem(subregion6);
        dataManager.addItem(subregion7);
        dataManager.addItem(subregion8);
        
        
    }
    public static void main(String[] args) throws IOException
    {
        
        CreateAndorra();
        filemanager.saveData(dataManager, "./work/Andorra");
        
        
        CreateSanMarino();
        filemanager.saveData(dataManager, "./work/San Marino");
        
        
        CreateSlovakia();
        filemanager.saveData(dataManager, "./work/Slovakia");
        
        //System.out.println(dataManager.haveCapitals());
        //System.out.println(dataManager.haveFlags());
        //System.out.println(dataManager.haveLeaders());
   
    }
}

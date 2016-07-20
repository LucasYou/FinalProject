/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_bed;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import regiovincomapeditor.data.DataManager;
import regiovincomapeditor.data.Subregions;
import regiovincomapeditor.file.FileManager;
import static test_bed.TestSave.dataManager;

/**
 *
 * @author li zhao
 */
public class TestSaveTest {
    
    public static FileManager filemanager = new FileManager();
    public static DataManager dataManager = new DataManager();
    public TestSaveTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of CreateAndorra method, of class TestSave.
     */
    @Test
    public void testCreateAndorra() {
        System.out.println("Test Andorra");
        
        dataManager.reset();
        dataManager.setName("Andorra");
        dataManager.setParentDirectory("./export/The World/Europe/Andorra");
        dataManager.setBackGroundColor("#ff6600");
        dataManager.setBorderColor("#000000");
        dataManager.setMapPath("./HW5SampleData/raw_map_data/Andorra.json");
        dataManager.setImagePath("./export/The World/Europe/Andorra");
        dataManager.setImageX(10.0);
        dataManager.setImageY(20.0);
        dataManager.setWidth(802);
        dataManager.setHeight(536);
        dataManager.setBorderThickness(1);
        dataManager.setZoomLevel(1);
        dataManager.setScrollX(5);
        dataManager.setScrollY(10);
        
        
        
        Subregions subregion1 = new Subregions("Ordino", "Ordino (town)", "Ventura Espot", 200,200,200);
        Subregions subregion2 = new Subregions("Canillo", "Canillo (town)", "Enric Casadevall Medrano", 198,198,198);
        Subregions subregion3 = new Subregions("Encamp", "Encamp (town)", "Miquel Alís Font", 196,196,196);
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
        
        assertEquals("Andorra" , dataManager.getName());
        assertEquals("Ordino" , dataManager.getItems().get(0).getName());
        assertEquals("./export/The World/Europe/Andorra" , dataManager.getParentDirectory());
        assertEquals("./HW5SampleData/raw_map_data/Andorra.json" , dataManager.getMapPath());
        assertEquals("Encamp (town)" , dataManager.getItems().get(2).getCapital());

    }

    /**
     * Test of CreateSanMarino method, of class TestSave.
     */
    @Test
    public void testCreateSanMarino() {
        System.out.println("Test SanMarino");
        
        dataManager.reset();
        dataManager.setName("San Marino");
        dataManager.setParentDirectory("./export/The World/Europe/San Marino");
        dataManager.setBackGroundColor("#ff6600");
        dataManager.setBorderColor("#000000");
        dataManager.setMapPath("./HW5SampleData/raw_map_data/San Marino.json");
        dataManager.setImagePath("./export/The World/Europe/San Marino");
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
        
        assertEquals("San Marino" , dataManager.getName());
        assertEquals("Acquaviva" , dataManager.getItems().get(0).getName());
        assertEquals("./export/The World/Europe/San Marino" , dataManager.getParentDirectory());
        assertEquals("./HW5SampleData/raw_map_data/San Marino.json" , dataManager.getMapPath());
        assertEquals("" , dataManager.getItems().get(2).getCapital());
 
    }

    /**
     * Test of CreateSlovakia method, of class TestSave.
     */
    @Test
    public void testCreateSlovakia() {
        System.out.println("Test Slovakia");
        
        dataManager.reset();
        dataManager.setName("Slovakia");
        dataManager.setParentDirectory("./export/The World/Europe/Slovakia");
        dataManager.setBackGroundColor("#ff6600");
        dataManager.setBorderColor("#000000");
        dataManager.setMapPath("./HW5SampleData/raw_map_data/Slovakia.json");
        dataManager.setImagePath("./export/The World/Europe/Slovakia");
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
        
        assertEquals("Slovakia" , dataManager.getName());
        assertEquals("Bratislava" , dataManager.getItems().get(0).getName());
        assertEquals("./export/The World/Europe/Slovakia" , dataManager.getParentDirectory());
        assertEquals("./HW5SampleData/raw_map_data/Slovakia.json" , dataManager.getMapPath());
        assertEquals("" , dataManager.getItems().get(2).getCapital());

    }

    
}

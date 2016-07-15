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
import regiovincomapeditor.data.Subregions;
import regiovincomapeditor.file.FileManager;
import static test_bed.TestSave.dataManager;

/**
 *
 * @author liyou
 */
public class TestSaveTest {
    public static FileManager filemanager = new FileManager();
    
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
        
        assertEquals("Andorra",dataManager.getName());
        //TestSave.CreateAndorra();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of CreateSanMarino method, of class TestSave.
     */
    @Test
    public void testCreateSanMarino() {
        System.out.println("Test San Marino");

    }

    /**
     * Test of CreateSlovakia method, of class TestSave.
     */
    @Test
    public void testCreateSlovakia() {
        System.out.println("Test Slovakia");

    }

    /**
     * Test of main method, of class TestSave.
     */

}

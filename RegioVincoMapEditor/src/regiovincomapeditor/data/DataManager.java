/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regiovincomapeditor.data;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import saf.AppTemplate;
import saf.components.AppDataComponent;

/**
 *
 * @author li zhao
 */
public class DataManager implements AppDataComponent {
    
    ObservableList<Subregions> subregion;
    
    AppTemplate app;    
    
    ArrayList <Double> arrayX;
    ArrayList <Double> arrayY;
    ArrayList <Double> coordinateX;
    ArrayList <Double> coordinateY;
    ArrayList subregions;
    ArrayList subregions_polygon;
    
    String Name;
    String ParentDirectory;
    String BackGroundColor = "#ff6600";
    String BorderColor;
    String MapPath;
    String ImagePath;
    double ImageX;
    double ImageY;
    
    int Width;
    int Height;
    int BorderThickness;
    int ZoomLevel;
    double ScrollX;
    double ScrollY;
    
    
    
    public DataManager(AppTemplate initApp) throws Exception {
	// KEEP THE APP FOR LATER
	app = initApp;
        subregion = FXCollections.observableArrayList();
        arrayX = new ArrayList();
        arrayY = new ArrayList();
        coordinateX = new ArrayList();
        coordinateY = new ArrayList();
        subregions = new ArrayList();
        subregions_polygon = new ArrayList();
        Name = "default map name";
        ParentDirectory = "";
        //BackGroundColor = "#ff6600";
        BorderColor = "";
        MapPath = "";
        ImagePath = "";
        ImageX = 0.0;
        ImageY = 0.0;
        Width = 802;
        Height = 536;
        BorderThickness = 1;
        ZoomLevel = 1; 
        ScrollX = 0.0;
        ScrollY = 0.0;
        /*
        Subregions China = new Subregions("China", "Bei Jing", "Xi");
        Subregions America = new Subregions("United States", "Washington", "Obama");
        Subregions England = new Subregions("England", "London", "Cameron");
        
        addItem(China);
        addItem(America);
        addItem(England);
        */
        
    }

    public DataManager() {
        subregion = FXCollections.observableArrayList();
        arrayX = new ArrayList();
        arrayY = new ArrayList();
        coordinateX = new ArrayList();
        coordinateY = new ArrayList();
        subregions = new ArrayList();
        subregions_polygon = new ArrayList();
        Name = "default map name";
        ParentDirectory = "";
        //BackGroundColor = "#ff6600";
        BorderColor = "";
        MapPath = "";
        ImagePath = "";
        ImageX = 0.0;
        ImageY = 0.0;
        Width = 802;
        Height = 536;
        BorderThickness = 1;
        ZoomLevel = 1; 
        ScrollX = 0.0;
        ScrollY = 0.0;
        
    }
    
    
    public ObservableList<Subregions> getItems() {
	return subregion;
    }


    public void addItem(Subregions subregions) {
        subregion.add(subregions);
    } 
    
    //SETTER METHODS
    public void addArrayX(Double X) {arrayX.add(X);} 
    public void addArrayY(Double Y) {arrayY.add(Y);} 
    public void addcoordinateX(Double X) {coordinateX.add(X);} 
    public void addcoordinateY(Double Y) {coordinateY.add(Y);} 
    public void addSubregions(int subregion) {subregions.add(subregion);}
    public void addSubregionsPolygon(int polygon) {subregions_polygon.add(polygon);} 
    public void setName(String name) {Name = name;};
    public void setParentDirectory(String parentdirectory) {ParentDirectory = parentdirectory;}; 
    public void setBackGroundColor(String backgroundcolor) {BackGroundColor = backgroundcolor;}; 
    public void setBorderColor(String bordercolor) {BorderColor = bordercolor;}; 
    public void setMapPath(String mappath) {MapPath = mappath;};
    public void setImagePath(String imagepath) {ImagePath = imagepath;};
    public void setImageX(double imagex) {ImageX = imagex;}
    public void setImageY(double imagey) {ImageY = imagey;}
    public void setWidth(int width) {Width = width;}
    public void setHeight(int height) {Height = height;}
    public void setBorderThickness(int borderthickness) {BorderThickness = borderthickness;}
    public void setZoomLevel(int zoomlevel) {ZoomLevel = zoomlevel;}
    public void setScrollX(double scrollx) {ScrollX = scrollx;}
    public void setScrollY(double scrolly) {ScrollY = scrolly;}
    
    //GETTER METHODS
    public ArrayList<Double> getArrayX() {return arrayX;}
    public ArrayList<Double> getArrayY() {return arrayY;}
    public ArrayList<Double> getcoordinateX() {return coordinateX;}
    public ArrayList<Double> getcoordinateY() {return coordinateY;}
    public ArrayList getSubregions() {return subregions;} 
    public ArrayList getSubregionsPolygon() {return subregions_polygon;}
    public String getName() {return Name;}
    public String getParentDirectory() {return ParentDirectory;}
    public String getBackGroundColor() {return BackGroundColor;}
    public String getBorderColor() {return BorderColor;}
    public String getMapPath() {return MapPath;}
    public double getImageX() {return ImageX;}
    public double getImageY() {return ImageY;}
    public String getImagePath() {return ImagePath;}
    public int getWidth() {return Width;}
    public int getHeight() {return Height;}
    public int getBorderThickness() {return BorderThickness;}
    public double getZoomLevel() {return ZoomLevel;}
    public double getScrollX() {return ScrollX;}
    public double getScrollY() {return ScrollY;}
    

    public void Print()
    {
        System.out.println("Name: " + Name);
        System.out.println("ParentDirectory: " + ParentDirectory);
        System.out.println("BackGroundColor: " + BackGroundColor);
        System.out.println("BorderColor: " + BorderColor);
        System.out.println("MapPath: " + MapPath);
        System.out.println("ImagePath: " + ImagePath);
        System.out.println("Image xy location: " + ImageX + "," + ImageY);
        System.out.println("Width: " + Width);
        System.out.println("Height: " + Height);
        System.out.println("BorderThickness: " + BorderThickness);
        System.out.println("ZoomLevel: " + ZoomLevel);
        System.out.println("Scroll xy location: " + ScrollX + "," + ScrollY);
        System.out.println("subregion name: " + subregion.get(0).getName());
        System.out.println("subregion capital: " + subregion.get(0).getCapital());
        System.out.println("subregion leader: " + subregion.get(0).getLeader());
        System.out.println("subregion color red: " + subregion.get(0).getRed());
        System.out.println("subregion color green: " + subregion.get(0).getGreen());
        System.out.println("subregion color blue: " + subregion.get(0).getBlue());
    }
    
    
    
    
    
    @Override
    public void reset() 
    {

        arrayX.clear();
        arrayY.clear();
        subregions.clear();
        subregions_polygon.clear();
        coordinateX.clear();
        coordinateY.clear();
        Name = "default map name";
        ParentDirectory = "";
        //BackGroundColor = "#ff6600";
        BorderColor = "";
        MapPath = "";
        ImagePath = "";
        ImageX = 0.0;
        ImageY = 0.0;
        Width = 802;
        Height = 536;
        BorderThickness = 1;
        ZoomLevel = 1; 
        ScrollX = 0.0;
        ScrollY = 0.0;
        
        subregion.clear();
        
    }
    
}

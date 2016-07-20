/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regiovincomapeditor.data;

import java.io.File;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import saf.AppTemplate;
import saf.NewMapDialog;
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
    double BorderThickness;
    int ZoomLevel;
    int ScrollX;
    int ScrollY;
    
    
    
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
        Name = "";
        ParentDirectory = "";
        //BackGroundColor = "#ff6600";
        BorderColor = "";
        MapPath = "";
        ImagePath = "";
        ImageX = 0.0;
        ImageY = 0.0;
        Width = 802;
        Height = 536;
        BorderThickness = 0.001;
        ZoomLevel = 50; 
        ScrollX = 0;
        ScrollY = 0;    
    }
    

    public DataManager() {
        subregion = FXCollections.observableArrayList();
        arrayX = new ArrayList();
        arrayY = new ArrayList();
        coordinateX = new ArrayList();
        coordinateY = new ArrayList();
        subregions = new ArrayList();
        subregions_polygon = new ArrayList();
        Name = "";
        ParentDirectory = "";
        BackGroundColor = "#ff6600";
        BorderColor = "";
        MapPath = "";
        ImagePath = "";
        ImageX = 0.0;
        ImageY = 0.0;
        Width = 802;
        Height = 536;
        BorderThickness = 0.001;
        ZoomLevel = 50; 
        ScrollX = 0;
        ScrollY = 0;

        
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
    public void setBorderThickness(double borderthickness) {BorderThickness = borderthickness;}
    public void setZoomLevel(int zoomlevel) {ZoomLevel = zoomlevel;}
    public void setScrollX(int scrollx) {ScrollX = scrollx;}
    public void setScrollY(int Scrolly) {ScrollY = Scrolly;}
    
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
    public String getImagePath() {return ImagePath;}
    public double getImageX() {return ImageX;}
    public double getImageY() {return ImageY;}
    public int getWidth() {return Width;}
    public int getHeight() {return Height;}
    public double getBorderThickness() {return BorderThickness;}
    public int getZoomLevel() {return ZoomLevel;}
    public int getScrollX() {return ScrollX;}
    public int getScrollY() {return ScrollY;}
    

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

        NewMapDialog newMapDialog = NewMapDialog.getSingleton();
        arrayX.clear();
        arrayY.clear();
        subregions.clear();
        subregions_polygon.clear();
        coordinateX.clear();
        coordinateY.clear();
        
        Name = newMapDialog.getFileName();
        ParentDirectory = newMapDialog.getDirectoryPath();
        BackGroundColor = "#ff6600";
        BorderColor = "#000000";
        MapPath = newMapDialog.getDataPath();
        ImagePath = newMapDialog.getDirectoryPath();
        ImageX = 0.0;
        ImageY = 0.0;
        Width = 802;
        Height = 536;
        BorderThickness = 0.001;
        ZoomLevel = 50;
        ScrollX = 0;
        ScrollY = 0;
        
        subregion.clear();
        
    }
    
    public boolean haveCapitals()
    {
        if(subregion.isEmpty())
            return false;
        for (int i = 0; i <subregion.size(); i++)
        {
            if(subregion.get(i).getCapital().equals(""))
                return false;
        }
        
        return true;
    }
    
    public boolean haveFlags()
    {
            if(subregion.isEmpty())
            return false;
            for(int i = 0; i < subregion.size(); i++)
            {
                File file = new File(ImagePath +"/" + subregion.get(i).getName() + " Flag.png");
                if(file.exists() == false)
                    return false;
                
            }
      
                return true;
    }
    
    public boolean haveLeaders()
    {
        if(subregion.isEmpty())
            return false;
        for(int i = 0; i < subregion.size(); i++)
        {
            File file = new File(ImagePath +"/" + subregion.get(i).getLeader() + ".png");
            if(file.exists() == false)
                    return false;
        }
        return true;
    }
}

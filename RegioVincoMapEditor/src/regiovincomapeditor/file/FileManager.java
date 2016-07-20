/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regiovincomapeditor.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.ObservableList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import regiovincomapeditor.data.DataManager;
import regiovincomapeditor.data.Subregions;
import saf.components.AppDataComponent;
import saf.components.AppFileComponent;

/**
 *
 * @author li zhao
 */
public class FileManager implements AppFileComponent 
{

    static final String JSON_NAME = "name";
    static final String NUMBER_OF_SUBREGIONS = "NUMBER_OF_SUBREGIONS";
    static final String SUBREGIONS = "SUBREGIONS";
    static final String NUMBER_OF_SUBREGION_POLYGONS = "NUMBER_OF_SUBREGION_POLYGONS";
    static final String SUBREGION_POLYGONS = "SUBREGION_POLYGONS";
    
    static final String JSON_SUBREGIONS_HAVE_CAPITALS = "subregions_have_capitals";
    static final String JSON_SUBREGIONS_HAVE_FLAGS = "subregions_have_flags";
    static final String JSON_SUBREGIONS_HAVE_LEADERS = "subregions_have_leaders";
    
    static final String JSON_CAPITAL = "capital";
    static final String JSON_LEADER = "leader";
    static final String JSON_RED = "red";
    static final String JSON_GREEN = "green";
    static final String JSON_BLUE = "blue";

    
    
    static final String JSON_ParentDirectory = "parent directory";
    static final String JSON_BackGroundColor = "back-ground color";
    static final String JSON_BorderColor = "border color";
    static final String JSON_MapPath = "map path";
    static final String JSON_ImagePath = "image path";
    static final String JSON_ImageX = "image x";
    static final String JSON_ImageY = "image y";
    static final String JSON_Width = "width";
    static final String JSON_Height = "height";
    static final String JSON_BorderThickness = "border thickness";
    static final String JSON_ZoomLevel = "zoom level";
    static final String JSON_ScrollX = "scroll x";
    static final String JSON_ScrollY = "scroll y";
    
    static final String JSON_SUBREGIONS = "subregions";
    
    
    int total;

    @Override
    public void saveData(AppDataComponent data, String filePath) throws FileNotFoundException
    {
        DataManager dataManager = (DataManager)data;
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        ObservableList<Subregions> subregions = dataManager.getItems();
        
        for (Subregions subregion : subregions) {	    
	    JsonObjectBuilder itemJson = Json.createObjectBuilder()
		    .add(JSON_NAME, subregion.getName())
		    .add(JSON_CAPITAL, subregion.getCapital())
                    .add(JSON_LEADER, subregion.getLeader())
                    .add(JSON_RED, subregion.getRed())
                    .add(JSON_GREEN, subregion.getRed())
                    .add(JSON_BLUE, subregion.getRed());
	    arrayBuilder.add(itemJson);
	}
        
	JsonArray itemsArray = arrayBuilder.build();
        
        JsonObject dataManagerJSO = Json.createObjectBuilder()
		.add(JSON_NAME, dataManager.getName())
                .add(JSON_ParentDirectory, dataManager.getParentDirectory())
                .add(JSON_BackGroundColor, dataManager.getBackGroundColor())
                .add(JSON_BorderColor, dataManager.getBorderColor())
                .add(JSON_MapPath, dataManager.getMapPath())
                .add(JSON_ImagePath, dataManager.getImagePath())
                .add(JSON_ImageX, dataManager.getImageX())
                .add(JSON_ImageY, dataManager.getImageY())
                .add(JSON_Width, dataManager.getWidth())
                .add(JSON_Height, dataManager.getHeight())
                .add(JSON_BorderThickness, Double.toString(dataManager.getBorderThickness()))
                .add(JSON_ZoomLevel, dataManager.getZoomLevel())
                .add(JSON_ScrollX, dataManager.getScrollX())
                .add(JSON_ScrollY, dataManager.getScrollY())
		.add(JSON_SUBREGIONS, itemsArray)
		.build(); 
        
        Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();
        
        
        OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }

    @Override
    public void loadData(AppDataComponent data, String filePath) throws IOException 
    {
        //CLEAR THE OLD DATA
        DataManager dataManager = (DataManager)data;
        dataManager.reset();    
        
        JsonObject json = loadJSONFile(filePath);
        
        String Name = json.getString(JSON_NAME);
        String ParentDirectory = json.getString(JSON_ParentDirectory);
        String BackGroundColor = json.getString(JSON_BackGroundColor);
        String BorderColor = json.getString(JSON_BorderColor);
        String MapPath = json.getString(JSON_MapPath);
        String ImagePath = json.getString(JSON_ImagePath);
        double ImageX = json.getInt(JSON_ImageX);
        double ImageY = json.getInt(JSON_ImageY);
        int Width = json.getInt(JSON_Width);
        int Height = json.getInt(JSON_Height);
        String BorderThickness = json.getString(JSON_BorderThickness);
        int ZoomLevel = json.getInt(JSON_ZoomLevel);
        int ScrollX = json.getInt(JSON_ScrollX);
        int ScrollY = json.getInt(JSON_ScrollY);

        
        dataManager.setName(Name);
        dataManager.setParentDirectory(ParentDirectory);
        dataManager.setBackGroundColor(BackGroundColor);
        dataManager.setBorderColor(BorderColor);
        dataManager.setMapPath(MapPath);
        dataManager.setImagePath(ImagePath);
        dataManager.setImageX(ImageX);
        dataManager.setImageY(ImageY);
        dataManager.setWidth(Width);
        dataManager.setHeight(Height);
        dataManager.setBorderThickness(Double.parseDouble(BorderThickness));
        dataManager.setZoomLevel(ZoomLevel);
        dataManager.setScrollX(ScrollX);
        dataManager.setScrollY(ScrollY);
       
        JsonArray jsonItemArray = json.getJsonArray(JSON_SUBREGIONS);
        for (int i = 0; i < jsonItemArray.size(); i++) {
            JsonObject jsonItem = jsonItemArray.getJsonObject(i);
            Subregions subregions = loadItem(jsonItem);
            dataManager.addItem(subregions);
        }
        
        
        
        JsonObject jsons = loadJSONFile(dataManager.getMapPath());
        JsonArray jsonSubregionsArray = jsons.getJsonArray(SUBREGIONS);
        
        //progressbar.show("loading", 1);
        for (int i = 0; i < jsonSubregionsArray.size(); i++) 
        {
            dataManager.addSubregions(i);
            JsonObject json1 = jsonSubregionsArray.getJsonObject(i);
            JsonArray jsonSubregionsPolygonsArray = json1.getJsonArray(SUBREGION_POLYGONS); //8
            
            for(int j = 0; j < jsonSubregionsPolygonsArray.size(); j++)
            {
                JsonArray items = jsonSubregionsPolygonsArray.getJsonArray(j);
                total += items.size();
                
                //System.out.println(items);
                for(int k = 0; k  < items.size(); k++)
                {
                        
                    JsonObject jsonItem = items.getJsonObject(k);
                    dataManager.addArrayX(getDataAsDouble(jsonItem,"X"));
                    dataManager.addArrayY(getDataAsDouble(jsonItem,"Y"));
                    
                }
            }
            dataManager.addSubregionsPolygon(total);
            total = 0;
        }
        
    }
    
    public Subregions loadItem(JsonObject jsonItem) {
	// GET THE DATA
	String name = jsonItem.getString(JSON_NAME);
	String capital = jsonItem.getString(JSON_CAPITAL);
        String leader = jsonItem.getString(JSON_LEADER);
        int red = jsonItem.getInt(JSON_RED);
        int green =jsonItem.getInt(JSON_GREEN);
        int blue = jsonItem.getInt(JSON_BLUE);
	// THEN USE THE DATA TO BUILD AN ITEM
        Subregions subregions = new Subregions(name, capital, leader, red, green,blue);
        
	// ALL DONE, RETURN IT
	return subregions;
    }


    @Override
    public void exportData(AppDataComponent data, String filePath) throws IOException 
    {
        
        DataManager dataManager = (DataManager)data;
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        ObservableList<Subregions> subregions = dataManager.getItems();
        
        for (Subregions subregion : subregions) {	    
	    JsonObjectBuilder itemJson = Json.createObjectBuilder()
		    .add(JSON_NAME, subregion.getName())
		    .add(JSON_CAPITAL, subregion.getCapital())
                    .add(JSON_LEADER, subregion.getLeader())
                    .add(JSON_RED, subregion.getRed())
                    .add(JSON_GREEN, subregion.getRed())
                    .add(JSON_BLUE, subregion.getRed());
	    arrayBuilder.add(itemJson);
	}
        
	JsonArray itemsArray = arrayBuilder.build();
        
        JsonObject dataManagerJSO = Json.createObjectBuilder()
		.add(JSON_NAME, dataManager.getName())
                .add(JSON_SUBREGIONS_HAVE_CAPITALS, dataManager.haveCapitals())
                .add(JSON_SUBREGIONS_HAVE_FLAGS, dataManager.haveFlags())
                .add(JSON_SUBREGIONS_HAVE_LEADERS, dataManager.haveLeaders())
		.add(JSON_SUBREGIONS, itemsArray)
		.build(); 
        
        Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();
        
        
        OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }

    @Override
    public void importData(AppDataComponent data, String filePath) throws IOException {
        
    }
    
    public double getDataAsDouble(JsonObject json, String dataName) {
	JsonValue value = json.get(dataName);
	JsonNumber number = (JsonNumber)value;
	return number.bigDecimalValue().doubleValue();	
    }
    
    public int getDataAsInt(JsonObject json, String dataName) {
        JsonValue value = json.get(dataName);
        JsonNumber number = (JsonNumber)value;
        return number.bigIntegerValue().intValue();
    }
    
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	BufferedReader bufRdr = new BufferedReader(new InputStreamReader(new FileInputStream(jsonFilePath),"UTF8"));
	JsonReader jsonReader = Json.createReader(bufRdr);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	bufRdr.close();
	return json;
    }
    
    public void loadMap(AppDataComponent data, String filePath) throws IOException
    {
        DataManager dataManager = (DataManager)data;
	dataManager.reset();
        
        //5 - 2088
        JsonObject jsons = loadJSONFile(filePath);
        JsonArray jsonSubregionsArray = jsons.getJsonArray(SUBREGIONS); //8
        //System.out.println(jsonSubregionsArray.size());
        for (int i = 0; i < jsonSubregionsArray.size(); i++) 
        {
            dataManager.addSubregions(i);
            JsonObject json1 = jsonSubregionsArray.getJsonObject(i);
            JsonArray jsonSubregionsPolygonsArray = json1.getJsonArray(SUBREGION_POLYGONS);
            //System.out.println(jsonSubregionsPolygonsArray.size());
            for(int j = 0; j < jsonSubregionsPolygonsArray.size(); j++)
            {
                
                JsonArray items = jsonSubregionsPolygonsArray.getJsonArray(j);
                total += items.size();

                for(int k = 0; k  < items.size(); k++)
                {
                    
                    JsonObject jsonItem = items.getJsonObject(k);
                    dataManager.addArrayX(getDataAsDouble(jsonItem,"X"));
                    dataManager.addArrayY(getDataAsDouble(jsonItem,"Y"));
                }
            
            }
            //System.out.println(total);
            dataManager.addSubregionsPolygon(total);
            total = 0;
            
        }
        //System.out.println(dataManager.getArrayX().size());
    }

    
}

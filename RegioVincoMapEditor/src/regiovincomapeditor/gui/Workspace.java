/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regiovincomapeditor.gui;

import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;
import regiovincomapeditor.controller.RegioVincoMapEditorController;
import saf.AppTemplate;
import saf.components.AppWorkspaceComponent;
import saf.ui.AppGUI;
import regiovincomapeditor.PropertyType;
import regiovincomapeditor.data.DataManager;
import regiovincomapeditor.data.Subregions;
import static saf.settings.AppStartupConstants.FILE_PROTOCOL;
import static saf.settings.AppStartupConstants.PATH_IMAGES;
import javafx.scene.shape.Polyline;
import static javafx.scene.paint.Color.LIGHTGREEN;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;


/**
 *
 * @author li zhao
 */


public class Workspace extends AppWorkspaceComponent 
{
    

    AppTemplate app;
    AppGUI gui;
    int count = 0;

    RegioVincoMapEditorController reigoVincoMapEditorController;
    
    //THE ELEMENTS
    SplitPane splitpane = new SplitPane();
    FlowPane editToolbar = new FlowPane();
    HBox ButtonBar = new HBox(15);
    HBox ChooserBar = new HBox(15);
    HBox SliderBar = new HBox(15);
    StackPane itemBox = new StackPane();
    Pane mapBox = new Pane();
    
    //BUTTONS
    Button changeName;
    Button addImage;
    Button removeImage;
    Button reassignColor;
    Button changeDimensions;
    Button playAnthem;
    Button pauseAnthem;
    
    ColorPicker changeBGColor;
    ColorPicker changeBorderColor;
    
        
    Slider changeBorderThickness;
    Slider zoomLevel;
    
    //OTHER CONTROLS
    Image map;
    ImageView mapView = new ImageView();
    
    Image BGColor;
    ImageView BGColorView = new ImageView();
    
    Image BorderColor;
    ImageView BorderColorView = new ImageView();
    
    Image BorderThickness;
    ImageView BorderThicknessView = new ImageView();
    
    Image ZoomLevel;
    ImageView ZoomLevelView = new ImageView();
    

    TableView subregionTable = new TableView();
    TableColumn subregionColumn;
    TableColumn capitalColumn;
    TableColumn leaderColumn;
    
    
    public Workspace(AppTemplate initApp) throws IOException {
	// KEEP THIS FOR LATER
	app = initApp;
        
        gui = app.getGUI();
        
        
        layoutGUI();
                
        setupHandlers();

    }

    
    public void layoutGUI()
    {
        
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        DataManager dataManager = (DataManager)app.getDataComponent();
        double x = 0.0;
        double y = 0.0;
        
        //SETUP TOOLBAR
        changeName = gui.initChildButton(ButtonBar, PropertyType.Edit_Map_Name_ICON.toString(), PropertyType.Edit_Map_Name_TOOLTIP.toString(), false);
        addImage = gui.initChildButton(ButtonBar, PropertyType.ADD_IMAGE_ICON.toString(), PropertyType.ADD_IMAGE_TOOLTIP.toString(), false);
        removeImage = gui.initChildButton(ButtonBar, PropertyType.REMOVE_IMAGE_ICON.toString(), PropertyType.REMOVE_IMAGE_TOOLTIP.toString(), false);
        reassignColor = gui.initChildButton(ButtonBar, PropertyType.REASSIGN_COLOR_ICON.toString(), PropertyType.REASSIGN_COLOR_TOOLTIP.toString(), false);
        changeDimensions = gui.initChildButton(ButtonBar, PropertyType.CHANGE_DIMENSIONS_ICON.toString(), PropertyType.CHANGE_DIMENSIONS_TOOLTIP.toString(), false);
        playAnthem = gui.initChildButton(ButtonBar, PropertyType.PLAY_ANTHEM_ICON.toString(), PropertyType.PLAY_ANTHEM_TOOLTIP.toString(), false);
        pauseAnthem = gui.initChildButton(ButtonBar, PropertyType.PAUSE_ANTHEM_ICON.toString(), PropertyType.PAUSE_ANTHEM_TOOLTIP.toString(), false);
        
          
        //CHANGE BACKGROUNDCOLOR
        changeBGColor = new ColorPicker();
        Tooltip buttonTooltip = new Tooltip("Change Background Color");
        changeBGColor.setTooltip(buttonTooltip);
        
        String imagePath = FILE_PROTOCOL + PATH_IMAGES + "BGColor.png";
        BGColor = new Image(imagePath);
        BGColorView.setImage(BGColor);
        
        //CHANGE BORDER COLOR
        changeBorderColor = new ColorPicker();
        buttonTooltip = new Tooltip("Change Border Color");
        changeBorderColor.setTooltip(buttonTooltip);
        
        imagePath = FILE_PROTOCOL + PATH_IMAGES + "BorderColor.png";
        BorderColor = new Image(imagePath);
        BorderColorView.setImage(BorderColor);
        
        ChooserBar.getChildren().add(BGColorView);
        ChooserBar.getChildren().add(changeBGColor);
        ChooserBar.getChildren().add(BorderColorView);
        ChooserBar.getChildren().add(changeBorderColor);

        //SLIDER
        imagePath = FILE_PROTOCOL + PATH_IMAGES + "BorderThickness.png";
        BorderThickness = new Image(imagePath);
        BorderThicknessView.setImage(BorderThickness);
        changeBorderThickness = new Slider(0, 1, 0.5);
        buttonTooltip = new Tooltip("Change Border Thickness");
        changeBorderThickness.setTooltip(buttonTooltip);
        
        imagePath = FILE_PROTOCOL + PATH_IMAGES + "ZoomLevel.png";
        ZoomLevel = new Image(imagePath);
        ZoomLevelView.setImage(ZoomLevel);
        zoomLevel = new Slider(0, 1, 0.5);
        buttonTooltip = new Tooltip("Change Zoom Level");
        zoomLevel.setTooltip(buttonTooltip);
        
        
        SliderBar.getChildren().add(BorderThicknessView);
        SliderBar.getChildren().add(changeBorderThickness);
        SliderBar.getChildren().add(ZoomLevelView);
        SliderBar.getChildren().add(zoomLevel);
        
        //EDITTOOLBAR SET UP
        editToolbar.getChildren().add(ButtonBar);
        editToolbar.getChildren().add(ChooserBar);
        editToolbar.getChildren().add(SliderBar);
        editToolbar.setHgap(50);
        
        //SPLITPANE SET UP
        /*
        imagePath = FILE_PROTOCOL + PATH_IMAGES + "Map.png";
        map = new Image(imagePath);
        mapView.setImage(map);
        */

        
        //SET UP FOR TABLE
        subregionColumn = new TableColumn("Names");
        capitalColumn = new TableColumn("Capitals");
        leaderColumn = new TableColumn("Leaders");
        
        subregionColumn.setCellValueFactory(new PropertyValueFactory<String, String>("Name"));
        capitalColumn.setCellValueFactory(new PropertyValueFactory<String, String>("Capital"));
        leaderColumn.setCellValueFactory(new PropertyValueFactory<String, String>("Leader"));
        
        subregionColumn.prefWidthProperty().bind(subregionTable.widthProperty().divide(3));
        capitalColumn.prefWidthProperty().bind(subregionTable.widthProperty().divide(3));
        leaderColumn.prefWidthProperty().bind(subregionTable.widthProperty().divide(3));
        subregionTable.getColumns().add(subregionColumn);
        subregionTable.getColumns().add(capitalColumn);
        subregionTable.getColumns().add(leaderColumn);     
        subregionTable.setPrefHeight(1000);
  
        itemBox.getChildren().add(subregionTable);
        splitpane.getItems().addAll(mapBox,itemBox);
        

        //WORKSPACE SET UP
        workspace = new VBox();
        workspace.getChildren().add(editToolbar);
        workspace.getChildren().add(splitpane);

        //ADDING DUMMY DATAS
        //DataManager dataManager = (DataManager)app.getDataComponent();
        //DataManager dataManager = new DataManager();
        //subregionTable.setItems(dataManager.getItems());
        
    }

      
    @Override
    public void reloadWorkspace() 
    {
        mapBox.getChildren().clear();
        DataManager dataManager = (DataManager)app.getDataComponent();
        Pane pane = new Pane();
        mapBox.getChildren().add(pane);
        double x = 0.0;
        double y = 0.0;
        for(int c = 0; c < dataManager.getArrayX().size();c++)
        {
            x = ((reScaleX(180 + dataManager.getArrayX().get(c)) * gui.getPrimaryScene().getWidth())/2);
            y = ((reScaleY(90 - dataManager.getArrayY().get(c)) * gui.getPrimaryScene().getHeight())/2 + 200);
            dataManager.addcoordinateX(x);
            dataManager.addcoordinateY(y);
        }
        
        //System.out.println(dataManager.getSubregions().size());
        //System.out.println(dataManager.getSubregionsPolygon().size());
        //System.out.println("The size in reload :" + coordinatesX.size());


        for(int i = 0; i < dataManager.getSubregions().size(); i++)
        {
            Polyline polyline = new Polyline();
            int b =  (int) dataManager.getSubregionsPolygon().get(i);
            
            for(int j = 0; j < b; j++)
            {
                polyline.getPoints().addAll(dataManager.getcoordinateX().get(count),dataManager.getcoordinateY().get(count));
                count++;
            }
            polyline.setFill(LIGHTGREEN);
            polyline.setStrokeWidth(0.05);
            
            //mapBox.setMaxSize(802, 536);
            //polyline.bind(mapBox.widthProperty().divide(2));
            //polyline.bind(mapBox.heightProperty().divide(2));
            
            //mapBox.setScaleX(mapBox.getScaleX() * 2.0);
            //mapBox.setScaleY(mapBox.getScaleY() * 2.0);
            pane.getChildren().add(polyline);
            Rectangle clip = new Rectangle(gui.getPrimaryScene().getWidth()/2, gui.getPrimaryScene().getHeight()/2 + 200);
            mapBox.setStyle("-fx-background-color: #ff6600" );
            //mapBox.setClip(clip);
            mapBox.setOnMousePressed(e ->{
                if(e.getButton() == MouseButton.PRIMARY)
                {
                    //mapBox.setLayoutX(mapBox.getLayoutX() + (app.getGUI().getPrimaryScene().getWidth()/2-e.getSceneX())/1.1);
                    //mapBox.setLayoutY(mapBox.getLayoutY() + ((app.getGUI().getPrimaryScene().getHeight())/2-e.getSceneY())/1.1);
                    
                    //Scale scale = new Scale();
                    //scale.setPivotX(e.getSceneX());
                    //scale.setPivotY(e.getSceneY());
                    //pane.getTransforms().add(scale);
                    pane.setLayoutX(pane.getLayoutX() + (app.getGUI().getPrimaryScene().getWidth()/4-e.getSceneX())*1.1);
                    pane.setLayoutY(pane.getLayoutY() + ( ((app.getGUI().getPrimaryScene().getHeight())/4-e.getSceneY())+200)*1.1);
                    //pane.setLayoutX(pane.getLayoutX() + (app.getGUI().getPrimaryScene().getWidth()/2- (app.getGUI().getPrimaryScene().getWidth()/1.15/2)*1.1));
                    //pane.setLayoutY(pane.getLayoutY() + (app.getGUI().getPrimaryScene().getHeight()/2-(app.getGUI().getPrimaryScene().getHeight()/1.15/2)*1.1));
                    pane.setScaleX(pane.getScaleX() * 1.1);
                    pane.setScaleY(pane.getScaleY() * 1.1);

                }
            
        });
        count = 0;
    }
    }

    @Override
    public void initStyle() {
        
        editToolbar.getStyleClass().add("bordered_pane");
        
        
    }
        
     private void setupHandlers() 
     {
         reigoVincoMapEditorController = new RegioVincoMapEditorController(app);
         
         subregionTable.setOnMousePressed(e ->{
             
            int size = subregionTable.getItems().size(); 
            boolean rightPlace = e.getY() <= 25* (size+1) && e.getY() > 26; 
             
            if (e.getClickCount() == 1) 
            { 
                System.out.println("I click once");
            }
            else if (e.getClickCount() == 2 && rightPlace)  
            {
                
                reigoVincoMapEditorController.processEditSubregions();
            }
     });
         
     }
    
    public TableView<Subregions> getTable()
    {
        return subregionTable;
    }
    
    private double reScaleX(double x)
    {
        x = x/360;
        
        return x;
    }
    
    private double reScaleY(double y)
    {
        y = y/180;

        return y;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regiovincomapeditor.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
import javafx.stage.FileChooser;
import regiovincomapeditor.file.FileManager;
import static saf.settings.AppStartupConstants.PATH_WORK;


/**
 *
 * @author li zhao
 */


public class Workspace extends AppWorkspaceComponent 
{
    

    AppTemplate app;
    AppGUI gui;
    int count = 0;
    double moveX = 0.0;
    double moveY = 0.0;
    int red = 225;
    int green = 220;
    int blue = 215;
    RegioVincoMapEditorController reigoVincoMapEditorController;
    
    //THE ELEMENTS
    SplitPane splitpane = new SplitPane();
    FlowPane editToolbar = new FlowPane();
    HBox ButtonBar = new HBox(15);
    HBox ChooserBar = new HBox(15);
    HBox SliderBar = new HBox(15);
    StackPane itemBox = new StackPane();
    StackPane mapBox = new StackPane();
    
    FlowPane ImagePane = new FlowPane();
    
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
    
    Image NewImage;
    
    ImageView NewImageView = new ImageView();
    
    ArrayList<ImageView> ImageList = new ArrayList();
    
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
        changeBorderThickness = new Slider();
        changeBorderThickness.setMin(0.001);
        changeBorderThickness.setMax(0.01);
        changeBorderThickness.setValue(0.001);
        buttonTooltip = new Tooltip("Change Border Thickness");
        changeBorderThickness.setTooltip(buttonTooltip);
        
        imagePath = FILE_PROTOCOL + PATH_IMAGES + "ZoomLevel.png";
        ZoomLevel = new Image(imagePath);
        ZoomLevelView.setImage(ZoomLevel);
        zoomLevel = new Slider();
        zoomLevel.setMin(50);
        zoomLevel.setMax(1000);
        zoomLevel.setValue(1);
        
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
        subregionTable.setItems(dataManager.getItems());
        //System.out.println(dataManager.getItems().get(5).getCapital());
        subregionTable.setPrefHeight(1000);
        
        itemBox.getChildren().add(subregionTable);
        splitpane.getItems().addAll(mapBox,itemBox);
        mapBox.getChildren().add(ImagePane);

        //WORKSPACE SET UP
        workspace = new VBox();
        workspace.getChildren().add(editToolbar);
        workspace.getChildren().add(splitpane);
        

        
    }

      
    @Override
    public void reloadWorkspace() 
    {
        mapBox.getChildren().clear();
        
        Group group = new Group();
        FileManager fileManager = (FileManager)app.getFileComponent();
        DataManager dataManager = (DataManager)app.getDataComponent();
        double x = 0.0;
        double y = 0.0;

        for(int c = 0; c < dataManager.getArrayX().size();c++)
        {
            x = ((reScaleX(180 + dataManager.getArrayX().get(c)) * gui.getPrimaryScene().getWidth())/2);
            y = ((reScaleY(90 - dataManager.getArrayY().get(c)) * gui.getPrimaryScene().getHeight())-200);
            dataManager.addcoordinateX(x);
            dataManager.addcoordinateY(y);

            
        }

        boolean empty = dataManager.getItems().isEmpty();
        for(int i = 0; i < dataManager.getSubregions().size(); i++)
        {
            Polyline polyline = new Polyline();
            int b =  (int) dataManager.getSubregionsPolygon().get(i);
            
            for(int j = 0; j < b; j++)
            {
                polyline.getPoints().addAll(dataManager.getcoordinateX().get(count),dataManager.getcoordinateY().get(count));
                
                count++;
            }
            //polyline.setFill(LIGHTGREEN);
            polyline.setStrokeWidth(dataManager.getBorderThickness());
            group.getChildren().add(polyline);
            if(empty)
            {

                String subregionName = "subregion " + (i+1);
                String subregionCapital = "subregion_capital " + (i+1);
                String subregionLeader = "subregion_leader " + (i+1);
                Subregions subregion = new Subregions(subregionName, subregionCapital,subregionLeader, red,green,blue);
                Color color = Color.rgb(red,green,blue);
                polyline.setFill(color);
                red -= 5;
                green -= 5;
                blue -= 5;
                dataManager.addItem(subregion);
                
            }
            else
            {
                red = dataManager.getItems().get(i).getRed();
                green = dataManager.getItems().get(i).getGreen();
                blue = dataManager.getItems().get(i).getBlue();
                Color color = Color.rgb(red,green,blue);
                polyline.setFill(color);
                red -= 5;
                green -= 5;
                blue -= 5;
                
            }
            changeBorderThickness.valueProperty().addListener(new ChangeListener<Number>(){

                @Override
                public void changed(ObservableValue<? extends Number> ov, Number OldValue, Number NewValue) {
                    
                    for(Node node : group.getChildren())
                    {
                        Polyline p = (Polyline) node;
                        p.setStrokeWidth(NewValue.doubleValue());
                    }
                    //group.setScaleX(NewValue.intValue());
                    //group.setScaleY(NewValue.intValue());
                    dataManager.setBorderThickness(NewValue.doubleValue());
                }
            
            
            });
            zoomLevel.valueProperty().addListener(new ChangeListener<Number>(){

                @Override
                public void changed(ObservableValue<? extends Number> ov, Number OldValue, Number NewValue) {
                    /*
                    for(Node node : group.getChildren())
                    {
                        Polyline p = (Polyline) node;
                        p.setScaleX(group.getScaleX() * NewValue.intValue());
                        p.setScaleY(group.getScaleY() * NewValue.intValue());
                        System.out.println(NewValue.intValue());
                    }*/
                    group.setScaleX(NewValue.intValue());
                    group.setScaleY(NewValue.intValue());
                    dataManager.setZoomLevel(NewValue.intValue());
                }
            
            
            });
            //System.out.println(dataManager.getName());
            


        }//END OF FOR
        //System.out.println(dataManager.getParentDirectory() + "/" + dataManager.getName());
        
        try {
                fileManager.saveData(dataManager, dataManager.getParentDirectory() + "/" + dataManager.getName() + "/" + dataManager.getName());
                //fileManager.saveData(dataManager, "./work/1/2");
                //mapBox.getChildren().add(polyline);
                //polyline.setStrokeWidth(0.05);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Workspace.class.getName()).log(Level.SEVERE, null, ex);}
        group.setScaleX(group.getScaleX() * dataManager.getZoomLevel());
        group.setScaleY(group.getScaleY() * dataManager.getZoomLevel());
        mapBox.getChildren().add(group);
        mapBox.setStyle("-fx-background-color: " + dataManager.getBackGroundColor());
        count = 0;

    }

    @Override
    public void initStyle() {
        
        editToolbar.getStyleClass().add("bordered_pane");
        
        
    }
        
     private void setupHandlers() 
     {
         reigoVincoMapEditorController = new RegioVincoMapEditorController(app);
         
         DataManager dataManager = (DataManager)app.getDataComponent();
         
         changeName.setOnAction(e ->{
             try {
                 reigoVincoMapEditorController.HandleChangeNameRequest();
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(Workspace.class.getName()).log(Level.SEVERE, null, ex);
             }
         });
         
         addImage.setOnAction(e ->{
            FileChooser fc = new FileChooser();
            fc.setInitialDirectory(new File("./HW5SampleData/export/The World/Europe"));
            fc.setTitle("Add Image");
            File selectedFile = fc.showOpenDialog(app.getGUI().getWindow());
            Group ImageGroup = new Group();
            if(selectedFile != null)
            {
                String imagePath = "file:///" + selectedFile.getPath();
                NewImage = new Image(imagePath);
                NewImageView.setImage(NewImage);
                
                //ADD IMAGE TO ARRAYLIST
                ImageList.add(NewImageView);
                
                //SET UP HANDLER
                NewImageView.setOnMouseClicked(a -> {
                    
                    //NewImageView.setEffect(new DropShadow(50, Color.BLACK));
                    //NewImageView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0)");
                    NewImageView.setStyle("-fx-border-width: 10px");
                    NewImageView.setStyle("-fx-background-color: firebrick");
                    NewImageView.setStyle("-fx-background-radius: 5");
                });
                
                
                mapBox.getChildren().add(ImagePane);
                ImagePane.getChildren().add(NewImageView);
            }
         
         });
         
         subregionTable.setOnMousePressed(e ->{
             
            int size = subregionTable.getItems().size(); 
            boolean rightPlace = e.getY() <= 25* (size+1) && e.getY() > 26; 
             
            if (e.getClickCount() == 1) 
            { 
                //System.out.println("I click once");
                int index = dataManager.getItems().indexOf(subregionTable.getSelectionModel().getSelectedItem());
                //System.out.println(index);
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
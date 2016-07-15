package saf;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import static javafx.geometry.Pos.CENTER;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import properties_manager.PropertiesManager;
import saf.components.AppStyleArbiter;
import static saf.settings.AppPropertyType.PROPERTIES_LOAD_ERROR_MESSAGE;
import static saf.settings.AppPropertyType.PROPERTIES_LOAD_ERROR_TITLE;
import static saf.settings.AppStartupConstants.CLOSE_BUTTON_LABEL;
import static saf.settings.AppStartupConstants.FILE_PROTOCOL;
import static saf.settings.AppStartupConstants.OK_BUTTON_LABEL;
import static saf.settings.AppStartupConstants.PATH_DATA;
import static saf.settings.AppStartupConstants.PATH_IMAGES;
import static saf.settings.AppStartupConstants.PROPERTIES_SCHEMA_FILE_NAME;
import static saf.settings.AppStartupConstants.SIMPLE_APP_PROPERTIES_FILE_NAME;
import static saf.settings.AppStartupConstants.WORKSPACE_PROPERTIES_FILE_NAME;
import saf.ui.AppMessageDialogSingleton;
import xml_utilities.InvalidXMLFileFormatException;



public class EditSubregionDialog extends Stage implements AppStyleArbiter{
    // HERE'S THE SINGLETON
    static EditSubregionDialog singleton;
    
    // GUI CONTROLS FOR OUR DIALOG
    BorderPane borderpane = new BorderPane();
    HBox pictures = new HBox(20);
    GridPane gridpane = new GridPane();

    
    //GUI CONTROLS
    Button previous = new Button();
    Button next = new Button();
    Button okButton;
    Button cancelButton;
    TextField name = new TextField();
    TextField capital = new TextField();
    TextField leader = new TextField();
    
    Image flag;
    ImageView flagView = new ImageView();
    
    Image leaderPic;
    ImageView leaderPicView = new ImageView();
    
    Image left;
    
    Image right;
    
    
    String selection;
    
    
    static final String BUTTON = "button";
    public static final String OK = "Ok";
    public static final String CANCEL = "Cancel";
    Scene messageScene;

    private EditSubregionDialog() {}
    

    public static EditSubregionDialog getSingleton() {
	if (singleton == null)
	    singleton = new EditSubregionDialog();
	return singleton;
    }

    public void init(Stage primaryStage) {

        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);
        
        okButton = new Button(OK_BUTTON_LABEL);
        cancelButton = new Button(CLOSE_BUTTON_LABEL);
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        boolean success = loadProperties(SIMPLE_APP_PROPERTIES_FILE_NAME)&& loadProperties(WORKSPACE_PROPERTIES_FILE_NAME);  
	if (success) {
        
        //SET UP
        
        
        
        
        String imagePath = FILE_PROTOCOL + PATH_IMAGES + "DefaultLeader.png";
        flag = new Image(imagePath);
        flagView.setImage(flag);
        
        imagePath = FILE_PROTOCOL + PATH_IMAGES + "DefaultFlag.png";
        leaderPic = new Image(imagePath);
        leaderPicView.setImage(leaderPic);    
        
                
        imagePath = FILE_PROTOCOL + PATH_IMAGES + "left.png";
        left = new Image(imagePath);
        previous.setGraphic(new ImageView(left));
        
        imagePath = FILE_PROTOCOL + PATH_IMAGES + "right.png";
        right = new Image(imagePath);
        next.setGraphic(new ImageView(right));
        pictures.getChildren().add(flagView);
        pictures.getChildren().add(leaderPicView);
        
        gridpane.setHgap(10);
        gridpane.setVgap(10);
      
        
        gridpane.add(previous,0,2);
        gridpane.add(next,3,2);
        gridpane.add(new Label("Name:"),1,1);
        gridpane.add(name,2,1);
        gridpane.add(new Label("Capital:"),1,2);
        gridpane.add(capital,2,2);
        gridpane.add(new Label("Leader:"), 1,3);
        gridpane.add(leader,2,3);
        gridpane.add(pictures,2,0);
        
        gridpane.add(okButton,2,4);
        gridpane.add(cancelButton,2,4);
        gridpane.setHalignment(okButton, HPos.CENTER);
        gridpane.setHalignment(cancelButton, HPos.RIGHT);
        gridpane.setPadding(new Insets(40, 60, 40, 60));
        

        // AND PUT IT IN THE WINDOW
        messageScene = new Scene(gridpane);
        this.setScene(messageScene);
        

        }
    }


    public String getSelection() {
        return selection;
    }
    
    
    public void reset()
    {

    }
    public void show(String title) {
	// SET THE DIALOG TITLE BAR TITLE
	setTitle(title);
        showAndWait();
    }
    public boolean loadProperties(String propertiesFileName) {
	    PropertiesManager props = PropertiesManager.getPropertiesManager();
	try {
	    // LOAD THE SETTINGS FOR STARTING THE APP
	    props.addProperty(PropertiesManager.DATA_PATH_PROPERTY, PATH_DATA);
	    props.loadProperties(propertiesFileName, PROPERTIES_SCHEMA_FILE_NAME);
	    return true;
	} catch (InvalidXMLFileFormatException ixmlffe) {
	    // SOMETHING WENT WRONG INITIALIZING THE XML FILE
	    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
	    dialog.show(props.getProperty(PROPERTIES_LOAD_ERROR_TITLE), props.getProperty(PROPERTIES_LOAD_ERROR_MESSAGE));
	    return false;
	}
    }
    
    public void initStyle() {

    }

}
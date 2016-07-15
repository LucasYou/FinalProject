package saf;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.scene.text.Text;
import properties_manager.PropertiesManager;
import saf.components.AppStyleArbiter;
import static saf.settings.AppPropertyType.PROPERTIES_LOAD_ERROR_MESSAGE;
import static saf.settings.AppPropertyType.PROPERTIES_LOAD_ERROR_TITLE;
import static saf.settings.AppStartupConstants.CLOSE_BUTTON_LABEL;
import static saf.settings.AppStartupConstants.OK_BUTTON_LABEL;
import static saf.settings.AppStartupConstants.PATH_DATA;
import static saf.settings.AppStartupConstants.PROPERTIES_SCHEMA_FILE_NAME;
import static saf.settings.AppStartupConstants.SIMPLE_APP_PROPERTIES_FILE_NAME;
import static saf.settings.AppStartupConstants.WORKSPACE_PROPERTIES_FILE_NAME;
import saf.ui.AppMessageDialogSingleton;
import xml_utilities.InvalidXMLFileFormatException;



public class NewMapDialog extends Stage implements AppStyleArbiter{
    // HERE'S THE SINGLETON
    static NewMapDialog singleton;
    
    // GUI CONTROLS FOR OUR DIALOG
    GridPane gridpane = new GridPane();
    TextField name = new TextField();
    Text dataText = new Text("some default text");
    Text directoryText = new Text("some default text");
    Button directoryChooser;
    Button dataChooser;
    Button okButton;
    Button cancelButton;
    String selection;

    static final String BUTTON = "button";
    public static final String OK = "Ok";
    public static final String CANCEL = "Cancel";
    Scene messageScene;

    private NewMapDialog() {}
    

    public static NewMapDialog getSingleton() {
	if (singleton == null)
	    singleton = new NewMapDialog();
	return singleton;
    }

    public void init(Stage primaryStage) {
        // MAKE THIS DIALOG MODAL, MEANING OTHERS WILL WAIT
        // FOR IT WHEN IT IS DISPLAYED
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);
        okButton = new Button(OK_BUTTON_LABEL);
        cancelButton = new Button(CLOSE_BUTTON_LABEL);
        cancelButton.setOnAction(e->{ NewMapDialog.this.close(); });
        dataChooser = new Button("Open");
        directoryChooser = new Button("Select Path");
        
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        boolean success = loadProperties(SIMPLE_APP_PROPERTIES_FILE_NAME)&& loadProperties(WORKSPACE_PROPERTIES_FILE_NAME);  
	if (success) {
        

        gridpane.setHgap(10);
        gridpane.setVgap(10);
        gridpane.add(new Label("Name:"),0,0);
        gridpane.add(name,1,0);
        gridpane.add(new Label("Directory:"),0,2);
        gridpane.add(directoryChooser,2,3);
        gridpane.add(directoryText,1,2);
        
        gridpane.add(new Label("Data:"),0,4);
        gridpane.add(dataText,1,4);
        gridpane.add(dataChooser,2,5);

        
        gridpane.add(okButton,1,7);
        gridpane.add(cancelButton,1,7);
        gridpane.setHalignment(okButton, HPos.CENTER);
        gridpane.setHalignment(cancelButton, HPos.RIGHT);
        gridpane.setPadding(new Insets(40, 60, 40, 60));
        
        okButton.setOnAction(e -> {
            
        });
        
        dataChooser.setOnAction(e ->{
        
        });
        
        directoryChooser.setOnAction(e ->{
        
        });
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
    
    public void initStyle() 
    {

    }

}
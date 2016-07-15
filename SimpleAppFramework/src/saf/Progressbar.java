package saf;

import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import properties_manager.PropertiesManager;
import saf.components.AppStyleArbiter;
import static saf.settings.AppPropertyType.PROPERTIES_LOAD_ERROR_MESSAGE;
import static saf.settings.AppPropertyType.PROPERTIES_LOAD_ERROR_TITLE;
import static saf.settings.AppStartupConstants.PATH_DATA;
import static saf.settings.AppStartupConstants.PROPERTIES_SCHEMA_FILE_NAME;
import static saf.settings.AppStartupConstants.SIMPLE_APP_PROPERTIES_FILE_NAME;
import static saf.settings.AppStartupConstants.WORKSPACE_PROPERTIES_FILE_NAME;
import saf.ui.AppMessageDialogSingleton;
import xml_utilities.InvalidXMLFileFormatException;



public class Progressbar extends Stage implements AppStyleArbiter{
    // HERE'S THE SINGLETON
    static Progressbar singleton;
    
    // GUI CONTROLS FOR OUR DIALOG
    HBox toolbar = new HBox();
    ProgressBar bar = new ProgressBar(0);
    ProgressIndicator indicator = new ProgressIndicator(0);
    Scene messageScene;

    private Progressbar() {}
    

    public static Progressbar getSingleton() {
	if (singleton == null)
	    singleton = new Progressbar();
	return singleton;
    }

    public void init(Stage primaryStage) {
        // MAKE THIS DIALOG MODAL, MEANING OTHERS WILL WAIT
        // FOR IT WHEN IT IS DISPLAYED
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);
        toolbar.getChildren().add(bar);
        bar.setProgress(0);
        
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        boolean success = loadProperties(SIMPLE_APP_PROPERTIES_FILE_NAME)&& loadProperties(WORKSPACE_PROPERTIES_FILE_NAME);  
	if (success) {
        

        messageScene = new Scene(toolbar);
        this.setScene(messageScene);
        

        }
    }




    public void show(String title, int max) {
	// SET THE DIALOG TITLE BAR TITLE
	setTitle(title);
        for(int i = 0; i < max; i++)
        {
            bar.setProgress(i/max);
        }
        
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
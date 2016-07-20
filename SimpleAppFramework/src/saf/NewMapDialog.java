package saf;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import properties_manager.PropertiesManager;
import saf.components.AppStyleArbiter;
import saf.controller.AppFileController;
import static saf.settings.AppPropertyType.LOAD_WORK_TITLE;
import static saf.settings.AppPropertyType.PROPERTIES_LOAD_ERROR_MESSAGE;
import static saf.settings.AppPropertyType.PROPERTIES_LOAD_ERROR_TITLE;
import static saf.settings.AppStartupConstants.CLOSE_BUTTON_LABEL;
import static saf.settings.AppStartupConstants.OK_BUTTON_LABEL;
import static saf.settings.AppStartupConstants.PATH_DATA;
import static saf.settings.AppStartupConstants.PATH_WORK;
import static saf.settings.AppStartupConstants.PROPERTIES_SCHEMA_FILE_NAME;
import static saf.settings.AppStartupConstants.SIMPLE_APP_PROPERTIES_FILE_NAME;
import static saf.settings.AppStartupConstants.WORKSPACE_PROPERTIES_FILE_NAME;
import saf.ui.AppMessageDialogSingleton;
import xml_utilities.InvalidXMLFileFormatException;



public class NewMapDialog extends Stage implements AppStyleArbiter{
    // HERE'S THE SINGLETON
    static NewMapDialog singleton;
    static File selectedFile;
    // GUI CONTROLS FOR OUR DIALOG
    GridPane gridpane = new GridPane();
    
    public static String FileName;
    
    TextField name = new TextField();
    Text dataText = new Text("some default text");
    Text directoryText = new Text("some default text");
    Button directoryChooser = new Button("Select Path");
    Button dataChooser = new Button("Select Data");;
    Button okButton = new Button(OK_BUTTON_LABEL);
    Button cancelButton = new Button(CLOSE_BUTTON_LABEL);
    String selection = "";
    static String directorypath = "";
    static String datapath = "";
    static String currentpath = "";
    public static File file;
   
    public static final String OK = "Ok";
    public static final String CANCEL = "Cancel";
    Scene messageScene;
    Window window;
    
    public NewMapDialog()
    {
            TextField name = new TextField();
            Text dataText = new Text("some default text");
            Text directoryText = new Text("some default text");
            Button directoryChooser = new Button("Select Path");
            Button dataChooser = new Button("Select Data");;
            Button okButton = new Button(OK_BUTTON_LABEL);
            Button cancelButton = new Button(CLOSE_BUTTON_LABEL);
            String selection = "";
            String path = "";
            file = null;
        
    }

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
        
        cancelButton.setOnAction(e->{ NewMapDialog.this.close(); });
        
        EventHandler yesNoCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button)ae.getSource();
            NewMapDialog.this.selection = sourceButton.getText();
            NewMapDialog.this.hide();
        };
        
        okButton.setOnAction(yesNoCancelHandler);
        
        
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        boolean success = loadProperties(SIMPLE_APP_PROPERTIES_FILE_NAME)&& loadProperties(WORKSPACE_PROPERTIES_FILE_NAME);  
	if (success) {
        
        directoryText.setWrappingWidth(350);    
        dataText.setWrappingWidth(350); 
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        gridpane.add(new Label("Name:"),0,0);
        gridpane.add(name,1,0);
        gridpane.add(new Label("Directory:"),0,1);
        gridpane.add(directoryText,1,1);
        gridpane.add(directoryChooser,2,1);
        
        
        gridpane.add(new Label("Data:"),0,2);
        gridpane.add(dataText,1,2);
        gridpane.add(dataChooser,2,2);

        
        gridpane.add(okButton,1,3);
        gridpane.add(cancelButton,2,3);
        gridpane.setHalignment(okButton, HPos.RIGHT);
        gridpane.setHalignment(cancelButton, HPos.RIGHT);
        gridpane.setPadding(new Insets(40, 60, 40, 60));
        

        
        directoryChooser.setOnAction(e ->{
            DirectoryChooser dc = new DirectoryChooser();
            dc.setInitialDirectory(new File(PATH_WORK));
            dc.setTitle("Choose Directory");
            selectedFile = dc.showDialog(window);
            if (selectedFile != null)
            {
                directorypath = selectedFile.getPath();
                directoryText.setText(selectedFile.getPath());
                
            }

        });
        
        dataChooser.setOnAction(e ->{
            FileChooser fc = new FileChooser();
            fc.setInitialDirectory(new File(PATH_WORK));
            fc.setTitle("Choose data");
            selectedFile = fc.showOpenDialog(window);
            if (selectedFile != null)
            {
                datapath = selectedFile.getPath();
                dataText.setText(selectedFile.getPath());
            }
            //AFTER SELECT, APPEND THE TEXT TO SELECTED DATA
                
            
        });
        
        /*
        okButton.setOnAction(e -> {

              file = new File(path  + "/" +  name.getText());
              if(file.exists() == true)
              {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("WARNING");
                    alert.setHeaderText(null);
                    alert.setContentText("Directory already exists");
                    alert.showAndWait();
              }
              else
              {
                  
              file.mkdir();
              Name = name.getText();
              NewMapDialog.this.reset();
              NewMapDialog.this.close();
              
              }
        });
        
        */
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
        name.setText(null);
        directoryText.setText("some default text");
        dataText.setText("some default text");

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
    
    public File getFile()
    {
        return selectedFile;
    }
    
    public void setFile(File file)
    {
        selectedFile = file;
    }
    
    public TextField getName()
    {
        return name;
    }
    
    public String getDirectoryPath()
    {
        return directorypath;
    }   
    public void setDirectoryPath(File file)
    {
        directorypath = file.getPath();
    }
    
    
    public String getDataPath()
    {
        return datapath;
    }
    public String getFileName()
    {
        return FileName;
    }
    public void setFileName(String filename)
    {
        FileName = filename;
    }
    
    public Text getData()
    {
        return dataText;
    }
    
    public String getCurrentPath()
    {
        return currentpath;
    }   
    public void setCurrentPath(File file)
    {
        currentpath = file.getPath();
    }
}
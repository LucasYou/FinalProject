/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regiovincomapeditor.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.Optional;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import regiovincomapeditor.data.DataManager;
import regiovincomapeditor.file.FileManager;
import regiovincomapeditor.data.Subregions;
import saf.AppTemplate;

import regiovincomapeditor.gui.Workspace;
import saf.EditSubregionDialog;
import saf.NewMapDialog;
import static saf.settings.AppPropertyType.LOAD_WORK_TITLE;
import static saf.settings.AppStartupConstants.PATH_WORK;

/**
 *
 * @author li zhao
 */
public class RegioVincoMapEditorController 
{
    AppTemplate app;
    
    Image NewImage;
    
    ImageView NewImageView = new ImageView();
    
    
    public RegioVincoMapEditorController(AppTemplate initApp) {
	app = initApp;
    }
    
    
    public void HandleChangeNameRequest() throws FileNotFoundException
    {
        DataManager dataManager = (DataManager)app.getDataComponent();
        FileManager fileManager = (FileManager)app.getFileComponent();
        NewMapDialog newmapdialog = new NewMapDialog();
        TextInputDialog dialog = new TextInputDialog(newmapdialog.getFileName());
        dialog.setTitle("Change Map Name");
        dialog.setContentText("New Map Name: ");
        dialog.setHeaderText(null);
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent())
        {
            File olddatafile = new File(newmapdialog.getCurrentPath() + "/" + dataManager.getName());
            dataManager.setName(result.get());
            File newfile = new File(newmapdialog.getDirectoryPath() + "/" + dataManager.getName());
            File oldfile = new File(newmapdialog.getCurrentPath());
            olddatafile.delete();
            newmapdialog.setFile(oldfile);
            newmapdialog.getFile().renameTo(newfile);
            
            
            newmapdialog.setCurrentPath(newfile);
            String datapath = newmapdialog.getCurrentPath() +  "/" + result.get();
            //System.out.println(datapath);
            //System.out.println(dataManager.getItems().get(0));
            fileManager.saveData(dataManager, datapath);
            newmapdialog.setFileName(result.get());
 
        }

        //System.out.println(dataManager.getName());
        //dataManager.reset();
        //fileManager.saveData(dataManager, newmapdialog.getDirectoryPath() + "/" + newmapdialog.getFileName());
        
    }//end of method
    
    public void processAddImage()
    {

            
        
    }
    
    public void processEditSubregions()
    {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        DataManager dataManager = (DataManager)app.getDataComponent();
        Subregions currentSubregions = workspace.getTable().getSelectionModel().getSelectedItem();
        int CurrentIndex = dataManager.getItems().indexOf(workspace.getTable().getSelectionModel().getSelectedItem());

            
        //System.out.println(currentSubregions.getLeader());
        
        EditSubregionDialog editSubregionDialog = EditSubregionDialog.getSingleton();
        
        editSubregionDialog.name.setText(workspace.getTable().getSelectionModel().getSelectedItem().getName());
        editSubregionDialog.capital.setText(workspace.getTable().getSelectionModel().getSelectedItem().getCapital());
        editSubregionDialog.leader.setText(workspace.getTable().getSelectionModel().getSelectedItem().getLeader());
        //System.out.println(workspace.getTable().getSelectionModel().getSelectedItem().getCapital());
        //System.out.println(editSubregionDialog.capital.getText());
        //
        String FlagPath;
        FlagPath = dataManager.getParentDirectory() + "/" + dataManager.getName() + "/" + workspace.getTable().getSelectionModel().getSelectedItem().getName() + " Flag.png";
        File FlagFile = new File(FlagPath);
        if(!FlagFile.exists())
            FlagPath = "file:///" + dataManager.getParentDirectory() + "/" + dataManager.getName() + "/"  + "DefaultFlag.png";
        else
            FlagPath = "file:///" + dataManager.getParentDirectory() + "/" + dataManager.getName() + "/" + workspace.getTable().getSelectionModel().getSelectedItem().getName() + " Flag.png";
        
        String LeaderPath;
        LeaderPath = dataManager.getParentDirectory() + "/" + dataManager.getName() + "/" + workspace.getTable().getSelectionModel().getSelectedItem().getLeader() + ".png";
        File LeaderFile = new File(LeaderPath);
        if(!LeaderFile.exists())
            LeaderPath = "file:///" + dataManager.getParentDirectory() + "/" + dataManager.getName() + "/"  + "DefaultLeader.png";
        else
            LeaderPath = "file:///" + dataManager.getParentDirectory() + "/" + dataManager.getName() + "/" + workspace.getTable().getSelectionModel().getSelectedItem().getLeader() + ".png";
        //System.out.println(FlagPath);
        //System.out.println(LeaderPath);
        
        editSubregionDialog.flag = new Image(FlagPath);
        editSubregionDialog.flagView.setImage(editSubregionDialog.flag);
        editSubregionDialog.flagView.setFitWidth(200);
        editSubregionDialog.flagView.setFitHeight(150);
        
        
        editSubregionDialog.leaderPic = new Image(LeaderPath);
        editSubregionDialog.leaderPicView.setImage(editSubregionDialog.leaderPic);
        editSubregionDialog.leaderPicView.setFitWidth(200);
        editSubregionDialog.leaderPicView.setFitHeight(150);
        
        
        //System.out.println(index);
        
        //Next subregion
        
        if(CurrentIndex == 6)
            editSubregionDialog.next.setDisable(true);
        else
            editSubregionDialog.next.setDisable(false);
        
        if(CurrentIndex == 0)
            editSubregionDialog.previous.setDisable(true);
        else
            editSubregionDialog.previous.setDisable(false);
        
        //NEXT BUTTON
        editSubregionDialog.next.setOnMouseClicked(e ->{
            
            int index = dataManager.getItems().indexOf(workspace.getTable().getSelectionModel().getSelectedItem());
            System.out.println(index);
            //editSubregionDialog.next.setDisable(false);
            if(index == 5)
                editSubregionDialog.next.setDisable(true);
            else
                editSubregionDialog.next.setDisable(false);
            
            if(index >=0)
                editSubregionDialog.previous.setDisable(false);
            else
                editSubregionDialog.previous.setDisable(true);
            
            if(index >= 0 && index < dataManager.getArrayX().size())
            {
                Subregions next = dataManager.getItems().get(index + 1);
                editSubregionDialog.name.setText(next.getName());
                editSubregionDialog.capital.setText(next.getCapital());
                editSubregionDialog.leader.setText(next.getLeader());
                workspace.getTable().getSelectionModel().select(index+1);
                String NewFlagPath = dataManager.getParentDirectory() + "/" + dataManager.getName() + "/" + workspace.getTable().getSelectionModel().getSelectedItem().getName() + " Flag.png";
                String NewLeaderPath = dataManager.getParentDirectory() + "/" + dataManager.getName() + "/" + workspace.getTable().getSelectionModel().getSelectedItem().getLeader() + ".png";
                File NewFlagFile = new File(NewFlagPath);
                if(!NewFlagFile.exists())
                    NewFlagPath = "file:///" + dataManager.getParentDirectory() + "/" + dataManager.getName() + "/"  + "DefaultFlag.png";
                else
                    NewFlagPath = "file:///" + dataManager.getParentDirectory() + "/" + dataManager.getName() + "/" + workspace.getTable().getSelectionModel().getSelectedItem().getName() + " Flag.png";
                
                
                File NewLeaderFile = new File(NewLeaderPath);
                if(!NewLeaderFile.exists())
                    NewLeaderPath = "file:///" + dataManager.getParentDirectory() + "/" + dataManager.getName() + "/"  + "DefaultLeader.png";
                else
                    NewLeaderPath = "file:///" + dataManager.getParentDirectory() + "/" + dataManager.getName() + "/" + workspace.getTable().getSelectionModel().getSelectedItem().getLeader() + ".png";
                
                editSubregionDialog.flag = new Image(NewFlagPath);
                editSubregionDialog.flagView.setImage(editSubregionDialog.flag);
                editSubregionDialog.leaderPic = new Image(NewLeaderPath);
                editSubregionDialog.leaderPicView.setImage(editSubregionDialog.leaderPic);
            }
            
        });
        
        //PREVIOUS BUTTON
                editSubregionDialog.previous.setOnMouseClicked(e ->{
            
            int index = dataManager.getItems().indexOf(workspace.getTable().getSelectionModel().getSelectedItem());
                        //System.out.println(index);
            //editSubregionDialog.next.setDisable(false);
            if(index == 1)
                editSubregionDialog.previous.setDisable(true);
            else
                editSubregionDialog.previous.setDisable(false);

            
            if(index == dataManager.getItems().size())
                editSubregionDialog.next.setDisable(true);
            else
                editSubregionDialog.next.setDisable(false);
            
            if(index >= 0 && index < dataManager.getArrayX().size())
            {
                Subregions next = dataManager.getItems().get(index - 1);
                editSubregionDialog.name.setText(next.getName());
                editSubregionDialog.capital.setText(next.getCapital());
                editSubregionDialog.leader.setText(next.getLeader());
                workspace.getTable().getSelectionModel().select(index-1);
                String NewFlagPath = dataManager.getParentDirectory() + "/" + dataManager.getName() + "/" + workspace.getTable().getSelectionModel().getSelectedItem().getName() + " Flag.png";
                String NewLeaderPath = dataManager.getParentDirectory() + "/" + dataManager.getName() + "/" + workspace.getTable().getSelectionModel().getSelectedItem().getLeader() + ".png";
                File NewFlagFile = new File(NewFlagPath);
                if(!NewFlagFile.exists())
                    NewFlagPath = "file:///" + dataManager.getParentDirectory() + "/" + dataManager.getName() + "/"  + "DefaultFlag.png";
                else
                    NewFlagPath = "file:///" + dataManager.getParentDirectory() + "/" + dataManager.getName() + "/" + workspace.getTable().getSelectionModel().getSelectedItem().getName() + " Flag.png";
                
                
                File NewLeaderFile = new File(NewLeaderPath);
                if(!NewLeaderFile.exists())
                    NewLeaderPath = "file:///" + dataManager.getParentDirectory() + "/" + dataManager.getName() + "/"  + "DefaultLeader.png";
                else
                    NewLeaderPath = "file:///" + dataManager.getParentDirectory() + "/" + dataManager.getName() + "/" + workspace.getTable().getSelectionModel().getSelectedItem().getLeader() + ".png";
                
                editSubregionDialog.flag = new Image(NewFlagPath);
                editSubregionDialog.flagView.setImage(editSubregionDialog.flag);
                editSubregionDialog.leaderPic = new Image(NewLeaderPath);
                editSubregionDialog.leaderPicView.setImage(editSubregionDialog.leaderPic);
            }
            
        });
        
        
        
        
        editSubregionDialog.show("Edit Subregion Dialog");
        

        
        
        
        String selection = editSubregionDialog.getSelection();
        //System.out.println(selection);
        //System.out.println(editSubregionDialog.CANCEL);
        if (selection.equals(editSubregionDialog.OK))
        {
            currentSubregions.setName(editSubregionDialog.name.getText());
            currentSubregions.setCapital(editSubregionDialog.capital.getText());
            currentSubregions.setLeader(editSubregionDialog.leader.getText());

        }
        else
        {
            editSubregionDialog.close();
        }
        for(int i = 0; i < 7; i++)
        {
            //System.out.println(dataManager.getItems().get(i).getCapital());
        }
        

        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regiovincomapeditor.controller;

import regiovincomapeditor.data.DataManager;
import regiovincomapeditor.data.Subregions;
import saf.AppTemplate;

import regiovincomapeditor.gui.Workspace;
import saf.EditSubregionDialog;

/**
 *
 * @author li zhao
 */
public class RegioVincoMapEditorController 
{
    AppTemplate app;
    
    
    public RegioVincoMapEditorController(AppTemplate initApp) {
	app = initApp;
    }
    
    public void processEditSubregions()
    {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        DataManager dataManager = (DataManager)app.getDataComponent();
        Subregions currentSubregions = workspace.getTable().getSelectionModel().getSelectedItem();
        
        //System.out.println(currentSubregions.getLeader());
        
        EditSubregionDialog editSubregionDialog = EditSubregionDialog.getSingleton();
        editSubregionDialog.show("Edit Subregion Dialog");
    }
}

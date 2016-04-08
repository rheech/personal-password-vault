/***************************************************************************
* Package: com.example.passwordvault
* Name: ActRecordDisplay
* Developer: Team Programmers Impossible
* Purpose: Display records
*****************************************************************************/

/*****************************************************************************
 * MAINTANENCE LOG                                                           *
 *****************************************************************************
 * DATE:       *  Entry               *     Description                      *
 *****************************************************************************
 * 11/03/2012    C.Lee                      Writes the file version 1.0
 * 12/03/2012    C.Lee                      Writes file version     1.1
 * 12/04/2012    E.Barrueto                 Documentation Work
 * 12/06/2012    C.Lee                      Review Document and Source
 * 12/07/2012    Group members              Final Review
 ***************************************************************************/

package com.example.passwordvault;

import vault.LoginItem;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/********************************************************************
* Name: ActRecordDisplay extends EnhancedActivity implements OnClickListener
* *******************************************************************
* Purpose: An activity to display password records
* Members: 
*          public void onCreate(Bundle savedInstanceState)
*          public void onPostCreate(Bundle savedInstanceState)
*          public void onClick(View v)
*          public void onDialogResult(int caller, DIALOG_RESULT result)
*          public void finish()
*          public void finish(boolean showListDialog)
*          
* Reuse Instructions/Limitations: none as 12/2012
*********************************************************************/
public class ActRecordDisplay extends EnhancedActivity implements OnClickListener
{
	// controls in activity
	private EditText txtTitle, txtLogin, txtPassword, txtLoginURL;
	// id value to identify the record
	private long id;
	
	/******************************************************
	* Name: public void onCreate(Bundle savedInstanceState)
	* *****************************************************
	* Purpose: Initialize components and get parameter for
	*          recognize record id.
	* ******************************************************/
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_display);
        
        // get id from parameter
        Intent intent = getIntent();
		String strID = intent.getExtras().get("ID").toString();
		
		// if parameter is numeric, get duration value
		if (strID.matches("^[0-9]+$"))
			id = Long.parseLong(strID);
		else
			id = -1;
    }
    
	/******************************************************
	* Name: public void onPostCreate(Bundle savedInstanceState)
	* *****************************************************
	* Purpose: Set all control variables & register events
	*          after creation
	* ******************************************************/
    @Override
    public void onPostCreate(Bundle savedInstanceState)
    {
    	super.onPostCreate(savedInstanceState);
    	
    	// get all text boxes
    	txtTitle = (EditText)findViewById(R.id.recordDisplay_txtTitle);
    	txtLogin = (EditText)findViewById(R.id.recordDisplay_txtLogin);
    	txtPassword = (EditText)findViewById(R.id.recordDisplay_txtPassword);
    	txtLoginURL = (EditText)findViewById(R.id.recordDisplay_txtLoginURL);
    	
    	// read only property
    	txtTitle.setFocusable(false);
    	txtLogin.setFocusable(false);
    	txtPassword.setFocusable(false);
    	txtLoginURL.setFocusable(false);
    	
    	// print all data on textbox
    	LoginItem item = App.vm.list().get((int)id);
    	txtTitle.setText(item.getTitle());
    	txtLogin.setText(item.getLoginID());
    	txtPassword.setText(item.getLoginPW());
    	txtLoginURL.setText(item.getURL());
    	
    	// register all click listeners
    	Button btn = (Button)findViewById(R.id.recordDisplay_btnEdit);
    	btn.setOnClickListener(this);
    	
    	btn = (Button)findViewById(R.id.recordDisplay_btnDelete);
    	btn.setOnClickListener(this);
    	
    	btn = (Button)findViewById(R.id.recordDisplay_btnClose);
    	btn.setOnClickListener(this);
    }

	/******************************************************
	* Name: public void onClick(View v)
	* *****************************************************
	* Purpose: Handles button onclick event.
	* ******************************************************/
	public void onClick(View v)
	{
		int callerID = v.getId();
		
		// close button
		if (callerID == R.id.recordDisplay_btnClose)
		{
			App.ui.showDlgRecordList();
			finish();			
		}
		else if (callerID == R.id.recordDisplay_btnEdit)
		{ // edit button
			App.ui.showDlgModifyRecord(id);
			finish(false);
		}
		else if (callerID == R.id.recordDisplay_btnDelete)
		{ // delete button
			messageBox(callerID, "Password Vault",
					"Are you sure you want to delete this item?",
					DIALOG_TYPE.YESNO);
		}
	}
	
	/******************************************************
	* Name: public void onDialogResult(int caller, DIALOG_RESULT result)
	* *****************************************************
	* Purpose: Method for handling onClick event after dialog is clicked
	* ******************************************************/
	@Override
	public void onDialogResult(int caller, DIALOG_RESULT result)
	{
		// delete confirm
		if (caller == R.id.recordDisplay_btnDelete &&
				result == DIALOG_RESULT.RESULT_POSITIVE)
		{
			App.vm.list().remove((int)id);
			App.vm.saveData();
			finish();
		}
	}
	
	
	/******************************************************
	* Name: public void finish()
	* *****************************************************
	* Purpose: Overridden method to finish with showListDialog option
	* ******************************************************/
	@Override
	public void finish()
	{
		finish(true);
	}
	
	/******************************************************
	* Name: public void finish(boolean showListDialog)
	* *****************************************************
	* Purpose: Internal method of finish() to finish with showListDialog option 
	* ******************************************************/
	public void finish(boolean showListDialog)
	{
		if (showListDialog)
			App.ui.showDlgRecordList();
		
		super.finish();
	}
}

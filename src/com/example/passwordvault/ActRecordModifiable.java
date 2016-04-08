/***************************************************************************
* Package: com.example.passwordvault
* Name: ActRecordModifiable
* Developer: Team Programmers Impossible
* Purpose: New/Modify record form
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
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;

/********************************************************************
* Name: ActRecordModifiable extends EnhancedActivity
				implements OnClickListener, OnKeyListener
* *******************************************************************
* Purpose: An activity to display password records
* Members: 
*          public void onCreate(Bundle savedInstanceState)
*          public void onPostCreate(Bundle savedInstanceState)
*          private LoginItem readAllComponents()
*          private void addData()
*          private void modifyData()
*          public boolean onKey(View v, int keyCode, KeyEvent event)
*          public void onClick(View v)
*          public void finish()
* Reuse Instructions/Limitations: none as 12/2012
*********************************************************************/
public class ActRecordModifiable extends EnhancedActivity
				implements OnClickListener, OnKeyListener
{
	// id, accessmode(create/modify), and controls
	private long id;
	private int intAccessMode;
	private EditText txtTitle, txtLoginID, txtLoginPW, txtLoginURL;
	private Button btnOK;
	
	// constants to identify access mode
	private final int ACCESS_NEW = 0;
	private final int ACCESS_MODIFY = 1;
	
	/******************************************************
	* Name: onCreate(Bundle savedInstanceState)
	* *****************************************************
	* Purpose: Initialize components and get ID parameter
	*          from Intent to specify record
	* ******************************************************/
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_modifiable);
        
        // get ID of the record from parameter (modify mode only)
        intAccessMode = 0;
        Intent intent = getIntent();
		String strID = intent.getExtras().get("ID").toString();
		
		// if parameter is numeric, get duration value
		if (strID.matches("^[0-9]+$"))
			id = Long.parseLong(strID);
		else
			id = -1;
        
		// if id = -1, identify as creating a new record.
		// else, it is modify mode
        if (id == -1)
        	intAccessMode = ACCESS_NEW;
        else
        	intAccessMode = ACCESS_MODIFY;
    }
    
	/******************************************************
	* Name: onPostCreate(Bundle savedInstanceState)
	* *****************************************************
	* Purpose: Set all controls to member variables and 
	*          register all components
	* ******************************************************/
    @Override
    public void onPostCreate(Bundle savedInstanceState)
    {
    	super.onPostCreate(savedInstanceState);
    	
    	// register all button click event
    	Button btn = (Button)findViewById(R.id.recordModifiable_btnOK);
    	btn.setOnClickListener(this);
    	
    	btn = (Button)findViewById(R.id.recordModifiable_btnCancel);
    	btn.setOnClickListener(this);
    	
    	// get all textboxes
    	txtTitle = (EditText)findViewById(R.id.recordModifiable_txtTitle);
    	txtLoginID = (EditText)findViewById(R.id.recordModifiable_txtLogin);
    	txtLoginPW = (EditText)findViewById(R.id.recordModifiable_txtPassword);
    	txtLoginURL = (EditText)findViewById(R.id.recordModifiable_txtLoginURL);
    	
    	// get ok button
    	btnOK = (Button)findViewById(R.id.recordModifiable_btnOK);
    	
    	txtTitle.setOnKeyListener(this);
    	txtLoginID.setOnKeyListener(this);
    	txtLoginPW.setOnKeyListener(this);
    	txtLoginURL.setOnKeyListener(this);
    	
    	
    	// on modify mode, get all record and print to textboxes
    	if (intAccessMode == ACCESS_MODIFY)
    	{
    		LoginItem item = App.vm.list().get((int)id);
    		txtTitle.setText(item.getTitle());
    		txtLoginID.setText(item.getLoginID());
    		txtLoginPW.setText(item.getLoginPW());
    		txtLoginURL.setText(item.getURL());
    	}
    }
    
	/******************************************************
	* Name: LoginItem readAllComponents()
	* *****************************************************
	* Purpose: read all user input and return as a record
	* ******************************************************/
    private LoginItem readAllComponents()
    {
    	LoginItem rtnItem = new LoginItem();
    	rtnItem.setTitle(txtTitle.getText().toString());
    	rtnItem.setLoginID(txtLoginID.getText().toString());
    	rtnItem.setLoginPW(txtLoginPW.getText().toString());
    	rtnItem.setURL(txtLoginURL.getText().toString());
    	
		return rtnItem;
    }
    
	/******************************************************
	* Name: void addData()
	* *****************************************************
	* Purpose: Add records to PPV
	* ******************************************************/
    private void addData()
    {
    	LoginItem loginItem = readAllComponents();
		App.vm.list().add(loginItem);
		App.vm.saveData();
    }
    
	/******************************************************
	* Name: void addData()
	* *****************************************************
	* Purpose: Modify record in PPV
	* ******************************************************/
    private void modifyData()
    {
    	LoginItem loginItem = readAllComponents();
    	App.vm.list().set((int)id, loginItem);
    	App.vm.saveData();
    }

	/******************************************************
	* Name: void addData()
	* *****************************************************
	* Purpose: onKey event to handler enter and middle OK button on mobile phone
	* ******************************************************/
	public boolean onKey(View v, int keyCode, KeyEvent event)
	{
		// if caller is editText and 
		// user pressed either enter or middle button on phone  
		if (v.getClass() == EditText.class &&
				(keyCode == 23 || keyCode == 66))
		{
			btnOK.requestFocus();
			return true;
		}
		
		return false;
	}
	
	/******************************************************
	* Name: void onClick(View v)
	* *****************************************************
	* Purpose: Button click event handling method
	* ******************************************************/
	public void onClick(View v)
	{
		int caller = v.getId();
		
		// OK button
		if (caller == R.id.recordModifiable_btnOK)
		{
			// check if title is empty
			if (txtTitle.getText().toString().trim().length() == 0)
			{
				messageBox("Title cannot be empty.", DIALOG_TYPE.OK);
				return;
			}
			
			// identify access mode and to the task
			if (intAccessMode == ACCESS_NEW)
				addData();
			else if (intAccessMode == ACCESS_MODIFY)
				modifyData();
			
			finish();
		}
		else if (caller == R.id.recordModifiable_btnCancel)
		{ // Cancel button
			finish();
		}
	}
	
	/******************************************************
	* Name: public void finish()
	* *****************************************************
	* Purpose: Overridden finish for showing record list on exit
	* ******************************************************/
	@Override
	public void finish()
	{
		App.ui.showDlgRecordList();
		super.finish();
	}
}

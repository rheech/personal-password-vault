/***************************************************************************
* Package: com.example.passwordvault
* Name: UIManager
* Developer: Team Programmers Impossible
* Purpose: Manages all Activities
           and user interface related components.
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

import java.util.Stack;

import android.app.Activity;
import android.content.Intent;

/********************************************************************
* Name: UIManager
* *******************************************************************
* Purpose: Extended Activity class
	       which creates splash screen
* Members: 
*          public UIManager(Activity mainActivity)
*          public Activity getMainActivity()
*          private void showDialog(...)
*          public void showDlgRecordDisplay(long id)
*          public void showDlgRecordList()
*          public void showDlgNewRecord()
*          public void showDlgModifyRecord(long id)
*          public void showSplashScreen(...)
* Reuse Instructions/Limitations: none as 12/2012
*********************************************************************/
public class UIManager
{
	// main activity object
	private Activity _mainActivity;
	// splash sceen shows only one time.
	private boolean splashShownBefore;
	// stack of intents to keep track of user's Activity objects.
	Stack<Intent> _intentStk;
	
	/******************************************************
	* Name: UIManager(Activity mainActivity)
	* *****************************************************
	* Purpose: constructor gets main activity.
	* ******************************************************/
	public UIManager(Activity mainActivity)
	{
		// save main activity and instantiate intent stacks
		_mainActivity = mainActivity;
		_intentStk = new Stack<Intent>();
	}
	
	/******************************************************
	* Name: Activity getMainActivity()
	* *****************************************************
	* Purpose: To access main activity
	* ******************************************************/
	public Activity getMainActivity()
	{
		return _mainActivity;
	}
	
	/******************************************************
	* Name: void showDialog(...)
	* *****************************************************
	* Purpose: show Activities on developer's choice
	* ******************************************************/
	private void showDialog(Class<?> cls)
	{
		// show activity
		Intent intent = new Intent(_mainActivity, cls);
		showDialog(intent);
	}
	
	/******************************************************
	* Name: void showDialog(...)
	* *****************************************************
	* Purpose: show Activities on developer's choice
	* ******************************************************/
	private void showDialog(Intent intent)
	{
		// start activity
		_mainActivity.startActivity(intent);
	}
	
	/******************************************************
	* Name: void showDlgRecordDisplay(long id)
	* *****************************************************
	* Purpose: show Activity which Displays Record 
	* ******************************************************/
	public void showDlgRecordDisplay(long id)
	{
		// put id to parameter, and show dialog
		Intent intent = new Intent(_mainActivity, ActRecordDisplay.class);
		intent.putExtra("ID", Long.toString(id));
		
		showDialog(intent);
	}
	
	/******************************************************
	* Name: void showDlgRecordList()
	* *****************************************************
	* Purpose: show Activity which shows record list
	* ******************************************************/
	public void showDlgRecordList()
	{
		// show record list of the vault
		showDialog(ActRecordList.class);
	}
	
	/******************************************************
	* Name: void showDlgNewRecord()
	* *****************************************************
	* Purpose: show Activity which the creates new record
	* ******************************************************/
	public void showDlgNewRecord()
	{
		// adding new record dialog.
		Intent intent = new Intent(_mainActivity, ActRecordModifiable.class);
		intent.putExtra("ID", Long.toString(-1));
		
		showDialog(intent);
	}
	
	/******************************************************
	* Name: void showDlgModifyRecord(long id)
	* *****************************************************
	* Purpose: show Activity which the modifies new record
	* ******************************************************/
	public void showDlgModifyRecord(long id)
	{
		// modifying record.
		Intent intent = new Intent(_mainActivity, ActRecordModifiable.class);
		intent.putExtra("ID", Long.toString(id));
		
		showDialog(intent);
	}
	
	/******************************************************
	* Name: void showDlgModifyRecord(long milliSeconds)
	* *****************************************************
	* Purpose: show Activity which shows the splash screen
	* ******************************************************/
	public void showSplashScreen(long milliSeconds)
	{
		// show splash screen
		showSplashScreen(milliSeconds, false);
	}
	
	/******************************************************
	* Name: void showSplashScreen(long milliSeconds, boolean ignoreDuplicates)
	* *****************************************************
	* Purpose: show Activity which shows the splash screen
	* ******************************************************/
	public void showSplashScreen(long milliSeconds, boolean ignoreDuplicates)
	{
		// show splash screen only one time. However, if the duplicates
		// are allowed, show splash screen more than one time.
		if (!splashShownBefore || ignoreDuplicates)
		{
			// put parameter (duration) and show splash screen
			Intent intent = new Intent(_mainActivity, SplashActivity.class);
			intent.putExtra("SplashTime", Long.toString(milliSeconds));
			
			showDialog(intent);
			
			splashShownBefore = true;
		}
	}
}

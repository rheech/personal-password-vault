/***************************************************************************
* Package: com.example.passwordvault
* Name: EnhancedActivity
* Developer: Team Programmers Impossible
* Purpose: Derived from activity class.
           Supports message dialog and
           dialog result handler.
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

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/********************************************************************
* Name: EnhancedActivity extends Activity implements OnClickListener
* *******************************************************************
* Purpose: Derived from activity class.
           Supports message dialog and
           dialog result handler.
* Members: 
*          protected void messageBox(...)
*          public void onClick(DialogInterface dialog, int which)
*          public void onDialogResult(int caller, DIALOG_RESULT result)
* Reuse Instructions/Limitations: none as 12/2012
*********************************************************************/
public class EnhancedActivity extends Activity implements OnClickListener
{
	// alert dialog and original caller (control)
	private AlertDialog msgAlert;
	private int _dialogCaller;
	
	// dialog type
	protected enum DIALOG_TYPE
	{
		YESNO,
		OK
	}
	
	// dialog result
	protected enum DIALOG_RESULT
	{
		RESULT_POSITIVE,
		RESULT_NEGATIVE,
		RESULT_NEUTRAL
	}
	
	/******************************************************
	* Name: void messageBox(...)
	* *****************************************************
	* Purpose: To support messagebox method.
	* ******************************************************/
	protected void messageBox(String title, String message, DIALOG_TYPE dialogType)
	{
		messageBox(0, title, message, dialogType);
	}
	
	/******************************************************
	* Name: void messageBox(...)
	* *****************************************************
	* Purpose: To support messagebox method.
	* ******************************************************/
	protected void messageBox(String message, DIALOG_TYPE dialogType)
	{
		messageBox(0, getResources().getString(R.string.app_name),
				message, dialogType);
	}
	
	/******************************************************
	* Name: void messageBox(...)
	* *****************************************************
	* Purpose: To support messagebox method.
	* ******************************************************/
	protected void messageBox(int caller, String message, DIALOG_TYPE dialogType)
	{
		messageBox(caller, getResources().getString(R.string.app_name),
				message, dialogType);
	}
	
	/******************************************************
	* Name: void messageBox(...)
	* *****************************************************
	* Purpose: To support messagebox method.
	* ******************************************************/
	@SuppressWarnings("deprecation")
	protected void messageBox(int caller, String title, String message, DIALOG_TYPE dialogType)
	{
		// set original dialog caller
		_dialogCaller = caller;
		
		// instantiate dialog
		msgAlert = new AlertDialog.Builder(this).create();
		
		// set title and message
		msgAlert.setTitle(title);
		msgAlert.setMessage(message);
		
		// create buttons
		if (dialogType == DIALOG_TYPE.YESNO)
		{
			msgAlert.setButton("Yes", this);
			msgAlert.setButton2("No", this);
		}
		else if (dialogType == DIALOG_TYPE.OK)
		{
			msgAlert.setButton3("OK", this);
		}
		
		// show dialog
		msgAlert.show();
	}

	/******************************************************
	* Name: onClick(DialogInterface dialog, int which)
	* *****************************************************
	* Purpose: on click event of dialog; check dialog result and original caller
	* ******************************************************/
	public void onClick(DialogInterface dialog, int which)
	{
		DIALOG_RESULT msgResult;
		
		switch (which)
		{
		case -1:
			msgResult = DIALOG_RESULT.RESULT_POSITIVE;
			break;
		case -2:
			msgResult = DIALOG_RESULT.RESULT_NEGATIVE;
			break;
		default:
			msgResult = DIALOG_RESULT.RESULT_NEUTRAL;
			break;
		}
		
		// call dialog result event
		onDialogResult(_dialogCaller, msgResult);
	}
	
	/******************************************************
	* Name: onDialogResult(int caller, DIALOG_RESULT result)
	* *****************************************************
	* Purpose: an empty method; developer will override this method to check dialog result
	* ******************************************************/
	public void onDialogResult(int caller, DIALOG_RESULT result)
	{
		
	}
}

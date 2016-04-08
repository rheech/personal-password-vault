/***************************************************************************
* Package: com.example.passwordvault
* Name: ActMain
* Developer: Team Programmers Impossible
* Purpose: The main entry activity of the application.
		This activity is invisible, and creates a splash
		screen and password associated dialogs.
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

import android.os.Bundle;
import android.provider.Settings.Secure;
import android.app.Activity;

import com.example.passwordvault.App;

/********************************************************************
* Name: ActMain extends Activity
* *******************************************************************
* Purpose: Entry point to run main activities.
* Members: 
*          public void onCreate(Bundle savedInstanceState)
* Reuse Instructions/Limitations: none as 12/2012
*********************************************************************/
public class ActMain extends Activity
{		
	/******************************************************
	* Name: public void onCreate(Bundle savedInstanceState)
	* *****************************************************
	* Purpose: Initialize all components for Password Vault
	*          shows splash screen.
	* ******************************************************/
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	// set home directory and main activity
    	App.initializeComponents(this, getFilesDir());
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // get unique number for password
        String serial = Secure.getString(getContentResolver(),
                Secure.ANDROID_ID);
        
        // if serial is null, use application name
        if (serial == null)
        	serial = "PERSONAL_PASSWORD_VAULT";
        
        // if first time user, show new user dialog
        if (App.vm.isFirstTime() ||
        		!App.vm.login(serial))
        {
			// set password
			App.vm.setPassword(serial);
			// login (for encryption)
			App.vm.login(serial);
			// display record list
			App.ui.showDlgRecordList();
        }
        else // show return user dialog
        {
        	// App.ui.showDlgUserReturn();
        	App.vm.login(serial);
        	// display record list
        	App.ui.showDlgRecordList();
        }
        
        // show splash screen
        App.ui.showSplashScreen(3000);
        
        finish();
    }
}

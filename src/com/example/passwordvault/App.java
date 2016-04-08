/***************************************************************************
* Package: com.example.passwordvault
* Name: App
* Developer: Team Programmers Impossible
* Purpose: Static class for accessing
           all shared objects.
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
 * 12/07/2012    Group members              Review Document and Source
 * 12/07/2012    Group members              Final Review
 ***************************************************************************/

package com.example.passwordvault;

import java.io.File;

import android.app.Activity;
import vault.VaultManager;

/********************************************************************
* Name: App
* *******************************************************************
* Purpose: Static class for accessing
           all shared objects.
* Members: 
*          public static void initializeComponents(Activity mainActivity, File homeDir)
* Reuse Instructions/Limitations: none as 12/2012
*********************************************************************/
public class App
{
	// vault manager
	public static VaultManager vm;
	// control all user interfaces
	public static UIManager ui;
	// for only one-time initialization
	private static boolean isInitialized = false;
	private static File _dataFile;
	
	/******************************************************
	* Name: void initializeComponents(Activity mainActivity, File homeDir)
	* *****************************************************
	* Purpose: Initialize all shared objects to interact with GUI and data
	* ******************************************************/
	public static void initializeComponents(Activity mainActivity, File homeDir)
	{
		if (!isInitialized)
		{
			// set data file path
			_dataFile = new File(homeDir, "data.dat");

			// initialize all components
			App.ui = new UIManager(mainActivity);
	        App.vm = new VaultManager(_dataFile);
	        isInitialized = true;
		}
	}
}

/***************************************************************************
* Package: com.example.passwordvault
* Name: SlpashActivity
* Developer: Team Programmers Impossible
* Purpose: Extended Activity class
	       which creates splash screen
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

import com.example.passwordvault.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

/********************************************************************
* Name: EnhancedActivity extends Activity implements OnClickListener
* *******************************************************************
* Purpose: Extended Activity class
	       which creates splash screen
* Members: 
*          public void onCreate(Bundle savedInstanceState)
*          private void _openSplashScreen()
*          private void changePicturebyRotation()
*          private void _setExitTimer(long milliSeconds)
* Reuse Instructions/Limitations: none as 12/2012
*********************************************************************/
@TargetApi(11)
public class SplashActivity extends Activity
{
	// screen duration
	private long _lDuration;
	private ImageView imageView;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// call super class method
		super.onCreate(savedInstanceState);
		
		// create layout dynamically
		LinearLayout LL = new LinearLayout(this);
		
		// all components will be MATCH_PARENT
		LayoutParams layoutParam = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		LL.setLayoutParams(layoutParam);
		
		// create imageView control
		imageView = new ImageView(this);
		
		// layout & scale type (stretch)
		imageView.setLayoutParams(layoutParam);
		imageView.setScaleType(ScaleType.FIT_XY);
		
		// add component to view
		LL.addView(imageView);
		
		// set content
		setContentView(LL);
		
		// get parameter
		Intent intent = getIntent();
		String strTime = intent.getExtras().get("SplashTime").toString();
		
		// if parameter is numeric, get duration value
		if (strTime.matches("^[0-9]+$"))
			_lDuration = Long.parseLong(strTime);
		else
			_lDuration = 3000;
		
		// call splash screen method
		_openSplashScreen();
	}

	/******************************************************
	* Name: void _openSplashScreen()
	* *****************************************************
	* Purpose: initial method of opening splash screen
	* ******************************************************/
	private void _openSplashScreen()
	{
		changePicturebyRotation();
		
		_setExitTimer(_lDuration);
	}
	
	/******************************************************
	* Name: void changePicturebyRotation()
	* *****************************************************
	* Purpose: To set picture depends on the screen state
	* ******************************************************/
	@SuppressWarnings("deprecation")
	private void changePicturebyRotation()
	{
		// get system service ==> display information
		WindowManager wm = (WindowManager) this.getSystemService(WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		
		// get width & height
		Point ptSize = new Point();
		
    	ptSize.x = Math.abs(display.getWidth());
    	ptSize.y = Math.abs(display.getHeight());

    	int resID = 0;
    	
    	// set screen
		if (ptSize.x > ptSize.y) // if horizontal view
			resID = R.drawable.splash_horizontal;
		else
			resID = R.drawable.splash_vertical;
		
		if (resID != 0)
			imageView.setImageResource(resID);
	}
	
	/******************************************************
	* Name: void _setExitTimer(long milliSeconds)
	* *****************************************************
	* Purpose: set timer to close splash screen (this activity)
	* ******************************************************/
	@SuppressLint("HandlerLeak")
	private void _setExitTimer(long milliSeconds)
	{
		// define event handler class
		Handler handler = new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				finish();
			}
		};
		
		// set message handler
		handler.sendEmptyMessageDelayed(0, milliSeconds);
	}
}
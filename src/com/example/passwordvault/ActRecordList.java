/***************************************************************************
* Package: com.example.passwordvault
* Name: ActRecordList
* Developer: Team Programmers Impossible
* Purpose: This is a main activity after
           logged in. Lists all records.
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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/********************************************************************
* Name: ActRecordList extends EnhancedActivity implements OnClickListener, OnItemClickListener
* *******************************************************************
* Purpose: An activity to display password records
* Members: 
*          public void onCreate(Bundle savedInstanceState)
*          public void onPostCreate(Bundle savedInstanceState)
*          public void onClick(View v)
*          public void onItemClick(AdapterView<?> parent, View view,
            		int position, long id)
*          
*          
* Reuse Instructions/Limitations: none as 12/2012
*********************************************************************/
public class ActRecordList extends EnhancedActivity implements OnClickListener, OnItemClickListener
{
	// arr adapter for listview
	ArrayAdapter<String> arrAdapter;
	
	/******************************************************
	* Name: public void onCreate(Bundle savedInstanceState)
	* *****************************************************
	* Purpose: Initialize components including getting
	*          and listing password records
	* ******************************************************/
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);
        
        // get all titles from data
        String[] titleList = App.vm.list().getAllTitles();
        
        // if data is not empty and not corrupted
        if (titleList != null)
        {
        	if (titleList[0] != null)
        	{
        		// add to listview
        		arrAdapter = new ArrayAdapter<String>(this,  
		                android.R.layout.simple_list_item_1,  
		                titleList);
        	}
        }
  
        // register Listview & adapter 
        ListView list = (ListView)findViewById(R.id.recordList_listView);  
        list.setAdapter(arrAdapter);  
        // able to choose only one by one   
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        
        // event on item click
        list.setOnItemClickListener(this);
    }
    
	/******************************************************
	* Name: public void onPostCreate(Bundle savedInstanceState)
	* *****************************************************
	* Purpose: Register all button click event
	* ******************************************************/
    @Override
    public void onPostCreate(Bundle savedInstanceState)
    {
    	super.onPostCreate(savedInstanceState);
    	// register all button click event
    	Button btn = (Button)findViewById(R.id.recordList_btnClose);
    	btn.setOnClickListener(this);
    	
    	btn = (Button)findViewById(R.id.recordList_btnNew);
    	btn.setOnClickListener(this);
    }

	/******************************************************
	* Name: onClick(View v)
	* *****************************************************
	* Purpose: OnClick button handler
	* ******************************************************/
	public void onClick(View v)
	{
		int caller = v.getId();
		
		// close button
		if (caller == R.id.recordList_btnClose)
		{
			//App.ui.clearAllSavedDialogs(this.getIntent());
			finish();
		}
		else if (caller == R.id.recordList_btnNew)
		{ // new button
			App.ui.showDlgNewRecord();
			finish();
		}
	}
	
	/******************************************************
	* Name: onItemClick(AdapterView<?> parent, View view,
            int position, long id)
	* *****************************************************
	* Purpose: on item click event in listview
	* ******************************************************/
	public void onItemClick(AdapterView<?> parent, View view,
            int position, long id)
	{
		// display record
		App.ui.showDlgRecordDisplay(id);
		finish();
	}

}
/***************************************************************************
* Package: Vault
* Name: IOManager.java
* Purpose: provide the file manipulations methods for the program
* Ownership/Programmers in action.
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

package vault;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/********************************************************************
* Name: public class IOManager
* *******************************************************************
* Purpose: serialize two objects _loginList _userInf
* 
* Members: 
*          public IOManager(File fileName)
*          public boolean saveData(UserInfo userInfo, LoginList rec)
*          public boolean loadData()
*          public UserInfo getUserInfo()
*          public LoginList getLoginList()
*          
* Reuse Instructions/Limitations: none as 12/2012
*********************************************************************/
public class IOManager
{

	File _file; //file property
	IOContainer ioContainer;//container for serializing
	/********************************************************************
	* Name: public IOManager(File fileName)
	* *******************************************************************
	* Purpose: constructor gets the name of the data file
	* 
	* Reuse Instructions/Limitations: none as 12/2012
	*********************************************************************/
	public IOManager(File fileName)
	{
    	_file = fileName;
    	ioContainer = new IOContainer(null, null);
	}
	/********************************************************************
	* Name: public boolean saveData(UserInfo userInfo, LoginList rec)
	* *******************************************************************
	* Purpose: save all data (serialize)
	* on entry:
	*      receives the user data
	* on exit:
	*      saves de data
	* Reuse Instructions/Limitations: none as 12/2012
	*********************************************************************/

	public boolean saveData(UserInfo userInfo, LoginList rec)
	{
		try
		{
			FileOutputStream opt = new FileOutputStream(_file);
			ObjectOutputStream os = new ObjectOutputStream(opt);
			ioContainer = new IOContainer(userInfo, rec);
			
			os.writeObject(ioContainer);	
			os.close();
			opt.close();
			return true;
		}
		catch (IOException iex)
		{
			return false;
		}
	}
	
	/********************************************************************
	* Name: public boolean loadData()
	* *******************************************************************
	* Purpose: load objects form file
	* Reuse Instructions/Limitations: none as 12/2012
	*********************************************************************/
	
	@SuppressWarnings("resource")
	public boolean loadData()
	{
		try
		{
			FileInputStream ipt = new FileInputStream(_file);
			ipt = new FileInputStream(_file);
			ObjectInputStream ios = new ObjectInputStream(ipt);
			
			ioContainer = (IOContainer)ios.readObject();
			
			ios.close();
			ipt.close();
		}
		catch (Exception iex)
		{
			return false;
		}
		
		return true;
	}
	/********************************************************************
	 * Name:public UserInfo getUserInfo()
	 * *******************************************************************
	 * Purpose: accessor for user information
	 * on exit: returns ioContainer.getUserInfo()
	 * Reuse Instructions/Limitations: none as 12/2012
	 *********************************************************************/	

	public UserInfo getUserInfo()
	{
		return ioContainer.getUserInfo();
	}
	/********************************************************************
	* Name:public LoginList getLoginList()
	* *******************************************************************
	* Purpose: accessor function for loginlist
	* Reuse Instructions/Limitations: none as 12/2012
	*********************************************************************/	
	public LoginList getLoginList()
	{
		return ioContainer.getLoginList();
	}
}

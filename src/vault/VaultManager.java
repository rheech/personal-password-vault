/***************************************************************************
* Package: Vault
* Name: VaultManager.java
* Purpose: create the encryption procedures for the program.
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
/********************************************************************
* Name: Provide methods for user interface
* *******************************************************************
* Purpose: serialize two objects _loginList _userInf
* 
* Members: 
*          public VaultManager(File filePath) 
* 		   public boolean isFirstTime()
* 		   public void setPassword(String password)
* 		   public boolean login(String password)
*          public void logout()
*          public boolean isLoggedIn()
*          public LoginList list()
*          public void saveData()
* 			
* Reuse Instructions/Limitations: none as 12/2012
*********************************************************************/
public class VaultManager
{

	private IOManager ioManager;//ioManager
	private LoginList loginList;//LoginList
	private UserInfo userInfo;  //userInfo
	
	private boolean _loggedIn;//logged in
	private boolean _isFirst;//first time user
	
	public Encryption AES;
	/****************************************************************
	* Name: public VaultManager(File filePath)
	* ***************************************************************
	* Purpose: constructor/verifies if is first time user and load data
	* Reuse Instructions/Limitations./none as 12/4/2012
	*****************************************************************/	
	public VaultManager(File filePath)
	{
		userInfo = new UserInfo();
		ioManager = new IOManager(filePath);
		if (ioManager.loadData())
		{
			userInfo = ioManager.getUserInfo();
			loginList = ioManager.getLoginList();
		}
		_isFirst = false;
		if (loginList == null || 
				loginList.isCorrupted())
		{
			loginList = new LoginList();
			_isFirst = true;
		}
		_loggedIn = false;
	}
	/****************************************************************
	* Name: public boolean isFirstTime()
	* ***************************************************************
	* Purpose: check if first time user
	* Reuse Instructions/Limitations./none as 12/4/2012
	*****************************************************************/	
	public boolean isFirstTime()
	{
		return _isFirst;
	}
	/****************************************************************
	* Name: public void setPassword(String password)
	* ***************************************************************
	* Purpose: set password
	* Reuse Instructions/Limitations./none as 12/4/2012
	*****************************************************************/	
	public void setPassword(String password)
	{
		userInfo.setPassword(password);
		loginList.clear();
		saveData();
	}
	/****************************************************************
	* Name: public boolean login(String password)
	* ***************************************************************
	* Purpose: login & initialize encryption methods
	* Reuse Instructions/Limitations./none as 12/4/2012
	*****************************************************************/	
	public boolean login(String password)
	{
		AES = new Encryption(password);
		_loggedIn = userInfo.checkPassword(password);
		
		return _loggedIn;
	}
	/****************************************************************
	* Name: public void logout()
	* ***************************************************************
	* Purpose: logout & delete encryption methods
	* Reuse Instructions/Limitations./none as 12/4/2012
	*****************************************************************/	
	public void logout()
	{
		AES = null;
		_loggedIn = false;
	}
	/****************************************************************
	* Name: public boolean isLoggedIn()
	* ***************************************************************
	* Purpose: check if currently logged in
	* Reuse Instructions/Limitations./none as 12/4/2012
	*****************************************************************/	
	public boolean isLoggedIn()
	{
		return _loggedIn;
	}
	/****************************************************************
	* Name: public LoginList list()
	* ***************************************************************
	* Purpose: returns linked list of data
	* Reuse Instructions/Limitations./none as 12/4/2012
	*****************************************************************/
	public LoginList list()
	{
		return loginList;
	}
	/****************************************************************
	* Name: public void saveData()
	* ***************************************************************
	* Purpose: save all data to file
	* Reuse Instructions/Limitations./none as 12/4/2012
	*****************************************************************/
	public void saveData()
	{
		ioManager.saveData(userInfo, loginList);
	}
}

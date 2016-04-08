/***************************************************************************
* Package: Vault
* Name: LoginItem.java
* Purpose: create the class members, constructors, accessors and modifiers
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

import java.io.Serializable;

import com.example.passwordvault.App;
/********************************************************************
* Name: public class LoginItem implements Serializable
* *******************************************************************
* Purpose: create the accessor and modifiers of the program
* 
* Members: 
*          public LoginItem()
*          public LoginItem(String title, String loginID, String loginPW, String url)
*          public void setTitle(String title)
*          public String getTitle()
*          public void setLoginID(String loginID)
*          public String getLoginID()
*          public void setLoginPW(String loginPW)
*          public String getLoginPW()
*          public void setURL(String url)
*          public String getURL()
* Reuse Instructions/Limitations: none as 12/2012
* 
*********************************************************************/
public class LoginItem implements Serializable
{
	private static final long serialVersionUID = -5630851296104602070L;
	private String _title;//holds the title
	private byte[] _loginID, _loginPW, _url;//holds userName Password and url
	
	public LoginItem()
	{
	}
	/********************************************
	* Name: methodname
	* *********************************************
	* Purpose: A single simple sentence.
	* on Entry
	* Parameters and other variables
	* on Exit
	* All variables modified and/or returned to the caller.
	* Reuse Instructions/Limitations.
	******************************************************/
	// constructor sets all components
	public LoginItem(String title, String loginID, String loginPW, String url)
	{
		setTitle(title);
		setLoginID(loginID);
		setLoginPW(loginPW);
		setURL(url);
	}
	/********************************************************************
	* Name: public void setTitle(String title)
	* *******************************************************************
	* Purpose: odifier for title
	* 
	* Reuse Instructions/Limitations: none as 12/2012
	*********************************************************************/	
	public void setTitle(String title)
	{
		_title = title;
	}
	/********************************************************************
	* Name: public String getTitle()
	* *******************************************************************
	* Purpose: accessor for title
	* 
	* Reuse Instructions/Limitations: none as 12/2012
	*********************************************************************/
	public String getTitle()
	{
		return _title;
	}
	/********************************************************************
	* Name: public void setLoginID(String loginID)
	* *******************************************************************
	* Purpose: modifier for loginID (encrypt)
	* 
	* Reuse Instructions/Limitations: none as 12/2012
	*********************************************************************/
	public void setLoginID(String loginID)
	{
		_loginID = App.vm.AES.encrypt(loginID);
	}
	/********************************************************************
	* Name: public String getLoginID()
	* *******************************************************************
	* Purpose: accessor for loginID (decrypt)
	* 
	* Reuse Instructions/Limitations: none as 12/2012
	*********************************************************************/
	public String getLoginID()
	{
		return App.vm.AES.decrypt(_loginID);
	}
	/********************************************************************
	* Name: public void setLoginPW(String loginPW)
	* *******************************************************************
	* Purpose: modifier for LoginPW (encrypt)
	* 
	* Reuse Instructions/Limitations: none as 12/2012
	*********************************************************************/
	public void setLoginPW(String loginPW)
	{
		_loginPW = App.vm.AES.encrypt(loginPW);
	}
	/********************************************************************
	* Name: public String getLoginPW()
	* *******************************************************************
	* Purpose: accessor for LoginPW (decrypt)
	* 
	* Reuse Instructions/Limitations: none as 12/2012
	*********************************************************************/
	public String getLoginPW()
	{
		return App.vm.AES.decrypt(_loginPW);
	}
	/********************************************************************
	* Name: public void setURL(String url)
	* *******************************************************************
	* Purpose: modifier for URL (encrypt)
	* 
	* Reuse Instructions/Limitations: none as 12/2012
	*********************************************************************/
	public void setURL(String url)
	{
		_url = App.vm.AES.encrypt(url);
	}
	/********************************************************************
	* Name: public String getURL()
	* *******************************************************************
	* Purpose: accessof for URL (decrypt)
	* 
	* Reuse Instructions/Limitations: none as 12/2012
	*********************************************************************/
	public String getURL()
	{
		return App.vm.AES.decrypt(_url);
	}
}

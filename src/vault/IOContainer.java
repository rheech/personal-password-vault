/***************************************************************************
* Package: Vault
* Name: IOContaniner.java
* Purpose: serialize the program
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
/********************************************************************
* Name: public class IOContainer implements Serializable
* *******************************************************************
* Purpose: serialize two objects _loginList _userInf
* 
* Members: 
*          public IOContainer(UserInfo userInfo, LoginList loginList) 
* 		   public UserInfo getUserInfo()
* 		   public LoginList getLoginList()
* Reuse Instructions/Limitations: none as 12/2012
*********************************************************************/
public class IOContainer implements Serializable
{
	private static final long serialVersionUID = 1496549186233710618L;
	private LoginList _loginList;
	private UserInfo _userInfo;
/********************************************************************
* Name: public IOContainer(UserInfo userInfo, LoginList loginList)
* *******************************************************************
* Purpose: constructor to save objects
* 
* Reuse Instructions/Limitations: none as 12/2012
*********************************************************************/	
	public IOContainer(UserInfo userInfo, LoginList loginList)
	{
		_loginList = loginList;
		_userInfo = userInfo;
	}
/********************************************************************
* Name: public UserInfo getUserInfo()
* *******************************************************************
* Purpose: return user info
* 
* Reuse Instructions/Limitations: none as 12/2012
*********************************************************************/		
	// accessor for userinfo
	public UserInfo getUserInfo()
	{
		return _userInfo;
	}
/********************************************************************
* Name: public LoginList getLoginList()
* *******************************************************************
* Purpose: return login list
* 
* Reuse Instructions/Limitations: none as 12/2012
*********************************************************************/		
	public LoginList getLoginList()
	{
		return _loginList;
	}
}

/***************************************************************************
* Package: Vault
* Name: UserInfo.java
* Purpose: Create the owner of the vault information data
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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
/********************************************************************
* Name: public class UserInfo implements Serializable
* *******************************************************************
* Purpose: provide the interface to enter the information of the user 
* 
* Members: public UserInfo()
* 		   public static byte[] getMD5(String text)
* 		   public void setPassword(String pw)  
*          public boolean checkPassword(String pw)
* Reuse Instructions/Limitations: none as 12/2012
*********************************************************************/
public class UserInfo implements Serializable
{
	private static final long serialVersionUID = -3534560578676296610L;
	private byte[] _pHash;
	/****************************************************************
	* Name: public UserInfo()
	* ***************************************************************
	* Purpose: constructor
	* Reuse Instructions/Limitations.
	*****************************************************************/	
	public UserInfo()
	{
		
	}
	/****************************************************************
	* public static byte[] getMD5(String text)
	* ***************************************************************
	* Purpose: check md5 hash
	* on Exit
	* Reuse Instructions/Limitations.
	*****************************************************************/	
	public static byte[] getMD5(String text)
	{
		byte buffer[] = null;
		
		try
		{
			MessageDigest msgDigest = MessageDigest.getInstance("MD5");
			msgDigest.update(text.getBytes());
			buffer = msgDigest.digest();
		}
		catch (NoSuchAlgorithmException ex)
		{
			ex.printStackTrace();
		}
		
		return buffer;
	}
	/******************************************************
	* Name: public void setPassword(String pw)
	* *****************************************************
	* Purpose: set the new password
	* Reuse Instructions/Limitations.
	******************************************************/
	// set new password
	public void setPassword(String pw)
	{
		_pHash = getMD5(pw).clone();
	}
	/******************************************************
	* 	public boolean checkPassword(String pw)
	* *****************************************************
	* Purpose: check if user input is correct password 
	* Reuse Instructions/Limitations.
	******************************************************/
	public boolean checkPassword(String pw)
	{
		return (Arrays.equals(_pHash, getMD5(pw)));
	}
}

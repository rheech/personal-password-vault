/***************************************************************************
* Package: Vault
* Name: Encryption.java
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

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import android.annotation.TargetApi;
 
/***********************************************************
* Name: public class Encryption
* **********************************************************
* Purpose: drive the encryption procedures of the program
* on Entry
* generates the secret key
* assigment of encrypton and decryption
* Members: 
*          public Encryption(String strKey) -> Constructor 
* 		   private byte[] generateHash(String strKey)
* 		   public byte[] encrypt(String orgString)
* 		   public String decrypt(byte[] buffer)
* Reuse Instructions/Limitations: none as 12/2012
************************************************************/


public class Encryption
{
	SecretKeySpec key;
	Cipher eCipher, dCipher;
	
/******************************************************
* Name: public Encryption(String strKey)
* *****************************************************
* Purpose: create the key for encryptation
* on Entry
* an array is create and initialized
* on Exit
* Reuse Instructions/Limitations. none as 12/04/2012
******************************************************/
	public Encryption(String strKey)
	{
		try
		{
			byte[] byteKey = generateHash(strKey);
			key = new SecretKeySpec(byteKey, "AES");
			
			eCipher = Cipher.getInstance("AES");
			dCipher = Cipher.getInstance("AES");

			eCipher.init(Cipher.ENCRYPT_MODE, key);
			dCipher.init(Cipher.DECRYPT_MODE, key);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}		
	}
	
/*********************************************************
* Name: private byte[] generateHash(String strKey)
* ********************************************************
* Purpose: Generate the key using the encodig utf-8, create 
*          a hash and define 128bit as key
* on Entry
*        define utf-8 create the hash
* on Exit
*         returns the key
* Reuse Instructions/Limitations.
**********************************************************/
	@TargetApi(9)
	private byte[] generateHash(String strKey)
			throws UnsupportedEncodingException, NoSuchAlgorithmException
	{
		byte[] key = strKey.getBytes("UTF-8");
		MessageDigest sha = MessageDigest.getInstance("SHA-1");
		key = sha.digest(key);

		key = Arrays.copyOf(key, 16);
		
		return key;
	}
/***********************************************************
* Name: public byte[] encrypt(String orgString)
* **********************************************************
* Purpose: Encrypt string to bytes
* on Entry
* recieves a string,
* on Exit
* returns the encrypted strings
* Reuse Instructions/Limitations. none as 12/04/2012
***********************************************************/
	public byte[] encrypt(String orgString)
	{
		byte[] encBuffer = null;
		try
		{
			byte[] orgBuffer = orgString.getBytes("UTF8");
			encBuffer = eCipher.doFinal(orgBuffer);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		return encBuffer;
	}
/*****************************************************
* Name: public String decrypt(byte[] buffer)
* ****************************************************
* Purpose: Decryption bytes to string
* on Entry
* receives the array to be decrypted
* on Exit
* returns the string
* Reuse Instructions/Limitations. none as 12/04/2012
******************************************************/
	public String decrypt(byte[] buffer)
	{
		String orgString = "";
		
		try
		{
			byte[] orgBuffer = dCipher.doFinal(buffer);
			orgString = new String(orgBuffer, "UTF8");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		return orgString;
	}

}
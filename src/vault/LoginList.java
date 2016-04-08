/***************************************************************************
* Package: Vault
* Name: LoginList.java
* Purpose: list data manipulation
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
import java.util.LinkedList;
/********************************************************************
* Name: public class LoginList extends LinkedList<LoginItem>
* *******************************************************************
* Purpose: verify data is correct and read all titles
* Members: public boolean isCorrupted()
*          public String[] getAllTitles()
* Reuse Instructions/Limitations: none as 12/2012
*********************************************************************/
public class LoginList extends LinkedList<LoginItem>
			implements Serializable
{
	private static final long serialVersionUID = 377291125542067224L;
	/****************************************************************
	* Name: public boolean isCorrupted()
	* ***************************************************************
	* Purpose: check if data is corrupted (serialization error)
	* on Exit
	* returns true or not if the data has files
	* Reuse Instructions/Limitations.
	*****************************************************************/
	
	public boolean isCorrupted()
	{
		if (!this.isEmpty())
		{
			if (getFirst() == null)
			{
				return true;
			}
		}
		
		return false;
	}
	/****************************************************************
	* Name: public String[] getAllTitles()
	* ***************************************************************
	* Purpose: get all titles in linkedlist, method verify the existance
	*          of data.
	* Reuse Instructions/Limitations.
	*****************************************************************/
	// 
	public String[] getAllTitles()
	{
		LinkedList<String> strTitles = new LinkedList<String>();
		String[] arr = null;
		
		if (!this.isEmpty())
		{
			int i = 0;
			LoginItem item = getFirst();
			
			strTitles.add(item.getTitle());
			i++;
			
			while (this.getLast() != item)
			{
				item = this.get(i);
				strTitles.add(item.getTitle());
				i++;
			}
			
			arr = strTitles.toArray(new String[0]);
		}
		
		return arr;
	}
}

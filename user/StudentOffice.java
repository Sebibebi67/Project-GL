package user;

/**
 * 
 * This class contains all methods and attributes linked to a member of the student office
 * 
 * @author Sébastien HERT
 * 
 */

public class StudentOffice extends Administration{

	/**
	 * Constructor
	 * @param login login of the user
	 */
	public StudentOffice (String login) {
		this.initArray();
		this.setLogin(login);
	}

}

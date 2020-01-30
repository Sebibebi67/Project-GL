package user;

/**
 * 
 * This class contains all methods and attributes linked to a member of the Departement of Education
 * 
 * @author Sébastien HERT
 * 
 */
public class DepartementOfEducation extends Administration{

	/**
	 * Constructor
	 * @param login login of the user
	 */
    public DepartementOfEducation(String login){
		this.initArray();
		this.setLogin(login);
		this.createModules();
	}

}
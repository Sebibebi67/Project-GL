package user;

/**
 * 
 * This class contains all methods and attributes linked to a user account
 * 
 * @author Sébastien HERT
 * @author Dejan PARIS
 * 
 */

public class UserAccount {

	private String login;
	private String password;

    /**
     * Constructor
     */
    public UserAccount () {}
    
    /**
     * Constructor
     * @param login login of the user
     * @param pswd password of the user
     */
    public UserAccount (String login, String pswd) {
        this.login = login;
        this.password = pswd;
    }

    /**
     * @return String return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return String return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the login of the user
     */
    public String toString() {
        return login;
    }

    /**
     * Tests if an account is equal to an other one
     * @author Dejan PARIS
     * @param account account to test
     * @return true or false
     */
    public boolean equals(UserAccount account)
    {
        return this.login.equals(account.getLogin());
    }
}
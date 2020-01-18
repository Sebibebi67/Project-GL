package user;

public class UserAccount {

	private String login;
	private String password;

    public UserAccount () {}
    
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

    public String toString() {
        return login;
    }

    public boolean equals(UserAccount account)
    {
        return this.login.equals(account.getLogin());
    }
}
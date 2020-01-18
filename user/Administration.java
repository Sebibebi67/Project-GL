package user;

public abstract class Administration implements Role{

	private String login;

	public Administration () {

	}

	public String getLogin()
	{
		return this.login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

}
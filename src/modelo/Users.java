package modelo;

public class Users {
	private long username;
	private String email;
	private String pass;
	
	public Users(long username, String email, String pass) {
		super();
		this.setUsername(username);
		this.setEmail(email);
		this.setPass(pass);
	}

	public long getUsername() {
		return username;
	}

	public void setUsername(long username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
	
	
	

}

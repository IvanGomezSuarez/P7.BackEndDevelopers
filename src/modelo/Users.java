package modelo;

public class Users {
	private String username;
	private String email;
	private String pass;
	
	public Users(String username, String email, String pass) {
		super();
		this.setUsername(username);
		this.setEmail(email);
		this.setPass(pass);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
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

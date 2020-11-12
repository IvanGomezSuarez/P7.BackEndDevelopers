package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name= "username")
	private String username;
	
	@Column(name= "email")
	private String email;
	
	@Column(name= "pass")
	private String pass;
	
	
	public User() {
		super();
	}


	public User(String username, String email, String pass) {
		super();
		this.setUsername(username);
		this.setEmail(email);
		this.setPass(pass);
	}


	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}


	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + ", pass=" + pass + "]";
	}



}
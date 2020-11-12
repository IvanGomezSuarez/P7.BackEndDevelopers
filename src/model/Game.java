package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the game database table.
 * 
 */
@Entity
@Table(name="game")

public class Game implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "idgame")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idgame;
	
	@Column(name = "score")
	private int score;
	
	@Column(name="users_username1")
	private String users_username1;


	public Game() {
		super();
	}

	public Game(long idgame, int score, String users_username1) {
		super();
		this.idgame = idgame;
		this.score = score;
		this.users_username1 = users_username1;
	}
	


	public long getIdgame() {
		return idgame;
	}

	public void setIdgame(int idgame) {
		this.idgame = idgame;
	}


	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getUsers_username1() {
		return users_username1;
	}

	public void setUsers_username1(String users_username1) {
		this.users_username1 = users_username1;
	}

	@Override
	public String toString() {
		return "Game [idgame=" + idgame + ", score=" + score + ", users_username1=" + users_username1 + "]";
	}



}
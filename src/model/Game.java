package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the game database table.
 * 
 */
@Entity
@Table(name="game")
@NamedQuery(name="Game.findAll", query="SELECT g FROM Game g")

public class Game implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "idgame")
	private int idgame;
	
	@Column(name = "score")
	private int score;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="users_username1")
	private User user;

	public int getIdgame() {
		return idgame;
	}

	public void setIdgame(int idgame) {
		this.idgame = idgame;
	}

	public Game() {
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
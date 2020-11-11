package entities;

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

	@Column(name = "score")
	private int score;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="users_username1")
	private User user;

	@Id
    @Column(name = "id")
    private String id;
	
	public Game() {
	}
	
	public Game(String id, User user, int score) {
		this.id = id;
		this.user = user;
		this.score = score;
	}
	
	public Game(User user, int score) {
		this.user = user;
		this.score = score;
	}

	public int getScore() {
		return score;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
package modelo;



public class Game {
	
	private String idGame;
	private int score;
	private String users_username1;
	
	public Game(String idGame, int score, String users_username1) {
		super();
		this.idGame = idGame;
		this.setScore(score);
		this.setUsers_username1(users_username1);
	}
	public String getIdGame() {
		return idGame;
	}
	public void setIdGame(String idGame) {
		this.idGame = idGame;
	}
	public int getScore() {
		return score;
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
		return "Game [idGame=" + idGame + ", score=" + score + ", users_username1=" + users_username1 + "]";
	}
	
	
	
	
	
	

}

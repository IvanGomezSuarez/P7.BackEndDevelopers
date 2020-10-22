package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import dao.DAOException;
import dao.DAOGame;
import dao.DAOLetters;
import dao.DAOManager;
import dao.DAOUsers;
import dao.DAOWords;
import modelo.Users;

public class MySQLDAOManager implements DAOManager {
	private Connection conn;
	
	private DAOGame game = null;
	private DAOLetters letters = null;
	private DAOUsers users = null;
	private DAOWords words = null;
	
	
	public MySQLDAOManager(String host, String database, String username, String password) throws SQLException {
		conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database, username, password);
	}


	@Override
	public DAOGame getDAOGame() {
		if (game == null) {
			game = new GameMySQLDAO(conn);
		}
		return game;
	}


	@Override
	public DAOLetters getDAOLetters() {
		if (letters == null) {
			letters = new LettersMySQLDAO(conn);
		}
		return letters;
	}



	@Override
	public DAOUsers getDAOUsers() {
		if (users == null) {
			users = new UsersMySQLDAO(conn);
		}
		return users;
	}


	@Override
	public DAOWords getDAOWord() {
		if (words == null) {
			words = new WordsMySQLDAO(conn);
		}
		return words;
	}
// para probar que se muestran los datos de un determinado DAO, en este caso del de DAOUsers
	public static void main(String[] args) throws SQLException, DAOException {
		MySQLDAOManager man = new MySQLDAOManager("localhost:3306","sopaletras", "root", "");
		List<Users> users = man.getDAOUsers().getAll();
		System.out.println(users);
	}

}

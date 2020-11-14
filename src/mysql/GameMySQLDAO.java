package mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.DAOException;
import dao.DAOGame;
import modelo.Game;

//esta clase contiene todos los métodos para el CRUD con la BD
public class GameMySQLDAO implements DAOGame {
	
	private Connection conn;

	public GameMySQLDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Game get(String id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Game> getAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Game t) throws DAOException {

	}

	@Override
	public void update(Game t) throws DAOException {

	}

	@Override
	public void delete(Game t) throws DAOException {

	}

}

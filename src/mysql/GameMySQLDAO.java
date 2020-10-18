package mysql;

import java.sql.SQLException;
import java.util.List;

import dao.DAOGame;
import modelo.Game;

//esta clase contiene todos los métodos para el CRUD con la BD
public class GameMySQLDAO implements DAOGame {

	@Override
	public Game get(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Game> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Game t) throws SQLException {

	}

	@Override
	public void update(Game t) throws SQLException {

	}

	@Override
	public void delete(Game t) throws SQLException {

	}

}

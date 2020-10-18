package mysql;

import java.sql.SQLException;
import java.util.List;

import DAO.DAOException;
import DAO.DAOGame;
import modelo.Game;

//esta clase contiene todos los m�todos para el CRUD con la BD
public class GameMySQLDAO implements DAOGame {

	@Override
	public Game get(Long id) throws DAOException {
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

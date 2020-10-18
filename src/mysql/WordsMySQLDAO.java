package mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.DAOException;
import dao.DAOWords;
import modelo.Words;
// esta clase contiene todos los métodos para el CRUD con la BD
public class WordsMySQLDAO implements DAOWords {

	private Connection conn;
	
	public WordsMySQLDAO(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Words get(Long id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Words> getAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Words t) throws DAOException {

	}

	@Override
	public void update(Words t) throws DAOException {

	}

	@Override
	public void delete(Words t) throws DAOException {

	}

}

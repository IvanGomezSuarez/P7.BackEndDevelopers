package mysql;

import java.sql.SQLException;
import java.util.List;

import dao.DAOException;
import dao.DAOLetters;
import modelo.Letters;
//esta clase contiene todos los métodos para el CRUD con la BD
public class LettersMySQLDAO implements DAOLetters {

	@Override
	public Letters get(String id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Letters> getAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Letters t) throws DAOException {

	}

	@Override
	public void update(Letters t) throws DAOException {

	}

	@Override
	public void delete(Letters t) throws DAOException {

	}

}

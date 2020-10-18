package mysql;

import java.sql.SQLException;
import java.util.List;

import dao.DAOLetters;
import modelo.Letters;
//esta clase contiene todos los métodos para el CRUD con la BD
public class LettersMySQLDAO implements DAOLetters {

	@Override
	public Letters get(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Letters> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Letters t) throws SQLException {

	}

	@Override
	public void update(Letters t) throws SQLException {

	}

	@Override
	public void delete(Letters t) throws SQLException {

	}

}

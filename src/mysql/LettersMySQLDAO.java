package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DAOException;
import dao.DAOLetters;
import modelo.Letters;

//esta clase contiene todos los m�todos para el CRUD con la BD
public class LettersMySQLDAO implements DAOLetters {
	
	final String INSERT = "INSERT INTO letters(idletter) VALUES(?)";
	final String UPDATE = "UPDATE letters SET idletter = ? WHERE idletter = ?";
	final String DELETE = "DELETE FROM letters WHERE idletter = ?";
	final String GETALL = "SELECT idletter FROM letters";
	final String GETONE = "SELECT idletter FROM letters WHERE idletter = ?";
	
	private Connection conn;

	public LettersMySQLDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Letters get(String id) throws DAOException {
		PreparedStatement stat = null;
		ResultSet rs = null;
		Letters t = null;
		try {
			stat = conn.prepareStatement(GETONE);
			stat.setString(1, id);
			rs = stat.executeQuery();
			if (rs.next()) {
				t = convertir(rs);
			}else {
				throw new DAOException("No se ha encontrado la letra.");
			}
		}catch (SQLException ex) {
			throw new DAOException("Error en SQL", ex);
		}finally {
			if (rs != null) {
				try {
					rs.close();
				}catch (SQLException ex) {
					new DAOException("Error en SQL", ex);
				}
			}
			if (stat != null) {
				try {
					stat.close();
				}catch (SQLException ex) {
					new DAOException("Error en SQL", ex);
				}
			}
		}
		return t;
	}

	@Override
	public List<Letters> getAll() throws DAOException {
		PreparedStatement stat = null;
		ResultSet rs = null;
		List<Letters> letter = new ArrayList<>();
		try {
			stat = conn.prepareStatement(GETALL);
			rs = stat.executeQuery();
			while (rs.next()) {
				letter.add(convertir(rs));
				
			}
		}catch (SQLException ex) {
			throw new DAOException("Error en SQL", ex);
		}finally {
			if (rs != null) {
				try {
					rs.close();
				}catch (SQLException ex) {
					new DAOException("Error en SQL", ex);
				}
			}
			if (stat != null) {
				try {
					stat.close();
				}catch (SQLException ex) {
					new DAOException("Error en SQL", ex);
				}
			}
		}
		return letter;
	}

	@Override
	public void save(Letters t) throws DAOException {
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(INSERT);
			stat.setString(1, t.getIdLetter());

			if (stat.executeUpdate() == 0){
				throw new DAOException("Es posible que no se hayan guardado los datos.");
			}
		} catch (SQLException ex) {
			throw new DAOException("Error en SQL", ex);
		}finally {
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException ex) {
					throw new DAOException("Error en SQL", ex);
				}
			}
		
		}

	}

	@Override
	public void update(Letters t) throws DAOException {
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(UPDATE);
			stat.setString(1, t.getIdLetter());
			if (stat.executeUpdate() == 0) {
				throw new DAOException("La letra no se ha actualizado.");
			}
	}catch (SQLException ex) {
		throw new DAOException("Error de SQL", ex);
		}finally {
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException ex) {
					throw new DAOException("Error de SQL", ex);
				}
			}
		}

	}

	@Override
	public void delete(Letters t) throws DAOException {
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(DELETE);
			stat.setString(1, t.getIdLetter());
			if (stat.executeUpdate() == 0) {
				throw new DAOException("La letra no se ha borrado.");
			}
	}catch (SQLException ex) {
		throw new DAOException("Error de SQL", ex);
		}finally {
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException ex) {
					throw new DAOException("Error de SQL", ex);
				}
			}
		}

	}
	
	private Letters convertir(ResultSet rs) throws SQLException {
		String idLetter = rs.getString("idletter");
		Letters letter = new Letters(idLetter);
		
		return letter;
	}

}

package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DAOException;
import dao.DAOWords;
import modelo.Words;

// esta clase contiene todos los m�todos para el CRUD con la BD
public class WordsMySQLDAO implements DAOWords {
	
	final String INSERT = "INSERT INTO words (idword) VALUES(?)";
	final String UPDATE = "UPDATE words SET idword = ? WHERE idword = ?";
	final String DELETE = "DELETE FROM words WHERE idword = ?";
	final String GETALL = "SELECT * FROM words";
	final String GETONE = "SELECT idword FROM words WHERE idword = ?";

	private Connection conn;
	
	public WordsMySQLDAO(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Words get(String id) throws DAOException {
		PreparedStatement stat = null;
		ResultSet rs = null;
		Words t = null;
		try {
			stat = conn.prepareStatement(GETONE);
			stat.setString(1, id);
			rs = stat.executeQuery();
			if (rs.next()) {
				t = convertir(rs);
			}else {
				throw new DAOException("No se ha encontrado la palabra.");
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
	public List<Words> getAll() throws DAOException {
		PreparedStatement stat = null;
		ResultSet rs = null;
		List<Words> word = new ArrayList<>();
		try {
			stat = conn.prepareStatement(GETALL);
			rs = stat.executeQuery();
			while (rs.next()) {
				word.add(convertir(rs));
				
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
		return word;
	}

	@Override
	public void save(Words t) throws DAOException {
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(INSERT);
			stat.setString(1, t.getIdWord());
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
	public void update(Words t) throws DAOException {
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(UPDATE);
			stat.setString(1, t.getIdWord());
			if (stat.executeUpdate() == 0) {
				throw new DAOException("La palabra no se ha actualizado.");
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
	public void delete(Words t) throws DAOException {
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(DELETE);
			stat.setString(1, t.getIdWord());
			if (stat.executeUpdate() == 0) {
				throw new DAOException("La palabra no se ha borrado.");
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
	
	private Words convertir(ResultSet rs) throws SQLException {
		String idWord = rs.getString("idword");
		Words word = new Words(idWord);
		
		return word;
	}
	
	public static void main(String[] args) throws SQLException, DAOException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sopaletras","root","");
			DAOWords dao = new WordsMySQLDAO(conn);
			List<Words> word = dao.getAll();
			for (Words t: word) {
				System.out.println(t.toString());
			}
			
		}finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

}

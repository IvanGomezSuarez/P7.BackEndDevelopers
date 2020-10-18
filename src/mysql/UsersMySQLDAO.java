package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DAOException;
import dao.DAOUsers;
import modelo.Users;
//esta clase contiene todos los métodos para el CRUD con la BD
public class UsersMySQLDAO implements DAOUsers {
	
	final String INSERT = "INSERT INTO users(username, email, pass) VALUES(?, ? ,?)";
	final String UPDATE = "UPDATE users SET username = ?, email = ? , pass = ? WHERE username = ?";
	final String DELETE = "DELETE FROM users WHERE username = ?";
	final String GETALL = "SELECT username, email, pass FROM users";
	final String GETONE = "SELECT username, email, pass FROM users WHERE username = ?";
	
	private Connection conn;
	
	public UsersMySQLDAO(Connection conn) {
		this.conn = conn;
	}
	

	@Override
	public Users get(Long id) throws DAOException{
		PreparedStatement stat = null;
		ResultSet rs = null;
		Users t = null;
		try {
			stat = conn.prepareStatement(GETONE);
			stat.setLong(1, id);
			rs = stat.executeQuery();
			if (rs.next()) {
				t = convertir(rs);
			}else {
				throw new DAOException("No se ha encontrado el usuario.");
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
	public List<Users> getAll() throws DAOException {
		PreparedStatement stat = null;
		ResultSet rs = null;
		List<Users> user = new ArrayList<>();
		try {
			stat = conn.prepareStatement(GETALL);
			rs = stat.executeQuery();
			while (rs.next()) {
				user.add(convertir(rs));
				
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
		return user;
	}

	@Override
	public void save(Users t) throws DAOException {
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(INSERT);
			stat.setLong(1, t.getUsername());
			stat.setString(2,  t.getEmail());
			stat.setString(3, t.getPass());
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
	public void update(Users t) throws DAOException {
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(UPDATE);
			stat.setLong(1, t.getUsername());
			stat.setString(2,  t.getEmail());
			stat.setString(3, t.getPass());
			if (stat.executeUpdate() == 0) {
				throw new DAOException("El alumno no se ha borrado.");
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
	public void delete(Users t) throws DAOException {
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(DELETE);
			stat.setLong(1, t.getUsername());
			if (stat.executeUpdate() == 0) {
				throw new DAOException("El alumno no se ha borrado.");
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
	
	private Users convertir(ResultSet rs) throws SQLException {
		Long username = rs.getLong("username");
		String email = rs.getString("email");
		String pass = rs.getString("pass");
		Users user = new Users(username, email, pass);
		
		return user;
	}
	
	public static void main(String[] args) throws SQLException, DAOException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/sopaletras","root","");
			DAOUsers dao = new UsersMySQLDAO(conn);

			List<Users> user = dao.getAll();
			for (Users t: user) {
				System.out.println(t.toString());
			}
			
		}finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

}

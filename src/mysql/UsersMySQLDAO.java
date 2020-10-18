package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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
	public Users get(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Users> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Users t) throws SQLException {
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(INSERT);
			stat.setLong(1, t.getUsername());
			stat.setString(2,  t.getEmail());
			stat.setString(3, t.getPass());
		}finally {
			
		}
		
	}

	@Override
	public void update(Users t) throws SQLException {

	}

	@Override
	public void delete(Users t) throws SQLException {

	}

}

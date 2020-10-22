package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DAOException;
import dao.DAOGame;
import modelo.Game;



//esta clase contiene todos los m�todos para el CRUD con la BD
public class GameMySQLDAO implements DAOGame {
	
	final String INSERT = "INSERT INTO game(idgame, score, users_username1) VALUES(?, ? ,?)";
	final String UPDATE = "UPDATE game SET idgame = ?, score = ? , users_username1 = ? WHERE idgame = ?";
	final String DELETE = "DELETE FROM game WHERE idgame = ?";
	final String GETALL = "SELECT idgame, score, users_username1 FROM game";
	final String GETONE = "SELECT idgame, score, users_username1 FROM game WHERE idgame = ?";
	
	private Connection conn;

	public GameMySQLDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Game get(String id) throws DAOException {
		PreparedStatement stat = null;
		ResultSet rs = null;
		Game t = null;
		try {
			stat = conn.prepareStatement(GETONE);
			stat.setString(1, id);
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
	public List<Game> getAll() throws DAOException {
		PreparedStatement stat = null;
		ResultSet rs = null;
		List<Game> game = new ArrayList<>();
		try {
			stat = conn.prepareStatement(GETALL);
			rs = stat.executeQuery();
			while (rs.next()) {
				game.add(convertir(rs));
				
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
		return game;
	}

	@Override
	public void save(Game t) throws DAOException {
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(INSERT);
			stat.setString(1, t.getIdGame());
			stat.setInt(2,  t.getScore());
			stat.setString(3, t.getUsers_username1());
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
	public void update(Game t) throws DAOException {
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(UPDATE);
			stat.setString(1, t.getIdGame());
			stat.setInt(2,  t.getScore());
			stat.setString(3, t.getUsers_username1());
			if (stat.executeUpdate() == 0) {
				throw new DAOException("El juego no se ha actualizado.");
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
	public void delete(Game t) throws DAOException {
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(DELETE);
			stat.setString(1, t.getIdGame());
			if (stat.executeUpdate() == 0) {
				throw new DAOException("El juego no se ha borrado.");
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
	
	private Game convertir(ResultSet rs) throws SQLException {
		String idgame = rs.getString("idgame");
		int score = rs.getInt("score");
		String  users_username1 = rs.getString("users_username1");
		Game game = new Game(idgame, score, users_username1);
		
		return game;
	}
	
	public static void main(String[] args) throws SQLException, DAOException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sopaletras","root","");
			DAOGame dao = new GameMySQLDAO(conn);

			List<Game> game = dao.getAll();
			for (Game t: game) {
				System.out.println(t.toString());
			}
			
		}finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

}

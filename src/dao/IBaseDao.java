package dao;

import java.util.List;
import java.sql.SQLException;
// clase plantilla
public interface IBaseDao<T, K> {
	
    T get(K id) throws SQLException;

    List<T> getAll() throws SQLException;

    void save(T t) throws SQLException;

    void update(T t) throws SQLException;

    void delete(T t) throws SQLException;
}
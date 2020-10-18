package dao;

import java.util.List;
import java.sql.SQLException;

public interface IBaseDao<T, K> {
	
    T get(K id) throws SQLException;

    List<T> getAll() throws SQLException;

    T save(T t) throws SQLException;

    T update(T t) throws SQLException;

    T delete(T t) throws SQLException;
}
package dao;

import java.util.List;
import java.sql.SQLException;

public interface IBaseDao<T> {
	
    T get(Long id) throws SQLException;

    List<T> getAll() throws SQLException;

    T save(T t) throws SQLException;

    T update(T t) throws SQLException;

    T delete(T t) throws SQLException;
}
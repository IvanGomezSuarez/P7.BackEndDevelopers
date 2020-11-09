package dao;

import java.util.List;
// clase plantilla
public interface IBaseDao<T, K> {
	
    T get(K id) throws DAOException;

    List<T> getAll() throws DAOException;

    void save(T t) throws DAOException;

    void update(T t) throws DAOException;

    void delete(T t) throws DAOException;

	T create() throws DAOException;
}
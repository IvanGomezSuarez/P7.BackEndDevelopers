package hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import dao.DAOException;
import dao.IBaseDao;

public class GenericDAOHibernate<T, K extends Serializable> implements IBaseDao<T, K> {



@Override
public List<T> getAll() throws DAOException {
	// TODO Auto-generated method stub
	return null;
}



@Override
public void update(T t) throws DAOException {
	// TODO Auto-generated method stub
	
}



@Override
public void delete(T t) throws DAOException {
	// TODO Auto-generated method stub
	
}



@Override
public T get(K id) throws DAOException {
	// TODO Auto-generated method stub
	return null;
}




@Override
public void save(T t) throws DAOException {
	// TODO Auto-generated method stub
	
}



@Override
public void create(T t) throws DAOException {
	// TODO Auto-generated method stub
	
}

}


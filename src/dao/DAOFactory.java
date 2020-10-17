package dao;

import java.sql.SQLException;

public abstract class DAOFactory {
	
    public abstract IBaseDao getDao(String entity) throws SQLException;


    public static DAOFactory getDaoFactory(String DaoType) {
        return new SQLDaoFactory();
        }
    }



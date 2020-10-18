package dao;

import java.sql.SQLException;
import java.util.List;

import modelo.Game;
//esta clase extiende los métodos genericos de IbaseDao, aplicable como plantilla para los DAO especificos

@SuppressWarnings("unused")
public interface DAOGame extends IBaseDao<Game, Long> {



}

package dao;

// forma centraliada de coger los DAO
public interface DAOManager {
	DAOGame getDAOGame();
	DAOLetters getDAOLetter();
	DAOUsers getDAOUser();
	DAOWords getDAOWord();
}

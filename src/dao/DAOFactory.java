package dao;

import dao.custom.impl.ProgramDAOImpl;
import dao.custom.impl.QueryDaoImpl;
import dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDAOFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentDAOImpl();
            case PROGRAM:
                return new ProgramDAOImpl();
            case STUDENT_PROGRAM:
                return new QueryDaoImpl();
            default:
                return null;
        }
    }

    public enum DAOTypes {
        STUDENT,PROGRAM,STUDENT_PROGRAM
    }
}

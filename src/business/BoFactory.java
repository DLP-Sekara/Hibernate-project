package business;

import business.impl.ProgramBOImpl;
import business.impl.QueryBoImpl;
import business.impl.StudentBOImpl;
import dao.custom.impl.QueryDaoImpl;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {
    }

    public static BoFactory getBOFactory() {
        if (boFactory == null) {
            boFactory = new BoFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BoTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentBOImpl();
            case PROGRAM:
                return new ProgramBOImpl();
            case STUDENT_PROGRAM:
                return new QueryBoImpl();
            default:
                return null;
        }
    }

    public enum BoTypes {
       STUDENT,PROGRAM,STUDENT_PROGRAM
    }
}

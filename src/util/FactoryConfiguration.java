package util;

import entity.Program;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration(){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("hibernate.properties"));
        }catch (IOException e) {
            e.printStackTrace();
        }
        Configuration configuration = new Configuration()
                .addProperties(properties)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Program.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance(){
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession(){

        return sessionFactory.openSession();
    }
}
//======================================================================================
//package util;
//
//import entity.Program;
//import entity.Student;
//import entity.student_program;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//
//public class FactoryConfiguration {
//    private static FactoryConfiguration factoryConfiguration;
//    private SessionFactory sessionFactory;
//
//    private FactoryConfiguration(){
//        Configuration configure = new Configuration().configure()
//                .addAnnotatedClass(Program.class)
//                .addAnnotatedClass(Student.class);
//        sessionFactory = configure.buildSessionFactory();
//    }
//
//    public static FactoryConfiguration getInstance(){
//        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
//    }
//
//    public Session getSession(){
//        return sessionFactory.openSession();
//    }
//
//
//}

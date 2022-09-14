package dao.custom.impl;

import dao.custom.StudentDAO;
import entity.Program;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean add(Student student) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(student);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.get(Student.class, s);

        session.delete(student);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student student) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        session.update(student);

        transaction.commit();
        return true;
    }

    @Override
    public boolean search(String s) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        boolean test;
        String hql = "FROM Student WHERE studentId = : student_Id";
        Query query = session.createQuery(hql);
        query.setParameter("student_Id", s);

        test = query.uniqueResult() != null;
        transaction.commit();
        session.close();
        return test;
    }

    @Override
    public ArrayList<Student> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        ArrayList<Student> temp = null;
        String hql = "From Student ORDER BY studentId ASC";
        Query query = session.createQuery(hql);

        temp = (ArrayList<Student>) query.list();
        System.out.println(temp);
        transaction.commit();
        session.close();
        return temp;
    }

    @Override
    public List<String> getProgramNames() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT programName FROM Program ";
        List<String> rst = session.createQuery(hql).list();

        transaction.commit();
        session.close();
        return rst;
    }

    @Override
    public boolean updateStudent(Student student, String text) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        boolean result = false;

        String hql = "UPDATE Student SET programs = :new_program WHERE studentId = :student_Id";
        Query query = session.createQuery(hql);
        query.setParameter("new_program", student.getPrograms());
        query.setParameter("student_Id", student.getStudentId());
        int rowCount = query.executeUpdate();
        result = rowCount > 0;
        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public boolean addNewProgram(Student student, String text) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
//        String hql = "INSERT INTO Student (programs)"+
//                "SELECT programs FROM Student WHERE studentId=:s_nic";
//        Query query = session.createQuery(hql);
//        query.setParameter("s_nic", text);
//        int i = query.executeUpdate();
//
////        String sql = "INSERT INTO Student(programs)values (programs)WHERE studentId=text";
////        NativeQuery sqlQuery = session.createSQLQuery(sql);
////        sqlQuery.addEntity(Student.class);
//        String hql = "UPDATE Student SET programs = :pro WHERE studentId = :student_Id";
//        Query query = session.createQuery(hql);
//        query.setParameter("pro", student.getPrograms());
//        query.setParameter("student_Id", text);
         //session.update(student);
        session.save(student);

        System.out.println(session.get(Student.class,text));
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Program> getLastProgram(String text) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT programs FROM Student WHERE studentId = : student_Id";
        Query query = session.createQuery(hql);
        query.setParameter("student_Id", text);
        Program temp = null;
        List<Program> result = query.list();

        for (Program p : result) {
            temp = new Program(p.getProgramId(), p.getProgramName(), p.getDuration(), p.getProgramFee(), p.getStudents());
        }
        transaction.commit();
        session.close();
        return result;
    }

//    @Override
//    public String getStudentname(String studentId) {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//
//        String test=null;
//        String hql = "SELECT studentName FROM Student WHERE studentId = : student_Id";
//        Query query = session.createQuery(hql);
////        query.setParameter("student_Id", studentId);
////        List<String>temp=query.list();
////        for(String result:temp){
////            test=result;
////        }
//        String test1= (String) query.uniqueResult();
//        transaction.commit();
//        session.close();
//        return test1;
//    }


}

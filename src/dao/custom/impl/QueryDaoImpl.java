package dao.custom.impl;

import dao.custom.QueryDao;
import entity.Program;
import entity.Student;
import entity.student_program;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.ArrayList;
import java.util.List;

public class QueryDaoImpl implements QueryDao {
    @Override
    public ArrayList<Student> getData() {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        ArrayList<Student> temp=new ArrayList<>();
        String hql="From Student ";
        Query query=session.createQuery(hql);

        temp= (ArrayList<Student>) query.list();
        System.out.println(temp);
        transaction.commit();
        session.close();
        return temp;
    }

    @Override
    public List<String> getProgramNames(String studentId) {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        List<Program> temp=new ArrayList<>();
        List<String> result=new ArrayList<>();

        Student student=session.get(Student.class,studentId);

        for(Program p:student.getPrograms()){
            result.add(p.getProgramName());
        }

        transaction.commit();
        session.close();
        return result;
    }

}

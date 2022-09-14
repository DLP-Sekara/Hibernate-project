package dao.custom.impl;

import dao.custom.ProgramDAO;
import dto.programDto;
import entity.Program;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramDAOImpl implements ProgramDAO {
    @Override
    public boolean add(Program program) throws SQLException, ClassNotFoundException, ClassNotFoundException {

        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        session.save(program);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Program program = session.get(Program.class, s);

        session.delete(program);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Program program) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean search(String s) throws SQLException, ClassNotFoundException {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

       boolean test;
        String hql = "FROM Program WHERE programId = : program_Id";
        Query query = session.createQuery(hql);
        query.setParameter("program_Id", s);

        if (query.uniqueResult()!=null){
            test=true;
        }else{
            test=false;
        }
        transaction.commit();
        session.close();
        return test;
    }

    @Override
    public ArrayList<Program> getAll() throws SQLException, ClassNotFoundException {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        ArrayList<Program>temp=null;
        String hql="From Program";
        Query query=session.createQuery(hql);

        temp= (ArrayList<Program>) query.list();
        System.out.println(temp);
        transaction.commit();
        session.close();
        return temp;
    }

    @Override
    public Program getProgramDetails(String tempProgramName) {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        String hql = "FROM Program WHERE programName = : program_Name";
        Query query = session.createQuery(hql);
        query.setParameter("program_Name", tempProgramName);
         Program temp=null;
        List<Program>result=query.list();

        for(Program p:result){
            temp=new Program(p.getProgramId(),p.getProgramName(),p.getDuration(),p.getProgramFee(),p.getStudents());
        }
        transaction.commit();
        session.close();
        return temp;
    }

    @Override
    public List<String> getProgramName(String programId) {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        ArrayList<String>temp=null;
        String hql="SELECT programName From Program where programId=:program_Id";
        Query query=session.createQuery(hql);
        query.setParameter("program_Id", programId);

        temp= (ArrayList<String>) query.list();
        System.out.println(temp);
        transaction.commit();
        session.close();
        return temp;
    }
}

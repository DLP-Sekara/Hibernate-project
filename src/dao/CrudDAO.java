package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T,ID>extends SuperDAO{
    boolean add(T t) throws SQLException, ClassNotFoundException, java.lang.ClassNotFoundException;

    boolean delete(ID id) throws SQLException, ClassNotFoundException;

    boolean update(T t) throws SQLException, ClassNotFoundException;

    boolean search(ID id) throws SQLException, ClassNotFoundException;

    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
}

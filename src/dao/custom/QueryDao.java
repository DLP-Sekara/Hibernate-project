package dao.custom;

import dao.SuperDAO;
import entity.Student;

import java.util.ArrayList;
import java.util.List;

public interface QueryDao extends SuperDAO {

    ArrayList<Student> getData();

    List<String> getProgramNames(String studentId);
}

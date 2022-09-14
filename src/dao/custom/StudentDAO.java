package dao.custom;

import dao.CrudDAO;
import entity.Program;
import entity.Student;

import java.util.List;

public interface StudentDAO extends CrudDAO<Student, String> {
    List<String> getProgramNames();

    boolean updateStudent(Student student, String text);

    boolean addNewProgram(Student student, String text);

    List<Program> getLastProgram(String text);

    //String getStudentname(String studentId);
}

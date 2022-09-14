package business;

import dto.programDto;
import dto.studentDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StudentBO extends SuperBO {
    List<String> getProgramNames();

    boolean addStudent(studentDto student) throws SQLException, ClassNotFoundException;

    ArrayList<studentDto> getAllData() throws SQLException, ClassNotFoundException;

    boolean checkStudent(String text) throws SQLException, ClassNotFoundException;

    boolean deleteStudent(String text) throws SQLException, ClassNotFoundException;

    boolean updateStudent(studentDto s1, String text) throws SQLException, ClassNotFoundException;

    boolean addNewProgram(studentDto programs, String text);

    List<programDto> getLastProgram(String text);




//    boolean addNewProgram(List<programDto> programs, String text);
}

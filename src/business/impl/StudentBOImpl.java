package business.impl;

import business.StudentBO;
import dao.DAOFactory;
import dao.custom.impl.StudentDAOImpl;
import dto.programDto;
import dto.studentDto;
import entity.Program;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    StudentDAOImpl studentDAO = (StudentDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public List<String> getProgramNames() {
        return studentDAO.getProgramNames();
    }

    @Override
    public boolean addStudent(studentDto st) throws SQLException, ClassNotFoundException {
        List<Program> temp = new ArrayList<>();
        for (programDto p : st.getPrograms()) {
            temp.add(new Program(p.getProgramId(), p.getProgramName(), p.getDuration(), p.getProgramFee(), null));

        }

        return studentDAO.add(new Student(st.getStudentId(), st.getStudentName(), st.getAddress(), st.getProgramName(), temp));
    }

    @Override
    public ArrayList<studentDto> getAllData() throws SQLException, ClassNotFoundException {
        ArrayList<studentDto> sDto = new ArrayList<>();
        ArrayList<Student> temp = studentDAO.getAll();

        for (Student s : temp) {
            sDto.add(new studentDto(s.getStudentId(), s.getStudentName(), s.getAddress(), s.getProgramName(), null));
        }
        return sDto;
    }

    @Override
    public boolean checkStudent(String text) throws SQLException, ClassNotFoundException {
        return studentDAO.search(text);
    }

    @Override
    public boolean deleteStudent(String text) throws SQLException, ClassNotFoundException {
        return studentDAO.delete(text);
    }

    @Override
    public boolean updateStudent(studentDto s1, String text) throws SQLException, ClassNotFoundException {
        List<Program> temp = new ArrayList<>();
        for (programDto p : s1.getPrograms()) {
            temp.add(new Program(p.getProgramId(), p.getProgramName(), p.getDuration(), p.getProgramFee(), null));

        }
        return studentDAO.update(new Student(s1.getStudentId(), s1.getStudentName(), s1.getAddress(), s1.getProgramName(), temp));
    }

    @Override
    public boolean addNewProgram(studentDto studentDto, String text) {
//        List<Program> temp = new ArrayList<>();
//        for (programDto p : programs) {
//            temp.add(new Program(p.getProgramId(), p.getProgramName(), p.getDuration(), p.getProgramFee(), null));
//
//        }
        // System.out.println(programs);
        List<Program> temp = new ArrayList<>();
        for (programDto p : studentDto.getPrograms()) {
            temp.add(new Program(p.getProgramId(), p.getProgramName(), p.getDuration(), p.getProgramFee(), null));

        }
        Student s = new Student(studentDto.getStudentId(), studentDto.getStudentName(), studentDto.getAddress(), studentDto.getProgramName(), temp);
        //System.out.println(programs);
        return studentDAO.addNewProgram(s, text);
    }

    @Override
    public List<programDto> getLastProgram(String text) {
        List<Program> p = studentDAO.getLastProgram(text);
        List<programDto> temp = new ArrayList<>();
        for (Program s : p) {
            temp.add(new programDto(s.getProgramId(), s.getProgramName(), s.getDuration(), s.getProgramFee()));
        }
        //programDto temp=new programDto(p.getProgramId(),p.getProgramName(),p.getDuration(),p.getProgramFee());
        return temp;
    }




}

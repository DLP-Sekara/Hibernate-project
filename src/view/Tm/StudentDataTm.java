package view.Tm;

import dto.programDto;
import entity.Program;

import java.util.List;

public class StudentDataTm {
    private String studentId;
    private String studentName;
    private List<String> programs;

    public StudentDataTm() {
    }

    public StudentDataTm(String studentId, String studentName, List<String> programs) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.programs = programs;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<String> getPrograms() {
        return programs;
    }

    public void setPrograms(List<String> programs) {
        this.programs = programs;
    }

    @Override
    public String toString() {
        return "StudentDataTm{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", programs=" + programs +
                '}';
    }
}

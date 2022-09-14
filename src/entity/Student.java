package entity;

import dto.programDto;

import javax.persistence.*;
import java.util.List;
@Entity
public class Student {
    @Id
    private String studentId;
    private String studentName;
    private String address;
    private String programName;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Program>programs;

    public Student() {
    }

    public Student(String studentId, String studentName, String address, String programName, List<Program> programs) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.address = address;
        this.programName = programName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", address='" + address + '\'' +
                ", programName='" + programName + '\'' +
                ", programs=" + programs +
                '}';
    }
}

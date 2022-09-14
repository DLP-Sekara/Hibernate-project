package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class student_program {
    @ManyToOne
    private String studentId;
    @ManyToOne
    private String programId;

    public student_program() {
    }

    public student_program(String studentId, String programId) {
        this.studentId = studentId;
        this.programId = programId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    @Override
    public String toString() {
        return "student_program{" +
                "studentId='" + studentId + '\'' +
                ", programId='" + programId + '\'' +
                '}';
    }
}

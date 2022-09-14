package dto;

import javax.persistence.ManyToOne;

public class student_programDto {
    private String studentId;
    private String programId;

    public student_programDto() {
    }

    public student_programDto(String studentId, String programId) {
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
        return "programDetailsDto{" +
                "studentId='" + studentId + '\'' +
                ", programId='" + programId + '\'' +
                '}';
    }
}

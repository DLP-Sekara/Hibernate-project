package dto;

import entity.Program;

import java.util.List;

public class studentDto {
    private String studentId;
    private String studentName;
    private String address;
    private String programName;
    private List<programDto> programs;

    public studentDto(String studentId, String studentName, String address, String programName, List<programDto> programs) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.address = address;
        this.programName = programName;
        this.programs = programs;
    }

    public studentDto() {
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

    public List<programDto> getPrograms() {
        return programs;
    }

    public void setPrograms(List<programDto> programs) {
        this.programs = programs;
    }

    @Override
    public String toString() {
        return "studentDto{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", address='" + address + '\'' +
                ", programName='" + programName + '\'' +
                ", programs=" + programs +
                '}';
    }
}

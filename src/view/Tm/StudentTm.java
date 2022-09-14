package view.Tm;

public class StudentTm {
    private String studentId;
    private String studentName;
    private String address;
    private String programName;

    public StudentTm(String studentId, String studentName, String address, String programName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.address = address;
        this.programName = programName;
    }

    public StudentTm() {
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

    @Override
    public String toString() {
        return "StudentTm{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", address='" + address + '\'' +
                ", programName='" + programName + '\'' +
                '}';
    }
}

package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;
@Entity
public class Program {
    @Id
    private String programId;
    private String programName;
    private String Duration;
    private String programFee;
    @ManyToMany(mappedBy = "programs")
    private List<Student>students;

    public Program() {
    }

    public Program(String programId, String programName, String duration, String programFee, List<Student> students) {
        this.programId = programId;
        this.programName = programName;
        Duration = duration;
        this.programFee = programFee;
        this.students = students;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getProgramFee() {
        return programFee;
    }

    public void setProgramFee(String programFee) {
        this.programFee = programFee;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

}

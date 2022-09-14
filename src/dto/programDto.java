package dto;

import entity.Student;

import java.util.List;

public class programDto {
    private String programId;
    private String programName;
    private String Duration;
    private String programFee;

    public programDto(String programId, String programName, String duration, String programFee) {
        this.programId = programId;
        this.programName = programName;
        Duration = duration;
        this.programFee = programFee;
    }

    public programDto() {
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

    @Override
    public String toString() {
        return "programDto{" +
                "programId='" + programId + '\'' +
                ", programName='" + programName + '\'' +
                ", Duration='" + Duration + '\'' +
                ", programFee='" + programFee + '\'' +
                '}';
    }
}

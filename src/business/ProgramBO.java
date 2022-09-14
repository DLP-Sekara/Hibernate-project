package business;

import dto.programDto;
import entity.Program;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ProgramBO extends SuperBO{
    public boolean addProgram(programDto pDto) throws Exception;

    boolean checkProgram(String text) throws SQLException, ClassNotFoundException;

    ArrayList<programDto> getAllData() throws SQLException, ClassNotFoundException;

    boolean deleteProgram(String programId) throws SQLException, ClassNotFoundException;

    programDto getProgramDetails(String tempProgramName);

    List<String> getProgramName(String programId);
}

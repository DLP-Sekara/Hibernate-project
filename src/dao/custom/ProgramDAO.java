package dao.custom;

import dao.CrudDAO;
import dto.programDto;
import entity.Program;

import java.util.List;

public interface ProgramDAO extends CrudDAO<Program,String> {
    Program getProgramDetails(String tempProgramName);

    List<String> getProgramName(String programId);
}

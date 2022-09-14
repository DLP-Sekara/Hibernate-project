package business.impl;

import business.ProgramBO;
import dao.DAOFactory;
import dao.custom.ProgramDAO;
import dao.custom.impl.ProgramDAOImpl;
import dto.programDto;
import dto.studentDto;
import entity.Program;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramBOImpl implements ProgramBO {
    ProgramDAOImpl programDAO= (ProgramDAOImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);

    @Override
    public boolean addProgram(programDto pDto) throws Exception{
        return programDAO.add(new Program(pDto.getProgramId(),pDto.getProgramName(),pDto.getDuration(),pDto.getProgramFee(),null));
    }

    @Override
    public boolean checkProgram(String text) throws SQLException, ClassNotFoundException {
        return programDAO.search(text);
    }

    @Override
    public ArrayList<programDto> getAllData() throws SQLException, ClassNotFoundException {
        ArrayList<programDto>pDto=new ArrayList<>();
        ArrayList<Program> temp = programDAO.getAll();

        for (Program p:temp) {
            pDto.add(new programDto(p.getProgramId(),p.getProgramName(),p.getDuration(),p.getProgramFee()));
        }
        return pDto;
    }

    @Override
    public boolean deleteProgram(String programId) throws SQLException, ClassNotFoundException {
        return programDAO.delete(programId);
    }

    @Override
    public programDto getProgramDetails(String tempProgramName) {
        Program temp= programDAO.getProgramDetails(tempProgramName);
        return new programDto(temp.getProgramId(),temp.getProgramName(),temp.getDuration(),temp.getProgramFee());
    }

    @Override
    public List<String> getProgramName(String programId) {
        return programDAO.getProgramName(programId);
    }
}

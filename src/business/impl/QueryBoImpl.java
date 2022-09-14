package business.impl;

import business.QueryBo;
import dao.DAOFactory;
import dao.custom.impl.QueryDaoImpl;
import dto.programDto;
import dto.studentDto;
import entity.Program;
import entity.Student;

import java.util.ArrayList;
import java.util.List;

public class QueryBoImpl implements QueryBo {
    QueryDaoImpl queryDao= (QueryDaoImpl) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT_PROGRAM);

    @Override
    public ArrayList<studentDto> getData() {
        ArrayList<studentDto> sDto = new ArrayList<>();
        ArrayList<Student> temp = queryDao.getData();
        List<programDto>result=new ArrayList<>();
        for (Student s : temp) {
            for(Program p:s.getPrograms()){
                result.add(new programDto(p.getProgramId(),p.getProgramName(),p.getDuration(),p.getProgramFee()));
            }
            sDto.add(new studentDto(s.getStudentId(), s.getStudentName(), s.getAddress(), s.getProgramName(), result));
        }
        return sDto;
    }

    @Override
    public List<String> getProgramsNames(String studentId) {
        return queryDao.getProgramNames(studentId);
    }
}

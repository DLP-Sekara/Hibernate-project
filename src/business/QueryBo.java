package business;

import dto.studentDto;

import java.util.ArrayList;
import java.util.List;

public interface QueryBo extends SuperBO{

    ArrayList<studentDto> getData();

    List<String> getProgramsNames(String studentId);
}

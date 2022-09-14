package controller;

import business.BoFactory;
import business.ProgramBO;
import business.QueryBo;
import business.StudentBO;
import com.jfoenix.controls.JFXComboBox;
import dao.custom.QueryDao;
import dto.programDto;
import dto.studentDto;
import dto.student_programDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.Tm.StudentDataTm;
import view.Tm.StudentTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {
    private final StudentBO studentBO = (StudentBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.STUDENT);
    private final ProgramBO programBO = (ProgramBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.PROGRAM);
    private final QueryBo queryBo = (QueryBo) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.STUDENT_PROGRAM);
    public AnchorPane DashboardPage;
    public TableView<StudentDataTm> tblProgramDetails;
    public TableColumn colStudentId;
    public TableColumn colStudentName;
    public TableColumn colProgramName;
    public TableColumn colProgramDuration;
    public JFXComboBox comboSelectStudent;
    ObservableList<StudentDataTm> oblist = FXCollections.observableArrayList();
    List<String> programNames = new ArrayList<>();
    List<programDto> programs = new ArrayList<>();
    //ArrayList<StudentDataTm> temp = new ArrayList<>();
    ArrayList<studentDto> sTemp = new ArrayList<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("programs"));
        setDataToTable(getData());
    }

    private void setDataToTable(ArrayList<studentDto> data) {
        for (studentDto result : data) {
            oblist.add(new StudentDataTm(result.getStudentId(), result.getStudentName(),getProgramNames(result.getStudentId())));
            tblProgramDetails.setItems(oblist);
        }

    }

    private List<String> getProgramNames(String studentId) {
        return queryBo.getProgramsNames(studentId);
    }

    private ArrayList<studentDto> getData() {
        return queryBo.getData();
    }

    public void OpenAddStudentForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AddNewStudentForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) DashboardPage.getScene().getWindow();
        window.setTitle("Add New Student");
        window.setScene(new Scene(load));
    }

    public void OpenAddProgramForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AddNewCourseForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) DashboardPage.getScene().getWindow();
        window.setTitle("Add New Student");
        window.setScene(new Scene(load));
    }


}

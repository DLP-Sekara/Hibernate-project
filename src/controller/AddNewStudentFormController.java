package controller;

import business.BoFactory;
import business.ProgramBO;
import business.StudentBO;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.programDto;
import dto.studentDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.Tm.StudentTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddNewStudentFormController implements Initializable {
    private final StudentBO studentBO = (StudentBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.STUDENT);
    private final ProgramBO programBO = (ProgramBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.PROGRAM);
    public AnchorPane AddStudentForm;
    public JFXTextField txtStudentNIC;
    public JFXTextField txtStudentName;
    public JFXTextField txtStudentAddress;
    public JFXComboBox comboSelectProgram;
    public TableView<StudentTm> tblStudent;
    public TableColumn colStudentId;
    public TableColumn colStudentName;
    public TableColumn colStudentAddress;
    public TableColumn colStudentProgram;
    public Button saveStudentOnAction;
    public Button saveStudentBtn;
    public Button deleteStudentButton;
    public Button updateStudent;
    public Button AddNewProgram;
    StudentTm cartSelectedRowForRemove = null;
    List<programDto> programs = new ArrayList<>();
    ObservableList<StudentTm> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> itemDes = studentBO.getProgramNames();
        comboSelectProgram.getItems().addAll(itemDes);

        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colStudentAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colStudentProgram.setCellValueFactory(new PropertyValueFactory<>("programName"));
        try {
            setAllStudentToTable(getAllStudents());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        saveStudentBtn.setDisable(true);
        deleteStudentButton.setDisable(true);
        updateStudent.setDisable(true);
        AddNewProgram.setDisable(true);
        comboSelectProgram.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (txtStudentNIC.getText() != null & txtStudentName.getText() != null & txtStudentAddress.getText() != null & comboSelectProgram.getSelectionModel().getSelectedItem() != null) {
                saveStudentBtn.setDisable(false);
            }
        });
        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                saveStudentBtn.setDisable(true);
                deleteStudentButton.setDisable(false);
                cartSelectedRowForRemove = newValue;
                txtStudentNIC.setText(newValue.getStudentId());
                txtStudentName.setText(newValue.getStudentName());
                txtStudentAddress.setText(newValue.getAddress());
                deleteStudentButton.setDisable(false);
                updateStudent.setDisable(false);
                AddNewProgram.setDisable(false);
            }
        });
    }

    private void setAllStudentToTable(ArrayList<studentDto> allStudents) {
        allStudents.forEach(e -> {
            oblist.add(new StudentTm(e.getStudentId(), e.getStudentName(), e.getAddress(), e.getProgramName()));
        });
        tblStudent.setItems(oblist);
    }

    private ArrayList<studentDto> getAllStudents() throws SQLException, ClassNotFoundException {
        return studentBO.getAllData();
    }

    public void BackToHome(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/DashboardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) AddStudentForm.getScene().getWindow();
        window.setTitle("SIPSEWANA");
        window.setScene(new Scene(load));
    }

    public void saveStudentOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        programs.clear();
        String tempProgramName = (String) comboSelectProgram.getSelectionModel().getSelectedItem();
        programDto programDetails = getProgramDetails(tempProgramName);
        programs.add(new programDto(programDetails.getProgramId(), programDetails.getProgramName(), programDetails.getDuration(), programDetails.getProgramFee()));

        if (checkStudent(txtStudentNIC.getText())) {
            new Alert(Alert.AlertType.WARNING, "Student Already").show();
        } else {
            studentDto student = new studentDto(txtStudentNIC.getText(), txtStudentName.getText(), txtStudentAddress.getText(), tempProgramName, programs);
            if (saveStudent(student)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student Saved.").show();
                tblStudent.getItems().clear();
                setAllStudentToTable(getAllStudents());
                txtStudentNIC.clear();
                txtStudentName.clear();
                txtStudentAddress.clear();
                //comboSelectProgram.getItems().clear();
                comboSelectProgram.getSelectionModel().clearSelection();
                //comboSelectProgram.setValue(null);
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again.").show();
            }

        }
    }

    private boolean saveStudent(studentDto student) throws SQLException, ClassNotFoundException {
        return studentBO.addStudent(student);
    }

    private boolean checkStudent(String text) throws SQLException, ClassNotFoundException {
        return studentBO.checkStudent(text);
    }

    private programDto getProgramDetails(String tempProgramName) {
        return programBO.getProgramDetails(tempProgramName);
    }

    public void DeleteStudent(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (delete(txtStudentNIC.getText())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Deleted");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                oblist.remove(cartSelectedRowForRemove);
                System.out.println("OK chosen");
            }

        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again.").show();
        }
    }

    private boolean delete(String text) throws SQLException, ClassNotFoundException {
        return studentBO.deleteStudent(text);
    }

    public void UpdateStudentOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String tempProgramName = (String) comboSelectProgram.getSelectionModel().getSelectedItem();
        programDto programDetails = getProgramDetails(tempProgramName);
        programs.add(new programDto(programDetails.getProgramId(), programDetails.getProgramName(), programDetails.getDuration(), programDetails.getProgramFee()));
        studentDto s1 = new studentDto(txtStudentNIC.getText(),txtStudentName.getText(),txtStudentAddress.getText(),tempProgramName,programs);
        if (updateStudent(s1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
            tblStudent.getItems().clear();
            setAllStudentToTable(getAllStudents());
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    private boolean updateStudent(studentDto s1) throws SQLException, ClassNotFoundException {
        return studentBO.updateStudent(s1,txtStudentNIC.getText());
    }

    public void addNewProgramOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        programs.clear();
        List<programDto> lastProgram = getLastProgram(txtStudentNIC.getText());
        System.out.println(lastProgram);

        for (programDto p:lastProgram) {
            programs.add(new programDto(p.getProgramId(),p.getProgramName(),p.getDuration(),p.getProgramFee()));
        }
        String tempProgramName = (String) comboSelectProgram.getSelectionModel().getSelectedItem();
        programDto programDetails = getProgramDetails(tempProgramName);

        programs.add(new programDto(programDetails.getProgramId(), programDetails.getProgramName(), programDetails.getDuration(), programDetails.getProgramFee()));
        studentDto s1 = new studentDto(txtStudentNIC.getText(),txtStudentName.getText(),txtStudentAddress.getText(),tempProgramName,programs);

        if (delete(txtStudentNIC.getText())) {
            cartSelectedRowForRemove=null;
            if (addnewProgram(s1, txtStudentNIC.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
                tblStudent.getItems().clear();
                setAllStudentToTable(getAllStudents());
                txtStudentNIC.clear();
                txtStudentName.clear();
                txtStudentAddress.clear();
                comboSelectProgram.getSelectionModel().clearSelection();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        }
    }

    private List<programDto> getLastProgram(String text) {
        return studentBO.getLastProgram(text);
    }

    private boolean addnewProgram(studentDto studentDto, String text) {
        System.out.println(studentDto);
        return studentBO.addNewProgram(studentDto,text);
    }
}

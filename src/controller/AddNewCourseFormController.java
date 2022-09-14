package controller;

import business.BoFactory;
import business.ProgramBO;
import com.jfoenix.controls.JFXTextField;
import dto.programDto;
import entity.Program;
import entity.Student;
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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddNewCourseFormController implements Initializable {
    private final ProgramBO programBO = (ProgramBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.PROGRAM);
    public AnchorPane AddProgramPage;
    public JFXTextField txtProgramId;
    public JFXTextField txtProgramName;
    public TableView<programDto> tblAllProgram;
    public TableColumn colProgramId;
    public TableColumn colProgramName;
    public TableColumn colProgramDuration;
    public TableColumn colProgramFee;
    public JFXTextField txtProgramDuration;
    public JFXTextField txtProgramFee;
    public Button deleteButton;
    programDto cartSelectedRowForRemove = null;
    int tempSelectedValue=-1;
    ObservableList<programDto> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colProgramDuration.setCellValueFactory(new PropertyValueFactory<>("Duration"));
        colProgramFee.setCellValueFactory(new PropertyValueFactory<>("programFee"));
        deleteButton.setDisable(true);
        try {
            setProgramDataToTable(getAllData());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        tblAllProgram.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                deleteButton.setDisable(false);
                cartSelectedRowForRemove = newValue;
                txtProgramId.setText(newValue.getProgramId());
                txtProgramName.setText(newValue.getProgramName());
                txtProgramDuration.setText(newValue.getDuration());
                txtProgramFee.setText(newValue.getProgramFee());
            }
        });
    }

    private void setProgramDataToTable(ArrayList<programDto> allData) {
        allData.forEach(e -> {
            oblist.add(new programDto(e.getProgramId(), e.getProgramName(), e.getDuration(), e.getProgramFee()));
        });
        tblAllProgram.setItems(oblist);
    }

    private ArrayList<programDto> getAllData() throws SQLException, ClassNotFoundException {
        return programBO.getAllData();
    }

    public void BackToHome(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("../view/DashboardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) AddProgramPage.getScene().getWindow();
        window.setTitle("SIPSEWANA");
        window.setScene(new Scene(load));
    }

    public void saveprogramOnAction(ActionEvent actionEvent) throws Exception {
        if (checkProgram(txtProgramId.getText())) {
            new Alert(Alert.AlertType.WARNING, "Program Already Exists.").show();
        } else {
            programDto program = new programDto(txtProgramId.getText(), txtProgramName.getText(), txtProgramDuration.getText(), txtProgramFee.getText());
            if (saveProgram(program)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Program Saved.").show();
                tblAllProgram.getItems().clear();
                setProgramDataToTable(getAllData());
                txtProgramId.clear();
                txtProgramName.clear();
                txtProgramFee.clear();
                txtProgramDuration.clear();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again.").show();
            }

        }
    }

    private boolean checkProgram(String text) throws SQLException, ClassNotFoundException {
        return programBO.checkProgram(text);
    }

    private boolean saveProgram(programDto program) throws Exception {
        return programBO.addProgram(program);
    }

    public void deleteprogram(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

            if (delete(txtProgramId.getText())) {
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

    private boolean delete(String programId) throws SQLException, ClassNotFoundException {
        return programBO.deleteProgram(programId);
    }
}

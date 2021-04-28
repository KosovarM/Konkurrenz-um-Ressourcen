package de.lingen.modsim.KonkurrenzUmRessourcen.Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * The Controller Class from DeletePop.fxml.
 */
public class DelPopCont implements Initializable
{

    public TextField tfdel;

    private ObservableList<ResultsProject> dataR;

    @FXML
    private TableView<ResultsProject> tbDataR;

    @FXML
    private TableColumn<ResultsProject, String> Project;

    /**
     * Fetching the Project Data from our Database.
     * Writes the Data into the TableView.
     * @throws SQLException
     */
    public void loadProjects() throws SQLException
    {
        DatabaseConnector connectionClass = new DatabaseConnector();
        Connection connection = connectionClass.getConnection();

        dataR = FXCollections.observableArrayList();

        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM konkurrenz_um_ressourcen.projekte");

        while (rs.next())
        {
            dataR.add(new ResultsProject(rs.getString(1)));
        }

        Project.setCellValueFactory(new PropertyValueFactory<>("Project"));

        tbDataR.setItems(null);
        tbDataR.setItems(dataR);
    }

    /**
     * Deletes every Data from a specific Project from our Database.
     * Shows an Alert Window.
     * @param e
     * @throws SQLException
     */
    public void deleteProject(ActionEvent e) throws SQLException
    {
        Connection connection = DatabaseConnector.getConnection();

        String sql = "DROP TABLE " + tfdel.getText();
        String sql2 = "DELETE FROM konkurrenz_um_ressourcen.parameter_a WHERE Name ='"+ tfdel.getText()+"'";
        String sql3 = "DELETE FROM konkurrenz_um_ressourcen.parameter_b WHERE Name ='"+ tfdel.getText()+"'";
        String sql4 = "DELETE FROM konkurrenz_um_ressourcen.projekte WHERE Projekte ='"+ tfdel.getText()+"'";

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        statement.executeUpdate(sql2);
        statement.executeUpdate(sql3);
        statement.executeUpdate(sql4);

        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

        window.close();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Project deleted");
        alert.setHeaderText("Projekt gelöscht");
        alert.setContentText("Sie haben das Projekt erfolgreich gelöscht!");

        alert.showAndWait();

    }

    /**
     * Initialize the Method loadProjects().
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadProjects();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

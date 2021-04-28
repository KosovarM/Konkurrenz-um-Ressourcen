package de.lingen.modsim.KonkurrenzUmRessourcen.Database;


import de.lingen.modsim.KonkurrenzUmRessourcen.Charts.LineChartController;
import de.lingen.modsim.KonkurrenzUmRessourcen.Charts.ScatterChartController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * The Controller Class from PopupProjects.fxml.
 */
public class PopupController implements Initializable
{

    private ObservableList<ResultsProject> dataR;

    @FXML
    private TableView<ResultsProject> tbDataR;

    @FXML
    private TableColumn<ResultsProject, String> Project;

    /**
     * Close Button to close the PopupProjects.fxml.
     * @param e
     */
    public void closePopUp(ActionEvent e)
    {
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

        TableController.s1 = getprojectname();
        LineChartController.s9 = getprojectname();
        ScatterChartController.s9 = getprojectname();

        window.close();
    }

    /**
     * Fetching the Projekt Data from our Database.
     * Writes the Data in our TableView.
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
     * Gets the Project Name from the TableView.
     * @return
     */
    public String getprojectname()
    {
        ResultsProject selected = tbDataR.getSelectionModel().getSelectedItem();

        if(selected==null)
        {
            return "";
        }
        else
            {
        return selected.getName();
            }
    }

    /**
     * Initializing the methods at getprojectname() and loadProjects().
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getprojectname();
        try
        {
            loadProjects();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
package de.lingen.modsim.KonkurrenzUmRessourcen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller Class from MainChart.fxml.
 */
public class Controller
{
    /**
     * Stops to run the Programm.
     * @param e
     */
    public void closeButton(ActionEvent e)
    {
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.close();
    }

    /**
     * Setting the Scene on LineChart.fxml
     * @param e
     * @throws IOException
     */
    @FXML
    private void goLineChart(ActionEvent e) throws IOException
    {
        Parent lineChartview = FXMLLoader.load(getClass().getResource("Charts/LineChart.fxml"));
        Scene lineChartScene = new Scene(lineChartview);

        Stage window =(Stage) ((Node) e.getSource()).getScene().getWindow();

        window.setScene(lineChartScene);
        window.show();
    }

    /**
     * Setting the Scene on ScatterChart.fxml
     * @param e
     * @throws IOException
     */
    @FXML
    private void goScatterChart(ActionEvent e) throws IOException
    {
        Parent scatterChartview = FXMLLoader.load(getClass().getResource("Charts/ScatterChart.fxml"));
        Scene scatterChartScene = new Scene(scatterChartview);

        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

        window.setScene(scatterChartScene);
        window.show();
    }

    /**
     * Setting the Scene on Table.fxml.
     * @param e
     * @throws IOException
     */
    @FXML
    private void goDatabase(ActionEvent e) throws IOException
    {
        Parent databaseChartview = FXMLLoader.load(getClass().getResource("Database/Table.fxml"));
        Scene  databaseChartScene = new Scene(databaseChartview);

        Stage window =(Stage)  ((Node) e.getSource()).getScene().getWindow();

        window.setScene(databaseChartScene);
        window.show();
    }
}

package de.lingen.modsim.KonkurrenzUmRessourcen.Database;


import de.lingen.modsim.KonkurrenzUmRessourcen.Charts.Ergebnisse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * The Controller Class of Table.fxml.
 */
public class TableController implements Initializable
{

    public TextField tf1;
    public TextField tf2;
    public TextField tf3;
    public static String s1 = "";

    private ObservableList<Ergebnisse> dataE;
    private ObservableList<TableModelA> dataA;
    private ObservableList<TableModelB> dataB;

    @FXML
    private TableView<Ergebnisse> tbDataE;

    @FXML
    private TableColumn<Ergebnisse, String> Month;

    @FXML
    private TableColumn<Ergebnisse, String> PopA;

    @FXML
    private TableColumn<Ergebnisse, String> PopB;

    @FXML
    private TableColumn<Ergebnisse, String> Konk;

    @FXML
    private TableView<TableModelA> tbDataA;

    @FXML
    private TableView<TableModelB> tbDataB;

    @FXML
    private TableColumn<TableModelB, String> NameB;

    @FXML
    private TableColumn<TableModelA, String> NameA;

    @FXML
    private TableColumn<TableModelA, Double> PopulationA;

    @FXML
    private TableColumn<TableModelA, Double> WachstumsRA;

    @FXML
    private TableColumn<TableModelA, Double> TragfähigkeitA;

    @FXML
    private TableColumn<TableModelA, Double> KonkEffektA;

    @FXML
    private TableColumn<TableModelB, Double> PopulationB;

    @FXML
    private TableColumn<TableModelB, Double> WachstumsRB;

    @FXML
    private TableColumn<TableModelB, Double> TragfähigkeitB;

    @FXML
    private TableColumn<TableModelB, Double> KonkEffektB;

    /**
     * Change the Scene from Table.fxml to LineChart.fxml.
     * @param e
     * @throws IOException
     */
    @FXML
    public void goLineChart(ActionEvent e) throws IOException
    {
        Parent lineChartview = FXMLLoader.load(getClass().getResource("../Charts/LineChart.fxml"));
        Scene lineChartScene = new Scene(lineChartview);

        Stage window =(Stage) ((Node) e.getSource()).getScene().getWindow();

        window.setScene(lineChartScene);
        window.show();
    }

    /**
     * Change the Scene from Table.fxml to ScatterChart.fxml.
     * @param e
     * @throws IOException
     */
    @FXML
    public void goScatterChart(ActionEvent e) throws IOException
    {
        Parent scatterChartview = FXMLLoader.load(getClass().getResource("../Charts/ScatterChart.fxml"));
        Scene scatterChartScene = new Scene(scatterChartview);

        Stage window =(Stage) ((Node) e.getSource()).getScene().getWindow();

        window.setScene(scatterChartScene);
        window.show();
    }

    /**
     * Change the Scene from Table.fxml to MainChart.fxml.
     * @param e
     * @throws IOException
     */
    @FXML
    public void goMainChart(ActionEvent e) throws IOException
    {
        Parent mainChartView = FXMLLoader.load(getClass().getResource("../Charts/MainChart.fxml"));
        Scene mainChartScene = new Scene(mainChartView);

        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

        window.setScene(mainChartScene);
        window.show();
    }

    /**
     * Fetching our FirstValues from our Database and writes it into the TableView.
     */
    @FXML
    private void loadData()
    {
        try
        {
            DatabaseConnector connectionClass = new DatabaseConnector();
            Connection connection = connectionClass.getConnection();

            dataA = FXCollections.observableArrayList();
            dataB = FXCollections.observableArrayList();

            ResultSet rsA = connection.createStatement().executeQuery("SELECT * FROM parameter_a");
            ResultSet rsB = connection.createStatement().executeQuery("SELECT * FROM parameter_b");

            while (rsA.next())
            {
                dataA.add(new TableModelA(rsA.getString(1),
                                          rsA.getDouble(2),
                                          rsA.getDouble(3),
                                          rsA.getDouble(4),
                                          rsA.getDouble(5)));

            }

            while(rsB.next())
            {
                dataB.add(new TableModelB(rsB.getString(1),
                                          rsB.getDouble(2),
                                          rsB.getDouble(3),
                                          rsB.getDouble(4),
                                          rsB.getDouble(5)));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        NameA.setCellValueFactory(new PropertyValueFactory<>("Name"));
        PopulationA.setCellValueFactory(new PropertyValueFactory<>("PopulationA"));
        WachstumsRA.setCellValueFactory(new PropertyValueFactory<>("WachstumsRA"));
        TragfähigkeitA.setCellValueFactory(new PropertyValueFactory<>("TragfähigkeitA"));
        KonkEffektA.setCellValueFactory(new PropertyValueFactory<>("KonkEffektA"));

        NameB.setCellValueFactory(new PropertyValueFactory<>("Name"));
        PopulationB.setCellValueFactory(new PropertyValueFactory<>("PopulationB"));
        WachstumsRB.setCellValueFactory(new PropertyValueFactory<>("WachstumsRB"));
        TragfähigkeitB.setCellValueFactory(new PropertyValueFactory<>("TragfähigkeitB"));
        KonkEffektB.setCellValueFactory(new PropertyValueFactory<>("KonkEffektB"));

        tbDataA.setItems(null);
        tbDataB.setItems(null);
        tbDataA.setItems(dataA);
        tbDataB.setItems(dataB);

        }

    /**
     * Fetching the Results from our Database and writes it into a TableView.
     * @param e
     * @throws SQLException
     */
    public void getRes(ActionEvent e) throws SQLException
    {
        DatabaseConnector connector = new DatabaseConnector();
        Connection connection = DatabaseConnector.getConnection();

        if(s1=="")
        {
            s1 = tf1.getText();
        }

        dataE = FXCollections.observableArrayList();

        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM " + s1);

        while (resultSet.next())
        {
            dataE.add(new Ergebnisse(resultSet.getString(1),
                                     resultSet.getString(2),
                                     resultSet.getString(3),
                                     resultSet.getString(4)));
        }

        Month.setCellValueFactory(new PropertyValueFactory<>("Month"));
        PopA.setCellValueFactory(new PropertyValueFactory<>("PopA"));
        PopB.setCellValueFactory(new PropertyValueFactory<>("PopB"));
        Konk.setCellValueFactory(new PropertyValueFactory<>("Konk"));

        tbDataE.setItems(null);
        tbDataE.setItems(dataE);

        tf1.setText(s1);
    }

    /**
     * Shows the PopupProjects.fxml.
     * @param e
     * @throws IOException
     */
    public void showPopup(ActionEvent e) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PopupProjects.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    /**
     * Shows the DeletePop.fxml.
     * @param e
     * @throws IOException
     */
    public void deletePopup(ActionEvent e) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeletePop.fxml"));
        Parent parent = (Parent) fxmlLoader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    /**
     * Initializing the method loadData().
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        loadData();
    }
}
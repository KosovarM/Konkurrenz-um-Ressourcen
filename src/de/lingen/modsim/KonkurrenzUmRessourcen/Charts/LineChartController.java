package de.lingen.modsim.KonkurrenzUmRessourcen.Charts;

import de.lingen.modsim.KonkurrenzUmRessourcen.Database.DatabaseConnector;
import de.lingen.modsim.KonkurrenzUmRessourcen.Database.Table;
import de.lingen.modsim.KonkurrenzUmRessourcen.Database.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import static de.lingen.modsim.KonkurrenzUmRessourcen.Core.Konkurrenz.*;

/**
 * The Controller Class from the LineChart.fxml.
 */
public class LineChartController
{

    PreparedStatement pst1 = null, pst2 = null;

    @FXML
    private ObservableList<Ergebnisse> data;

    @FXML
    private TableView<Ergebnisse> eData;

    @FXML
    private TableColumn<Ergebnisse, String> Month;

    @FXML
    private TableColumn<Ergebnisse, String> PopA;

    @FXML
    private TableColumn<Ergebnisse, String> PopB;

    @FXML
    private TableColumn<Ergebnisse, String> Konk;

    public TextField tf1;
    public TextField tf2;
    public TextField tf3;
    public TextField tf4;
    public TextField tf5;
    public TextField tf6;
    public TextField tf7;
    public TextField tf8;
    public TextField tf9;
    public TextField tf10;

    public static String s9 = "";

    public TextField getTf1() {
        return tf1;
    }

    public TextField getTf2() {
        return tf2;
    }

    public TextField getTf3() {
        return tf3;
    }

    public TextField getTf4() {
        return tf4;
    }

    public TextField getTf5() {
        return tf5;
    }

    public TextField getTf6()
    {
        return tf6;
    }

    public TextField getTf7() {
        return tf7;
    }

    public TextField getTf8() {
        return tf8;
    }

    /**
     * Gets all Values from the Textfields.
     */
    public void getValues()
    {
        String tf1t = getTf1().getText();
        String tf2t = getTf2().getText();
        String tf3t = getTf3().getText();
        String tf4t = getTf4().getText();
        String tf5t = getTf5().getText();
        String tf6t = getTf6().getText();
        String tf7t = getTf7().getText();
        String tf8t = getTf8().getText();

        Konkurrenzkampf(tf1t, tf2t, tf3t,tf4t, tf5t, tf6t, tf7t, tf8t);
    }

    @FXML
    LineChart<Number, Number> lineChart;

    /**
     * Setting the Scene to MainChart.fxml.
     * @param e
     * @throws IOException
     */
    @FXML
    private void goMainChart(ActionEvent e) throws IOException
    {
        Parent mainChartview = FXMLLoader.load(getClass().getResource("../Charts/MainChart.fxml"));
        Scene mainChartScene = new Scene(mainChartview);

        Stage window =(Stage) ((Node) e.getSource()).getScene().getWindow();

        window.setScene(mainChartScene);
        window.show();
    }

    /**
     * Setting the Scene to Table.fxml
     * @param e
     * @throws IOException
     */
    @FXML
    private void goDatabase(ActionEvent e) throws IOException
    {
        Parent databaseChartview = FXMLLoader.load(getClass().getResource("../Database/Table.fxml"));
        Scene  databaseChartScene = new Scene(databaseChartview);

        Stage window =(Stage)  ((Node) e.getSource()).getScene().getWindow();

        window.setScene(databaseChartScene);
        window.show();
    }

    /**
     * Saves the Values from the Textfields in the Database.
     * @param e
     * @throws SQLException
     */
    public void saveData(ActionEvent e) throws SQLException
    {
        DatabaseConnector connectionClass = new DatabaseConnector();
        Connection connection = DatabaseConnector.getConnection();

        String sql1 = "INSERT INTO parameter_a(Name, PopulationA, WachstumsRA, TragfähigkeitA, KonkEffektA)" +
                "VALUES('"+tf9.getText()+"','"+tf1.getText()+"','"+tf3.getText()+"','"+tf5.getText()+"','"+tf7.getText()+"')";

        String sql2 = "INSERT INTO parameter_b(Name, PopulationB, WachstumsRB, TragfähigkeitB, KonkEffektB)" +
                "VALUES('"+tf9.getText()+"','"+tf2.getText()+"','"+tf4.getText()+"','"+tf6.getText()+"','"+tf8.getText()+"')";

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql1);
        statement.executeUpdate(sql2);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Anfangswerte gespeichert!");
        alert.setContentText("Sie haben die Anfangswerte erfolgreich gespeichert!");

        alert.showAndWait();
    }

    /**
     * Updates the Data inside the Database
     * @param e
     * @throws SQLException
     */
    public void doUpdate(ActionEvent e) throws SQLException
    {
        Connection connection = DatabaseConnector.getConnection();

        String sql1 = "UPDATE konkurrenz_um_ressourcen.parameter_a " +
                "SET PopulationA = "+tf1.getText()+", WachstumsRA = "+tf3.getText()+", TragfähigkeitA = "+tf5.getText()+", KonkEffektA = "+tf7.getText()+" " +
                "WHERE Name like '" + tf9.getText()+"'";
        String sql2 = "UPDATE konkurrenz_um_ressourcen.parameter_b " +
                "SET PopulationB = "+tf2.getText()+", WachstumsRB = "+tf4.getText()+", TragfähigkeitB = "+tf6.getText()+", KonkEffektB = "+tf8.getText()+" " +
                "WHERE Name like '" + tf9.getText()+"'";

        int i;
        for(i=0; i<getListM().size(); i++)
        {
            String sql3 = "UPDATE konkurrenz_um_ressourcen." + tf9.getText() + " " +
                    "SET Population_A = " + getListA().get(i) + ", Population_B = " +
                    getListB().get(i) + ", Konkurrenz = " + getListK().get(i) + " WHERE Month = "+getListM().get(i)+"";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql3);
        }

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql1);
        statement.executeUpdate(sql2);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Ergebnisse geupdated!");
        alert.setContentText("Sie haben die Ergebnisse erfolgreich geupdated!");

        alert.showAndWait();
    }

    /**
     * Creates the Scatter Chart from the specific Values that were given.
     * @param e
     */
    public void createChart(ActionEvent e)
    {
        String etf = tf9.getText();
        tf10.setText(etf + " Ergebnisse");

        lineChart.getData().clear();
        this.getValues();

        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series3 = new XYChart.Series<>();

        int i;

        for (i=0; i < (getListA().size()); i++)
          {

          Number M = (Number) getListM().get(i);
          Number A = (Number) getListA().get(i);
          Number B = (Number) getListB().get(i);
          Number K = (Number) getListK().get(i);

          series1.getData().add(new XYChart.Data<>(M, A));
          series2.getData().add(new XYChart.Data<>(M, B));
          series3.getData().add(new XYChart.Data<>(M, K));
          }

        lineChart.getData().add(series1);
        lineChart.getData().add(series2);
        lineChart.getData().add(series3);

        series1.setName("Population A");
        series2.setName("Population B");
        series3.setName("Konkurrenz");

        data = FXCollections.observableArrayList();

        for (i=0; i < (getListA().size()); i++)
        {
            String M = String.valueOf(getListM().get(i));
            String A = String.valueOf(getListA().get(i));
            String B = String.valueOf(getListB().get(i));
            String K = String.valueOf(getListK().get(i));

            data.add(new Ergebnisse(M, A, B, K));
        }

        Month.setCellValueFactory(new PropertyValueFactory<>("Month"));
        PopA.setCellValueFactory(new PropertyValueFactory<>("PopA"));
        PopB.setCellValueFactory(new PropertyValueFactory<>("PopB"));
        Konk.setCellValueFactory(new PropertyValueFactory<>("Konk"));

        eData.setItems(null);
        eData.setItems(data);
    }

    /**
     * Saves the Name of the Project inside the Database Table PROJEKTE.
     * Creates a new Table in our Database.
     * Saves the Results inside the beforehand created Table.
     * @param e
     * @throws SQLException
     */
    public void saveOutput(ActionEvent e) throws SQLException
    {

        DatabaseConnector connector = new DatabaseConnector();
        Connection connection = DatabaseConnector.getConnection();

        String sql = "INSERT INTO PROJEKTE(Projekte)VALUES('"+tf9.getText()+"')";

        Statement st = connection.createStatement();
        st.executeUpdate(sql);

        Table ergebnisse = new Table(tf9.getText())
                .addPrimaryKey("Month",        Type.DECIMAL)
                .addAttr      ("Population_A", Type.DECIMAL)
                .addAttr      ("Population_B", Type.DECIMAL)
                .addAttr      ("Konkurrenz",   Type.DECIMAL);

        String sqlstatement = ergebnisse.create();
        System.out.println(sqlstatement);
        DatabaseConnector.updateStatement(connection, sqlstatement);

        int i;

        for (i=0; i<(getListA().size()); i++)
        {

            String sqla = "INSERT INTO " + tf9.getText() + "(Month, Population_A, Population_B, Konkurrenz)" +
                    "VALUES('" + getListM().get(i) + "','" + getListA().get(i) + "','"
                    + getListB().get(i) + "','" + getListK().get(i) + "')";

            Statement statement = connection.createStatement();
            statement.executeUpdate(sqla);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Ergebnisse gespeichert!");
        alert.setContentText("Sie haben die Ergebnisse erfolgreich gespeichert!");

        alert.showAndWait();

    }

    /**
     * Show the PopupProjects.fxml.
     * @param e
     * @throws IOException
     */
    public void showPopup(ActionEvent e) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Database/PopupProjects.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    /**
     * Gets the First Values from our Database.
     * Filling the Textfields with the First Values.
     * @param e
     * @throws SQLException
     */
    public void getFirstValues(ActionEvent e) throws SQLException
    {
        DatabaseConnector connector = new DatabaseConnector();
        Connection connection = DatabaseConnector.getConnection();

        if (s9=="")
        {
            s9 = tf9.getText();
        }

        String sql1 = "SELECT PopulationA, WachstumsRA, TragfähigkeitA, KonkEffektA " +
                        "FROM konkurrenz_um_ressourcen.parameter_a WHERE Name like '"+s9+"'";

        String sql2 = "SELECT PopulationB, WachstumsRB, TragfähigkeitB, KonkEffektB " +
                "FROM konkurrenz_um_ressourcen.parameter_b WHERE Name like '"+s9+"'";

        pst1 = connection.prepareStatement(sql1);
        pst2 = connection.prepareStatement(sql2);

        ResultSet rs1 = pst1.executeQuery();
        ResultSet rs2 = pst2.executeQuery();

        if(rs1.next())
        {
            String popA = rs1.getString("PopulationA");
            tf1.setText(popA);
            String war = rs1.getString("WachstumsRA");
            tf3.setText(war);
            String tra = rs1.getString("TragfähigkeitA");
            tf5.setText(tra);
            String konka = rs1.getString("KonkEffektA");
            tf7.setText(konka);
        }

        if(rs2.next())
        {
            String popb = rs2.getString("PopulationB");
            tf2.setText(popb);
            String wbr = rs2.getString("WachstumsRB");
            tf4.setText(wbr);
            String trb = rs2.getString("TragfähigkeitB");
            tf6.setText(trb);
            String konkb = rs2.getString("KonkEffektB");
            tf8.setText(konkb);
        }


        tf9.setText(s9);
    }
}

package de.lingen.modsim.KonkurrenzUmRessourcen.Database;

import java.sql.*;

/**
 * The DatabaseConnector Class is used to get Connection to our Database.
 */
public class DatabaseConnector
{

    /**
     * Gets Connection to our Database.
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException
    {
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/konkurrenz_um_ressourcen",
                                                            "root", "xxxenter9");
        System.out.println("Connection done!");

        return connection;
    }

    /**
     * Does an Update to our Database.
     * @param connection
     * @param sql
     * @return
     * @throws SQLException
     */
    public static int updateStatement(Connection connection, String sql) throws SQLException
    {
        Statement statement = connection.createStatement();
        int tuplesUpdate = statement.executeUpdate(sql);

        System.out.println("SQL update executed :" + sql);
        return tuplesUpdate;
    }

    /**
     * Creates the Schema from our Database.
     * @param args
     */
    public static void main(String[] args)
    {
        try
        {
            Connection connection = getConnection();
            createSchema(connection, "Konkurrenz_um_Ressourcen");
            createTable(connection);

            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Fehlermeldung: " + e.getSQLState());
            System.out.println("Fehlermeldung: " + e.getErrorCode());
        }
    }

    /**
     * Creates a new Schema.
     * @param connection gets Connection to Database.
     * @param schemaName here is the String for the Schema written.
     * @throws SQLException
     */
    private static void createSchema(Connection connection, String schemaName) throws SQLException
    {
        Schema schema = new Schema(schemaName);
        updateStatement(connection, schema.drop());
        updateStatement(connection, schema.create());
        updateStatement(connection, schema.use());
    }

    /**
     * Creates a new Table.
     * @param connection gets Connection to Database
     * @throws SQLException
     */
    public static void createTable(Connection connection) throws SQLException
    {
        Table parametera = new Table("PARAMETER_A")
                .addPrimaryKey("Name", Type.VARCHAR)
                .addAttr("PopulationA", Type.DECIMAL)
                .addAttr("WachstumsRA", Type.DECIMAL)
                .addAttr("TragfähigkeitA", Type.DECIMAL)
                .addAttr("KonkEffektA", Type.DECIMAL);

        Table parameterb = new Table("PARAMETER_B")
                .addPrimaryKey("Name", Type.VARCHAR)
                .addAttr("PopulationB", Type.DECIMAL)
                .addAttr("WachstumsRB", Type.DECIMAL)
                .addAttr("TragfähigkeitB", Type.DECIMAL)
                .addAttr("KonkEffektB", Type.DECIMAL);

        Table projects = new Table("PROJEKTE")
                .addPrimaryKey("Projekte", Type.VARCHAR);


        String sqlstatement4 = projects.create();
        String sqlStatement1 = parametera.create();
        String  sqlStatement = parameterb.create();

        System.out.println(sqlstatement4);
        System.out.println(sqlStatement);
        System.out.println(sqlStatement1);

        updateStatement(connection, sqlstatement4);
        updateStatement(connection, sqlStatement);
        updateStatement(connection, sqlStatement1);

    }
}

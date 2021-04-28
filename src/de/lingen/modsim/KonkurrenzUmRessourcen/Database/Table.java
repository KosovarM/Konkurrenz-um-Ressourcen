package de.lingen.modsim.KonkurrenzUmRessourcen.Database;

import java.util.ArrayList;

public class Table
{
    String name = null;
    ArrayList<Attribute> attributes = new ArrayList<>();

    public Table(String name)
    {
        this.name = name;
    }

    public String create()
    {
        String sqlStatement = "CREATE TABLE " + name + "(";
        String sqlStatementPrimaryKey = "";

        for (int i=0; i < attributes.size(); i++)
        {
            sqlStatement += attributes.get(i).create();

            if (i < attributes.size() -1)
                sqlStatement += ", ";

                sqlStatementPrimaryKey = attributes.get(i).createPrimaryKey(sqlStatementPrimaryKey);
        }
        sqlStatement += ", PRIMARY KEY (" + sqlStatementPrimaryKey + "));";

        return sqlStatement;
    }

    public Table addPrimaryKey(String name, Type type)
    {
        attributes.add(new PrimaryKey(name, type));
        return this;
    }

    public Table addAttr(String name, Type type)
    {
        attributes.add(new Attribute(name, type));
        return this;
    }
}

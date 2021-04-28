package de.lingen.modsim.KonkurrenzUmRessourcen.Database;

public class PrimaryKey extends Attribute
{

    public PrimaryKey(String name, Type type)
    {
        super(name, type);
    }

    public String createPrimaryKey(String sqlStatementPrimaryKey)
    {
        return sqlStatementPrimaryKey.isEmpty() ? name : sqlStatementPrimaryKey +", name";
    }
}

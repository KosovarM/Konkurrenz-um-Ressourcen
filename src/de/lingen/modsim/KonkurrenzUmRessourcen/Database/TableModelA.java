package de.lingen.modsim.KonkurrenzUmRessourcen.Database;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Class to use from a TableView.
 */
public class TableModelA
{
    private SimpleStringProperty Name;
    private SimpleDoubleProperty PopulationA;
    private SimpleDoubleProperty WachstumsRA;
    private SimpleDoubleProperty TragfähigkeitA;
    private SimpleDoubleProperty KonkEffektA;

    /**
     *
     * @param Name a SimpleStringProperty which will be used in the TableView as Column.
     * @param PopulationA a SimpleStringProperty which will be used in the TableView as Column.
     * @param WachstumsRA a SimpleStringProperty which will be used in the TableView as Column.
     * @param TragfähigkeitA a SimpleStringProperty which will be used in the TableView as Column.
     * @param KonkEffektA a SimpleStringProperty which will be used in the TableView as Column.
     */
    public TableModelA(String Name,
                       Double PopulationA,
                       Double WachstumsRA,
                       Double TragfähigkeitA,
                       Double KonkEffektA)
    {
        this.Name = new SimpleStringProperty(Name);
        this.PopulationA = new SimpleDoubleProperty(PopulationA);
        this.WachstumsRA = new SimpleDoubleProperty(WachstumsRA);
        this.TragfähigkeitA = new SimpleDoubleProperty(TragfähigkeitA);
        this.KonkEffektA = new SimpleDoubleProperty(KonkEffektA);
    }
    /**
     * Getter and Setter for the TableView Data.
     * @return
     */
    public String getName()
    {
        return Name.get();
    }

    public void setName(String name)
    {
        this.Name.set(name);
    }

    public double getPopulationA()
    {
        return PopulationA.get();
    }

    public void setPopulationA(double populationA)
    {
        this.PopulationA.set(populationA);
    }

    public double getWachstumsRA()
    {
        return WachstumsRA.get();
    }

    public void setWachstumsRA(double wachstumsRA)
    {
        this.WachstumsRA.set(wachstumsRA);
    }

    public double getTragfähigkeitA()
    {
        return TragfähigkeitA.get();
    }

    public void setTragfähigkeitA(double tragfähigkeitA)
    {
        this.TragfähigkeitA.set(tragfähigkeitA);
    }

    public double getKonkEffektA()
    {
        return KonkEffektA.get();
    }

    public void setKonkEffektA(double konkEffektA)
    {
        this.KonkEffektA.set(konkEffektA);
    }

}


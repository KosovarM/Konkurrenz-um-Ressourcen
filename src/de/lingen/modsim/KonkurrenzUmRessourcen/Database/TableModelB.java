package de.lingen.modsim.KonkurrenzUmRessourcen.Database;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Class to use from a TableView.
 */
public class TableModelB
{
    private SimpleStringProperty Name;
    private SimpleDoubleProperty PopulationB;
    private SimpleDoubleProperty WachstumsRB;
    private SimpleDoubleProperty TragfähigkeitB;
    private SimpleDoubleProperty KonkEffektB;

    /**
     *
     * @param Name a SimpleStringProperty which will be used in the TableView as Column.
     * @param PopulationB a SimpleStringProperty which will be used in the TableView as Column.
     * @param WachstumsRB a SimpleStringProperty which will be used in the TableView as Column.
     * @param TragfähigkeitB a SimpleStringProperty which will be used in the TableView as Column.
     * @param KonkEffektB a SimpleStringProperty which will be used in the TableView as Column.
     */
    public TableModelB(String Name,
                       Double PopulationB,
                       Double WachstumsRB,
                       Double TragfähigkeitB,
                       Double KonkEffektB)
    {
        this.Name = new SimpleStringProperty(Name);
        this.PopulationB = new SimpleDoubleProperty(PopulationB);
        this.WachstumsRB = new SimpleDoubleProperty(WachstumsRB);
        this.TragfähigkeitB = new SimpleDoubleProperty(TragfähigkeitB);
        this.KonkEffektB = new SimpleDoubleProperty(KonkEffektB);
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

    public double getPopulationB()
    {
        return PopulationB.get();
    }

    public void setPopulationB(double populationB)
    {
        this.PopulationB.set(populationB);
    }

    public double getWachstumsRB()
    {
        return WachstumsRB.get();
    }

    public void setWachstumsRB(double wachstumsRB)
    {
        this.WachstumsRB.set(wachstumsRB);
    }

    public double getTragfähigkeitB()
    {
        return TragfähigkeitB.get();
    }

    public void setTragfähigkeitB(double tragfähigkeitB)
    {
        this.TragfähigkeitB.set(tragfähigkeitB);
    }

    public double getKonkEffektB()
    {
        return KonkEffektB.get();
    }

    public void setKonkEffektB(double konkEffektB)
    {
        this.KonkEffektB.set(konkEffektB);
    }

}

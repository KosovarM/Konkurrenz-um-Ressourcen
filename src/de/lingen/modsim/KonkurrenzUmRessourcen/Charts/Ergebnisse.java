package de.lingen.modsim.KonkurrenzUmRessourcen.Charts;

import javafx.beans.property.SimpleStringProperty;
/**
 * Class to use from a TableView.
 */
public class Ergebnisse
{
    private SimpleStringProperty Month;
    private SimpleStringProperty PopA;
    private SimpleStringProperty PopB;
    private SimpleStringProperty Konk;

    /**
     *
     * @param Month a SimpleStringProperty which will be used in the TableView as Column.
     * @param PopA a SimpleStringProperty which will be used in the TableView as Column.
     * @param PopB a SimpleStringProperty which will be used in the TableView as Column.
     * @param Konk a SimpleStringProperty which will be used in the TableView as Column.
     */
    public Ergebnisse(String Month,
                      String PopA,
                      String PopB,
                      String Konk)
    {
        this.Month = new SimpleStringProperty(Month);
        this.PopA = new SimpleStringProperty(PopA);
        this.PopB = new SimpleStringProperty(PopB);
        this.Konk = new SimpleStringProperty(Konk);
    }

    /**
     * Getter and Setter for the TableView Data.
     * @return
     */
    public String getMonth()
    {
        return Month.get();
    }

    public void setMonth(String month)
    {
        this.Month.set(month);
    }

    public String getPopA()
    {
        return PopA.get();
    }

    public void setPopA(String popA)
    {
        this.PopA.set(popA);
    }

    public String getPopB()
    {
        return PopB.get();
    }

    public void setPopB(String popB)
    {
        this.PopB.set(popB);
    }

    public String getKonk()
    {
        return Konk.get();
    }

    public void setKonk(String konk)
    {
        this.Konk.set(konk);
    }
}

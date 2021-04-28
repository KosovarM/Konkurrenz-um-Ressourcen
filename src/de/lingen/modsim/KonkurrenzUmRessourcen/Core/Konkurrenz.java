package de.lingen.modsim.KonkurrenzUmRessourcen.Core;

import java.util.ArrayList;

/**
 * Class to calculate the new Populations for the Linechart.
 */
public class Konkurrenz
{

    static ArrayList<Number> PopulationAliste = new ArrayList<Number>();
    static ArrayList<Number> PopulationBliste = new ArrayList<Number>();
    static ArrayList<Number> Konkurrenzliste = new ArrayList<Number>();
    static ArrayList<Number> Monatliste = new ArrayList<Number>();

    /**
     * Calculates the new Populationvalues with the Startvalues given from Linechartcontroller.
     * Inserts the calculatedd values into the Arraylists
     * @param PAs, PBs, WrAs, WrBs, TAs, TBs, KEAs, KEBs
     */
    public static void Konkurrenzkampf(String PAs, String PBs, String WrAs, String WrBs, String TAs, String TBs, String KEAs, String KEBs)
    {
        double PAd = Double.parseDouble(PAs);
        double PBd = Double.parseDouble(PBs);

        double WrAd = Double.parseDouble(WrAs);
        double WrBd = Double.parseDouble(WrBs);

        double TAd = Double.parseDouble(TAs);
        double TBd = Double.parseDouble(TBs);

        double KEAd = Double.parseDouble(KEAs);
        double KEBd = Double.parseDouble(KEBs);


        double PA = 0, PB = 0;      //Population
        double K;                   //Konkurrenz
        double WA, WB;              //Wachstum
        double KA, KB;              //Verlust durch Konkurrenz

        PopulationAliste.clear();
        PopulationBliste.clear();
        Konkurrenzliste.clear();
        Monatliste.clear();

        Integer r = 0;

        while(Time.Month(r)<=25) {

            if(Time.Month(r)==0.0)
            {
                PA = PopulationA.AnfA(PAd);
                PB = PopulationB.AnfB(PBd);
            }


            K = PA * PB;


            WA = PopulationA.WrA(WrAd) * PA * (1 - PA / PopulationA.TA(TAd));
            WB = PopulationB.WrB(WrBd) * PB * (1 - PB / PopulationB.TB(TBd));


            KA = PopulationA.KEA(KEAd) * K;
            KB = PopulationB.KEB(KEBd) * K;


            PA = PA + (PopulationA.AnfA(PAd) * WA) - (PopulationA.AnfA(PAd) * KA);
            PB = PB + (PopulationB.AnfB(PBd) * WB) - (PopulationB.AnfB(PBd) * KB);

            PopulationAliste.add(PA);
            PopulationBliste.add(PB);
            Konkurrenzliste.add(K);
            Monatliste.add(Time.Month(r));


            r += 1;
            Time.Month(r);

        }
    }

    /**
     * gives out the Arraylist for Population A
     */
    public static ArrayList getListA()
    {
        return PopulationAliste;
    }

    /**
     * gives out the Arraylist for Population B
     */
    public static ArrayList getListB()
    {
        return PopulationBliste;
    }

    /**
     * gives out the Arraylist for Konkurrenz
     */
    public static ArrayList getListK()
    {
        return Konkurrenzliste;
    }

    /**
     * gives out the Arraylist for Month
     */
    public static ArrayList getListM()
    {
        return Monatliste;
    }


    /**
     * Is used to Test the Formula of Konkurrenzkampf()
     * */
    public static void main(String[] args)
    {
        int i = 0;

        Konkurrenzkampf("0.02", "0.02", "1", "0.99", "2", "2", "1", "1");

        for(i=0; i < (getListA().size()-1); i++)
        {
            System.out.println("y-AchseA " + getListA().get(i) + "; x-Achse " + getListM().get(i));
            System.out.println("y-AchseB " + getListB().get(i) + "; x-Achse " + getListM().get(i));
            System.out.println("Momentane Konkurrenz beträgt: " + getListK().get(i));
            System.out.println("");
        }
    }
}
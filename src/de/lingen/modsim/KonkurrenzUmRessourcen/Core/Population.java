package de.lingen.modsim.KonkurrenzUmRessourcen.Core;


/**
 * Class to get the startingvalues for the calculation
 */
public class Population
{
    //Anfangswerte

    /**
     * Creates a Method to get the startvalue for Population
     * @param AnfW
     * @return
     */
    public static Double AnfP(double AnfW)
    {
        return AnfW;            //Population
    }

    /**
     * Creates a Method to get the startvalue for Growthrate
     * @param WrW
     * @return
     */
    public static Double Wr(double WrW)
    {
        return WrW;             //Wachstumsrate
    }

    /**
     * Creates a Method to get the startvalue for Load capacity
     * @param TW
     * @return
     */
    public static Double T(double TW)
    {
        return TW;              //Tragfähigkeit
    }

    /**
     * Creates a Method to get the startvalue for Competitioneffect
     * @param KEW
     * @return
     */
    public static Double KE(double KEW)
    {
        return KEW;             //Konkurrenzeffekt
    }

}

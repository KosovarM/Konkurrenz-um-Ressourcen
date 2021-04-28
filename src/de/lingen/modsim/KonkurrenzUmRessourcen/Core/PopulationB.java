package de.lingen.modsim.KonkurrenzUmRessourcen.Core;

/**
 * Class to get the startingvalues for Population B
 */
public class PopulationB extends Population
{

    /**
     * Creates a Method to get the startvalue for PopulationB and uses Population() for it
     * @param AnfWB
     * @return
     */
    public static double AnfB(double AnfWB)
    {
        return AnfP(AnfWB);
    }

    /**
     * Creates a Method to get the startvalue for Growthrate B and uses Population() for it
     * @param WrBW
     * @return
     */
    public static double WrB(double WrBW)
    {
        return Wr(WrBW);             //Wachstumsrate
    }

    /**
     * Creates a Method to get the startvalue for Load capacity B and uses Population() for it
     * @param TBW
     * @return
     */
    public static double TB(double TBW)
    {
        return T(TBW);              //Tragfähigkeit
    }

    /**
     * Creates a Method to get the startvalue for Competitioneffect B and uses Population() for it
     * @param KEBW
     * @return
     */
    public static double KEB(double KEBW)
    {
        return KE(KEBW);             //Konkurrenzeffekt
    }

}

package de.lingen.modsim.KonkurrenzUmRessourcen.Core;

/**
 * Class to get the startingvalues for Population A
 */
public class PopulationA extends Population
{

    /**
     * Creates a Method to get the startvalue for PopulationA and uses Population() for it
     * @param AnfWA
     * @return
     */
    public static double AnfA(double AnfWA)
    {
        return AnfP(AnfWA);
    }

    /**
     * Creates a Method to get the startvalue for Growthrate A and uses Population() for it
     * @param WrAW
     * @return
     */
    public static double WrA(double WrAW)
    {
        return Wr(WrAW);             //Wachstumsrate
    }

    /**
     * Creates a Method to get the startvalue for Load capacity A and uses Population() for it
     * @param TAW
     * @return
     */
    public static double TA(double TAW)
    {
        return T(TAW);              //Tragfähigkeit
    }

    /**
     * Creates a Method to get the startvalue for Competitioneffect A and uses Population() for it
     * @param KEAW
     * @return
     */
    public static double KEA(double KEAW)
    {
        return KE(KEAW);             //Konkurrenzeffekt
    }

}

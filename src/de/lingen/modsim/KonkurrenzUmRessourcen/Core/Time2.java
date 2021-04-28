package de.lingen.modsim.KonkurrenzUmRessourcen.Core;

/**
 * Klasse Time für Timestep und Month for the Konkurrenz2() class.
 */
public class Time2
{
    /**
     * Gives a Timestep.
     * @return
     */
    public static Double Timestep()
    {
        return 0.04;
    }

    /**
     * Uses the Timestep() to calculate the Month.
     * @param i
     * @return
     */
    public static Double Month(Integer i)
    {
        return i * Timestep();
    }

}
package de.lingen.modsim.KonkurrenzUmRessourcen.Core;

/**
 * Klasse Time für Timestep und Month for the Konkurrenz() class.
 */
public class Time
{
    /**
     * Gives a Timestep.
     * @return
     */
    public static Double Timestep()
    {
        return 0.02;
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
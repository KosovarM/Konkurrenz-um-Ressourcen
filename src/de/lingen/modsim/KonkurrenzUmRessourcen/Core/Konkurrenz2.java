package de.lingen.modsim.KonkurrenzUmRessourcen.Core;

import java.util.ArrayList;

/**
 * Class to calculate the new Populations for Scatterchart.
 * broken.
 */
public class Konkurrenz2
{
       // PWMChannel channel =

    static ArrayList<Number> state1liste = new ArrayList<Number>();
    static ArrayList<Number> state2liste = new ArrayList<Number>();
    static ArrayList<Number> newstate1liste = new ArrayList<Number>();
    static ArrayList<Number> newstate2liste = new ArrayList<Number>();

    /**
     * Calculates the new Populationvalues.
     * Inserts the calculatedd values into the Arraylists
     * @param
     */
    public static void Konkurrenzkampf()
    {

        state1liste.clear();
        state2liste.clear();
        newstate1liste.clear();
        newstate2liste.clear();


        double PA =0 , PB=0;        //Population
        double K;                   //Konkurrenz
        double WA, WB;                      //Wachstum
        double KA, KB;                      //Verlust durch Konkurrenz

        int r = 0;


        double maxstate1 = 2.5;
        double minstate1 = 0;
        double maxstate2 = 2.5;
        double minstate2 = 0;

        int center1oredge0 = 1;

        int computelength = 10;

        int Initaltime = 0;
        int Finaltime = 500;


        double state1;
        double state2;

        double limit1 = 1;
        double limit2 = 1;

        double modulotime;
        double normtime;
        int intgrmodulo;

        double newstate1 = 0;
        double newstate2 = 0;

        double Saveper = 0.04;
        double timestep = 0.02;

        double flixtrain;
        double flixtrain2;


        while(Time2.Month(r)<=Finaltime) {

            K = PA * PB;


            WA = PopulationA.WrA(1) * PA * (1 - PA / PopulationA.TA(2));
            WB = PopulationB.WrB(0.99) * PB * (1 - PB / PopulationB.TB(2));


            KA = PopulationA.KEA(1) * K;
            KB = PopulationB.KEB(1) * K;


            PA = PA + WA * Saveper - KA * Saveper;
            PB = PB + WB * Saveper - KB * Saveper;


            PA = PA * limit1 + 0.02 * newstate1;
            PB = PB * limit2 + 0.02 * newstate2;


            state1 = PA;
            state2 = PB;


            normtime = (Time2.Month(r)+timestep/2)/(Finaltime-Initaltime);

            modulotime = 10*normtime%1;

            intgrmodulo = (int) (10*modulotime);


            if(state1>(3*maxstate1-minstate1)/2)
            {limit1 = 0;}
            else
            {if(state1<(3*minstate1-maxstate1))
            {limit1=0;}
            else
            {limit1=1;}}

            if(state2>(3*maxstate2-minstate2)/2)
            {limit2 = 0;}
            else
            {if(state2<(3*minstate2-maxstate2))
            {limit2=0;}
            else
            {limit2=1;}}


            if (r > Initaltime && 0 == r%(computelength/Saveper))
                flixtrain = 1;
            else {flixtrain = 0;}

            if (r > Initaltime && 0 == r%(computelength*10/Saveper))
                flixtrain2 = 1;
            else {flixtrain2 = 0;}


            newstate1 = /*PULSE TRAIN*/((flixtrain) *
                    ((maxstate1 - minstate1) * (intgrmodulo / 10) + minstate1 +
                            center1oredge0 * (maxstate1 - minstate1) / 20 - state1) / timestep) * 2;


            newstate2 = /*PULSE TRAIN*/((flixtrain2)*
                    (((maxstate2-minstate2)/10) * (int)(10*normtime) + minstate2 +
                            center1oredge0 * (maxstate2-minstate2)/20 - state2)/ timestep) / 1.5;


            if(Time.Month(r)<=50.04) {
                System.out.println("state1 = " + state1);
                System.out.println("state2 = " + state2);
                System.out.println("newstate1 = " + newstate1);
                System.out.println("newstate2 = " + newstate2);
                System.out.println("train = " + flixtrain);
                System.out.println(r*0.04);
                System.out.println("");
            }


            state1liste.add(state1);
            state2liste.add(state2);
            newstate1liste.add(KA);
            newstate2liste.add(KB);

            r += 1;
        }
    }

    /**
     * gives out the Arraylist for state1
     */
    public static ArrayList getstate1list()
    {
        return state1liste;
    }

    /**
     * gives out the Arraylist for state2
     */
    public static ArrayList getstate2list()
    {
        return state2liste;
    }

    /**
     * gives out the Arraylist for newstate1
     */
    public static ArrayList getnewstate1list()
    {
        return newstate1liste;
    }

    /**
     * gives out the Arraylist for newstate2
     */
    public static ArrayList getnewstate2list()
    {
        return newstate2liste;
    }


    public static void main(String[] args)
    {
        Konkurrenzkampf();
    }

}

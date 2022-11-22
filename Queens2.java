
import java.lang.Math;
import java.util.*;

/* YOU NEED TO ADD YOUR CODE TO THIS CLASS, REMOVING ALL DUMMY CODE
 *
 * DO NOT CHANGE THE NAME OR SIGNATURE OF ANY OF THE EXISTING METHODS
 * (Signature includes parameter types, return types and being static)
 *
 * You can add private methods to this class if it makes your code cleaner,
 * but this class MUST work with the UNMODIFIED Tester2.java class.
 *
 * This is the ONLY class that you can submit for your assignment.
 *
 * MH October 2020
 */
public class Queens2
{
    private static int boardSize = 12;
    
    // ************************************************************************
    // ************ A. RANK A SET OF VALUES ***********************************
    // ************************************************************************
    
    // returns the rank of the values provided
    // worst =0 ; best = values.length-1
    // ranking is shared if tied:
    // for input values [30, 70, 50, 50, 40, 80] 
    // the rankings are [0, 4, 2.5, 2.5, 1, 5]
    // ... because (2 + 3)/2 = 2.5
    public static double[] rank(Integer values[])
    {
        double rank[] = new double[values.length];
        
        // YOUR CODE GOES HERE
        rank = new double[]{5, 4, 3, 2, 1, 0};
        
        
        for (int i=0; i<values.length; i++)
        {
            double position = values.length - 1;
            int count = 1;
            for (int j=0; j<values.length; j++)
            {
                if (i != j)
                {
                    if (values[i] == values[j])
                    {
                        count += 1;
                    }
                    if (values[j] >= values[i])
                    {
                        position -= 1;
                    }
                }
            }
            double seat = position;
            if (count > 1)
            {
                for (double k=1; k<count; k++)
                {
                    position = position + (seat+k);
                }
            }
            rank[i] = position/count;
        }
        // END OF YOUR CODE
        
        return rank;
    }
    
    // ************************************************************************
    // ************ B. CALCULATE LINEAR RANKING PROBABILITY *******************
    // ************************************************************************
    
    // calculates the linear ranking probability of a genotype (see the linear ranking equation)
    public static double linearRankingProb(double rank, double s, int populationSize)
    {
        // YOUR CODE GOES HERE
        // DUMMY CODE TO REMOVE:
        double probability = 0.2;
        double firstPart = (2-s)/populationSize;
        double otherPart = ((2*rank)*(s-1))/(populationSize*(populationSize-1));
        probability = firstPart + otherPart;
        // END OF YOUR CODE
        
        return probability;
    }
    
    // ************************************************************************
    // ************ C. PERFORM LINEAR RANKING PARENT SELECTION ****************
    // ************************************************************************
    
    /* performs linear ranking parent selection (see class slides & the guide in the course shell)
     * s is the 'selective pressure' parameter from the P[i] equation
     */
    public static Integer[][] linearRankingSelect(Integer[][] population, double s)
    {
        // The first three parts of this method have been written for you...
        // But the fourth part - selecting the two parents - is up to you!
        
        Integer [][] parents = new Integer [2][boardSize]; // will hold the selected parents
        int popSize = population.length;
        
        // 1. determine the fitness of each member of the population
        Integer [] fitness = getFitnesses(population);
        
        // 2. hence determine the ranking of each member by its fitness
        double [] rank = rank(fitness);
        
        // 3. determine the cumulative probability of selecting each member, using the linear ranking formula
        double [] cumulative = new double [popSize];
        cumulative[0] = linearRankingProb(rank[0], s, popSize);
        for (int index = 1; index < popSize; index ++)
        {
            cumulative[index] = cumulative[index-1] + linearRankingProb(rank[index], s, popSize);
        }
        
        // 4. finally, select two parents, based on the cumulative probabilities
        // see the pseudocode in RESOURCES > Evolutionary Computation General >
        // Linear Ranking: How to use cumulative probability to select parents
        
        // YOUR CODE GOES HERE
        // DUMMY CODE TO REMOVE: (which always returns the same two parents)
        parents[0] = new Integer[]{ 10, 6, 4, 2, 8, 11, 5, 12, 9, 1, 3, 7 };
        parents[1] = new Integer[]{ 9, 4, 3, 1, 2, 11, 5, 10, 12, 7, 8, 6 };
        
        int first = 0;
        int second = 0;
        Random rand = new Random();
        double random = rand.nextDouble();
        
        while (cumulative[first] < random)
        {
            first += 1;
        }
        second = first;
        while (second == first)
        {
            random = rand.nextDouble();
            second = 0;
            while (cumulative[second]<random)
            {
                second += 1;
            }
        }
        parents[0] = population[first];
        parents[1] = population[second];
        // END OF YOUR CODE
        
        return parents;
    }
    
    // ************************************************************************
    // ************ D. (λ, μ) SURVIVOR SELECTION ******************************
    // ************************************************************************
    
    /* creates a new population through (λ, μ) survivor selection
     * given a required population of size n, and a set of children of size 2n,
     * this method will return the n fittest children as the new population
     */
    public static Integer[][] survivorSelection(Integer[][] children, int n)
    {
        Integer [][] newPop = new Integer [n][boardSize];
        
        // YOUR CODE GOES HERE
        // DUMMY CODE TO REMOVE:
        newPop [0] = new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        newPop [1] = new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        newPop [2] = new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        newPop [3] = new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        newPop [4] = new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        newPop [5] = new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        newPop [6] = new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        newPop [7] = new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        newPop [8] = new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        newPop [9] = new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        
        //find the lowest fitness
        int rockBottom = 0;
        for (int i=0; i<children.length; i++)
        {
            rockBottom = i;
            for (int j=0; j<children.length; j++)
            {
                if (Queens.measureFitness(children[j])<Queens.measureFitness(children[rockBottom]))
                {
                    rockBottom = j;
                }
            }
        }
        
        for (int i=0; i<newPop.length; i++)
        {
            int highestInTheRoom = rockBottom;
            for (int j=0; j<children.length; j++)
            {
                for (int k=0; k<children.length; k++)
                {
                    if (Queens.measureFitness(children[k]) > Queens.measureFitness(children[highestInTheRoom]) && (!Arrays.asList(newPop).contains(children[k])))
                    {
                        highestInTheRoom = k;
                    }
                }
            }
            newPop[i] = children[highestInTheRoom];
        }
        // END OF YOUR CODE
        
        return newPop;
    }
    
    // ************************************************************************
    // ************ E. FITNESS DIVERSITY **************************************
    // ************************************************************************
    
    // counts the number of unique fitness values seen in the population
    public static int fitnessDiversity(Integer[][] population)
    {
        // YOUR CODE GOES HERE
        // DUMMY CODE TO REMOVE:
        int uniqueFitnessValues = population.length;
        int copies = 0;
        for (int i=0; i<population.length; i++)
        {
            for (int j=i; j<population.length; j++)
            {
                if (i != j && (Arrays.equals(population[i], population[j])))
                {
                    copies += 1;
                    break;
                }
            }
        }
        uniqueFitnessValues -= (copies);
        // END OF YOUR CODE
        
        return uniqueFitnessValues;
    }
    
    // ************************************************************************
    // ************ F.  INVERSION MUTATION ************************************
    // ************************************************************************
    
    // inverts the order of a series of genes in the genotype
    public static Integer[] inversionMutate(Integer[] genotype, double p)
    {
        // YOUR CODE GOES HERE
        // DUMMY CODE TO REMOVE:
        Integer [] genotype0 = new Integer[genotype.length];
        for (int i=0; i<genotype.length; i++)
        {
            genotype0[i] = genotype[i];
        }
        
        Random rand = new Random();
        double chance = rand.nextDouble();
        int random0 = rand.nextInt(genotype.length);
        int random1 = rand.nextInt(genotype.length);
        while (random1<= random0)
        {
            random0 = rand.nextInt(genotype.length);
            random1 = rand.nextInt(genotype.length);
        }
        
        if (chance<p)
        { 
            int cupholder = random1;
            for (int i=random0; i<=random1; i++)
            {
                genotype0[i] = genotype[cupholder];
                cupholder -= 1;
            }
        }
        genotype = genotype0;
        // END OF YOUR CODE
        
        return genotype;
    }
    
    // ************ DO NOT EDIT OR DELETE THE METHOD BELOW! *******************
    
    // ************************************************************************
    // ************ GET THE FITNESS VALUES OF A POPULATION ********************
    // ************************************************************************
    
    // returns an array of fitnesses for a population
    private static Integer[] getFitnesses(Integer [][] population)
    {
        int popSize = population.length;
        Integer [] fitness = new Integer [popSize];
        
        for (int index = 0; index < popSize; index ++)
        {
            fitness[index] = Queens.measureFitness(population[index]);
        }
        
        return fitness;
    }
}

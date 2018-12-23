/*
 * CensusCalculator.java
 * 
 * Computer Science S-111, Harvard University
 *
 * This program performs computations on census data stored in a text file.
 * It uses arrays in several ways, including for storage of the results of
 * the computations.
 * 
 * modified by: Anusha Prasad 
 * date: 7/9/16
 * 
 */

import java.util.*;
import java.io.*;

public class CensusCalculator {
    /* 
     * A class-constant array of Strings containing the names of the states
     * in the data file.
     * 
     * The index of a given state name in the array is the
     * same as the numeric code of the state in the data file.
     * For example, Alaska has a state code of 1 in the data file, 
     * so its name is at position 1 in this array.
     */
    public static final String[] STATE_NAMES = {"Alabama", "Alaska",
        "Arizona", "Arkansas", "California", "Colorado", "Connecticut",
        "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois",
        "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine",
        "Maryland", "Massachusetts", "Michigan", "Minnesota",
        "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada",
        "New Hampshire", "New Jersey", "New Mexico", "New York",
        "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", 
        "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota",
        "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington",
        "West Virginia", "Wisconsin", "Wyoming", "District of Columbia"};
    
    /* 
     * A class-constant array of Strings containing the names of 
     * the nine divisions used by the Census Bureau.
     * 
     * The Census Bureau also numbers the divisions, and we have
     * used a division's number as its index in this array.
     * For example, New England is Division 1, so its name is at
     * position 1 in this array.
     * 
     * Note that there is no division 0, so we have put the name
     * "none" in position 0 of the array.
     */
    public static final String[] DIVISION_NAMES = {
        "none", "New England", "Mid-Atlantic", "East North Central", "West North Central", 
        "South Atlantic", "East South Central", "West South Central", 
        "Mountain", "Pacific"};  
    
    /*
     * A class-constant array of integers that allows you to
     * determine the number of the division to which each
     * state belongs.
     * 
     * For example, Alaska has a state code of 1.
     * Element 1 of this array is the integer 9, which indicates
     * that Alaska is in division 9 (Pacific).
     */
    public static final int[] DIVISION_FOR_STATE = {6, 9,
        8, 7, 9, 8, 1, 5, 5, 5, 9, 8, 3, 3, 4, 4, 6, 7, 1, 5, 
        1, 3, 4, 6, 4, 8, 4, 8, 1, 2, 8, 2, 5, 4, 3, 7, 9, 2,
        1, 5, 4, 6, 7, 8, 1, 5, 9, 5, 3, 8, 5};
    
    
    //main method 
    public static void main (String[] args) throws FileNotFoundException
    {
        //initializing Scanner 
        Scanner console = new Scanner (System.in);
        System.out.print("Name of data file: ");
        String file = console.nextLine();
        //local variables responsible for getting the state name and the division name
        String census;
        String state;
        
        do
        {
            System.out.print("Name of state or q (to quit): ");
            state = console.nextLine();
            System.out.println();
            census = getStateDivisionCensus(state);
            int stateCode = getStateCode(state);
            //runs when the name is valid
            if (isNameValid(state))
            {
                System.out.println(state + " is in the " + census + " division");
                System.out.println();
                System.out.println("Counties: ");
                getCounties(console, file, state);
                
            }
            System.out.println();
        }while (!state.equals("q"));
    }
    
     /*
     * getStateCode - finds and returns the state code for
     * the specified state name, and -1 if the specified
     * state name is not found.
     * 
     * You will complete this method so that it searches 
     * through the STATE_NAMES array and returns the index
     * of stateName in that array, or -1 if stateName
     * is not found in that array.
     */
    public static int getStateCode(String stateName) {
        for (int i = 0; i < STATE_NAMES.length; i++)
        {
            if (stateName.equals(STATE_NAMES[i]))
            {
                return i;
            }
        }
        return -1;    
    }
    
    /**** PUT THE ADDITIONAL METHODS THAT YOU WRITE BELOW. ****/
    
    //returns the division number for the state or -1 if the number is not found
    public static int getStateDivisionNum (String stateName)
    {
        int stateDivision = getStateCode(stateName);
        
        for (int i = 0; i < DIVISION_FOR_STATE.length; i++)
        {
            if (i == stateDivision)
            {
                return DIVISION_FOR_STATE[i];
            }
        }
        return -1;
    }
    
    //returns the division name based on the division number for the specified state
    public static String getStateDivisionCensus (String stateName)
    {
        int divisionCensus = getStateDivisionNum(stateName);
        
        for (int i = 0; i < DIVISION_NAMES.length; i++)
        {
            if (i == divisionCensus)
            { 
                return DIVISION_NAMES[i];
            }
        }
        //if the division name is not able to be found
        return "division name not found";
    }
    
    //returns true when the state name is found in the constant state name array, and false when it is not found in the array 
    public static boolean isNameValid (String stateName)
    {
        for (int i = 0; i < STATE_NAMES.length; i++)
        {
            if (stateName.equals(STATE_NAMES[i]))
            {
                return true;
            }
        }
        return false;
        
    }
    
    //prints the counties for a state, based on the various codes of the state inputted
    public static void getCounties (Scanner console, String file, String stateName) throws FileNotFoundException
    {
        Scanner input = new Scanner(new File(file));
        //reading only the first line of the files 
        String years = input.nextLine();
        String[] years_split = years.split(",");
        int[] counter = new int[years_split.length];
        //reading the rest of the lines of the file
        while (input.hasNextLine())
        {
            String record = input.nextLine();
            String[] fields = record.split(",");
            
            String county = fields[0];
            int code = getStateCode(stateName);
            
            //only inputs the population totals if the code is matched
            if (code == Integer.parseInt(fields[2]))
            {
                System.out.println(county);
                for (int i = 0; i < years_split.length; i++)
                {
                    counter[i] += Integer.parseInt(fields[i+3]);
                }
            }
        }
        //printing the population totals 
        System.out.println();
        System.out.println("Population totals: ");
        printPopulations(counter, years_split);
    }
    
    //prints the population totals using printf
    public static void printPopulations (int[] population, String[] years)
    {
        for(int i = 0; i < years.length; i++)
        {
            //formatting the population totals (1990: ...., 2007: ....)
            System.out.printf("%s: %,d%n",years[i], population[i]);
        }
    }
}

import java.util.Arrays;
import java.util.Random;

/**
 * This class implements a Dynamic Programming solution for the Skiing Problem
 * @author Skye Guegan
 *
 */
public class Skiing {
    // All measurements are given in inches
    private static final int MAX_HEIGHT = 77;
    private static final int MIN_HEIGHT = 61;

    // Feel free to change these values as long as NUM_SKIS > NUM_SKIERS
    private static final int NUM_SKIS = 5;
    private static final int NUM_SKIERS = 3;

    // These will contain the actual human and ski lengths
    private static int[] skiHeights;
    private static int[] skierHeights;
    private static int[][] A;

    /**
     * Randomly populates the skier and ski arrays with realistic
     * human heights and ski lengths
     */
    private static void populateArrays(){
        Random rng = new Random();       
        for(int i = 0; i < skierHeights.length; i++){
            skierHeights[i] = rng.nextInt(MAX_HEIGHT-MIN_HEIGHT + 1) + MIN_HEIGHT; 
        }
        for(int i = 0; i < skiHeights.length; i++){
            skiHeights[i] = rng.nextInt(MAX_HEIGHT-MIN_HEIGHT + 1) + MIN_HEIGHT;
        }
    }

    /**
     * Returns an optimal solution to an instance of the Skiing Problem. 
     * @param assignments An array whose length is the number of skiers. The ith entry
     * in the array is the ski assignment for person i.  
     */
    public static void computeSolution(int[] assignments){
        DP_ski();
        Ski_Soln(NUM_SKIERS, NUM_SKIS, assignments);
    }

    /**
     * Once you're done, you can run this main 
     * @param args
     */
    public static void main(String[] args){         
        skiHeights = new int[NUM_SKIS];
        skierHeights = new int[NUM_SKIERS];     
        populateArrays();
        // Note: sorting is crucial for this problem...why is that?
        //The way we go through the array only works if it is sorted
        Arrays.sort(skiHeights);
        Arrays.sort(skierHeights);
        int[] assignments = new int[NUM_SKIERS];
        computeSolution(assignments);
        System.out.println("The optimal ski assignment is: ");
        System.out.println(Arrays.toString(assignments));
    }
    /*********************************************************
     *              Private helper methods
     *********************************************************/

    /**
     * Computes the value of the optimal solution
     */
    private static int DP_ski() {
        A = new int [NUM_SKIERS+1][NUM_SKIS+1];

        for(int i=1; i<=NUM_SKIERS; i++){
            A[i][0]=1000;
        }

        for(int j=1; j<=NUM_SKIS; j++){
            A[0][j]=0;
        }

        for(int i=1; i<=NUM_SKIERS; i++){
            for(int j=1; j<=NUM_SKIS; j++){
                if((A[i-1][j-1]+java.lang.Math.abs(skierHeights[i-1]-skiHeights[j-1]))<=A[i][j-1]){
                    A[i][j]=A[i-1][j-1]+java.lang.Math.abs(skierHeights[i-1]-skiHeights[j-1]);
                }
                else{
                    A[i][j]=A[i][j-1];
                }
            }
        }
        return A[NUM_SKIERS][NUM_SKIS];
    }

    /**
     * Computes the actual optimal solution
     * @param assignments An array whose length is the number of skiers. The ith entry
     * in the array is the ski assignment for person i. 
     */
    private static void Ski_Soln(int i, int j, int[] assignments) {
        if( i==0 || j== 0){
            return;
        }

        if((A[i-1][j]+java.lang.Math.abs(skierHeights[i-1]-skiHeights[j-1]))<=A[i][j-1]){
            assignments[i-1]=j;
            Ski_Soln(i-1, j-1, assignments);
        }
        else{
            Ski_Soln(i, j-1, assignments);
        }
    }
}
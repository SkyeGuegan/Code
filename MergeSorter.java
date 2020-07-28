import java.util.Scanner;
import java.util.Arrays;
/**
 * Mergesort is a recursive algorithm. It consists of four steps:
 * 1. Divide the active area into two equal regions: a “left half” and a “right half”.
 * 2. Recurse on the left half.
 * 3. Recurse on the right half.
 * 4. Merge the left and right halves together into a big sorted region.
 * 
 * @author (Skye Guegan) 
 * @version (3/6/2017)
 */
public class MergeSorter extends Sorter
{

    public static void main(String[] args) {
        System.out.println("Testing Merge sort.");
        System.out.println("Please enter the array size: ");
        Scanner scanner= new Scanner(System.in);
        String input= scanner.nextLine();
        int size = 0;
        //only excepts integers
        try{
            size= Integer.parseInt(input);
        }
        catch(NumberFormatException e){
            System.out.println("WOW. YOU are an idiot. Enter a @#%$#@# integer!");
            System.exit(1);
        }
        Sorter sorter= new MergeSorter();
        //         Integer[] array = new Integer[10];
        //         for (int i=0; i<array.length; i++) array[i] = new Integer((int)(Math.random() * size));
        //         System.out.println(Arrays.toString(array));
        //         sorter.sort(array);
        //         System.out.println(Arrays.toString(array));
        int msecs= sorter.timeSort(size);
        System.out.println("Array of size " + size + " took " + msecs + " ms to sort.");
    }

    /**
    * Sort method using Merge sort.
    * 
    * @param  array to be sorted
    */
    @SuppressWarnings("unchecked")
    public <E extends Comparable<E>> void sort (E[] array){
        E[] buffer = (E[]) (new Comparable[array.length/2]);
        sort(array,0,array.length, buffer);
    }

    /**
     * Divides the active area into two equal regions: a “left half” and a “right half”.
     * Recurse on the left half. Recurse on the right half.
     * The merge step is the one that requires the auxiliary array. Copy the sorted left half into this array, 
     * and then compare the ﬁrst left element with the ﬁrst right element. The lesserone will be copied into the proper location in the full array. 
     * Continue this until one of the halves is exhausted.
     * 
     * @param  array to be sorted
     * @param  start of region being sorted
     * @param  end of region being sorted
     * @param  buffer the array that the sort compies left side to
     */
    private <E extends Comparable<E>> void sort (E[] array, int start, int end, E[] buffer){
        //bookkeeping;
        if(end-start<=1)return;
        int midpoint=(start+end)/2;
        //recursive calls;
        sort(array, start, midpoint, buffer);
        sort(array, midpoint, end, buffer);
        //merge
        System.arraycopy(array,start,buffer,0,midpoint-start);
        //System.out.println("M"+Arrays.toString(buffer));
        int j=0;
        int k=midpoint;
        int i= start;
        while(j<midpoint-start && k<end){            
            if(buffer[j].compareTo(array[k])<0){//if the next element in the buffer array is smaller than the next right element copy to actual array
                array[i]=buffer[j];
                j++;
                i++;
                //System.out.println("1"+Arrays.toString(array));
            }
            else{
                array[i]=array[k];
                k++;
                i++;
                //System.out.println("2"+Arrays.toString(array));
            }
        }
        if(j!=midpoint-start){//if all the right elements are sorted copy the rest of the left elements in the buffer array over to actual array
            System.arraycopy(buffer,j,array,i,midpoint-start-j);
            //System.out.println("3"+Arrays.toString(array));
        }
    }
}

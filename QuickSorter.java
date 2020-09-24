import java.util.Scanner;
import java.util.Arrays;
/**
 * Like mergesort, quick is a recursive algorithm. It consists of these steps:
 * 1. Partition the elements based on a pivot element:
 * (a) Pick the pivot (usually the ﬁrst element).
 * (b) Scan forwards from the beginning and backwards from the end, swapping so that elements toward the beginning are less than the pivot and elements toward the end are greater.
 * (c) Swap the pivot into the position just prior to where the scanning indices met.
 * 2. Recurse on the elements before the pivot.
 * 3. Recurse on the elements after the pivot.
 * 
 * @author (Skye Guegan) 
 * @version (3/19/2017)
 */
class QuickSorter extends Sorter
{

    public static void main(String[] args) {
        System.out.println("Testing Quick sort.");
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
        Sorter sorter= new QuickSorter();
        //         Integer[] array = new Integer[50];
        //         for (int i=0; i<array.length; i++) array[i] = new Integer((int)(Math.random() * size));
        //         System.out.println(Arrays.toString(array));
        //         sorter.sort(array);
        //         System.out.println(Arrays.toString(array));
        int msecs= sorter.timeSort(size);
        System.out.println("Array of size " + size + " took " + msecs + " ms to sort.");
    }

    /**
    * Sort method using Quick sort.
    * 
    * @param  array to be sorted
    */
    @SuppressWarnings("unchecked")
    public <E extends Comparable<E>> void sort (E[] array){
        sort(array,0,array.length);
    }

    /**
     * 1. Partition the elements based on a pivot element:
     * (a) Pick the pivot (usually the ﬁrst element).
     * (b) Scan forwards from the beginning and backwards from the end, swapping so that elements toward the beginning are less than the pivot and elements toward the end are greater.
     * (c) Swap the pivot into the position just prior to where the scanning indices met.
     * 2. Recurse on the elements before the pivot.
     * 3. Recurse on the elements after the pivot.
     * 
     * @param  array to be sorted
     * @param  start of region being sorted
     * @param  end of region being sorted
     */
    private <E extends Comparable<E>> void sort (E[] array, int start, int end){
        if(end-start<=1)return;
        //1.Partition
        //a.picks pivot
        int pivot=start;
        int l=pivot+1;
        int r=end;
        E hold;
        //b.Scan forwards from the beginning and backwards from the end, 
        //swapping so that elements toward the beginning are less than the 
        //pivot and elements toward the end are greater
        while(r!=l){
            if(array[pivot].compareTo(array[l]) > 0)
                l++;
            else{
                r--;
                if(array[pivot].compareTo(array[r]) > 0||array[l].compareTo(array[r]) == 0){
                    hold=array[r];
                    array[r]=array[l];
                    array[l]=hold;
                }
            }
        }
        //c.swap pivots just prior of where indices met
        hold=array[pivot];
        array[pivot]=array[l-1];
        array[l-1]=hold;
        //System.out.println("pivot "+pivot+" l: "+l+" r: "+r+Arrays.toString(array));

        //recursive calls
        sort(array, start, l-1);
        sort(array, l, end);
    }
}

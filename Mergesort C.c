/*
 * Mergesort is a recursive algorithm. It consists of four steps:
 * 1. Divide the active area into two equal regions.
 * 2. Recurse on the left half.
 * 3. Recurse on the right half.
 * 4. Merge the left and right halves together into a big sorted region.
 * 
 * @author (Skye Guegan) 
 * @version (1/31/2019) 
 */
#include <stdio.h>
#include <time.h>
#include <stdlib.h>

void sort (int *array, int start, int end, int *buffer);//prototype

int main(int argc, char **argv)
{
    if(argc!=2){//check to see if length was given
       printf("I'm sorry. You must include the length of the array.");
    return 0;
    }

    int length = atoi(argv[1]);
    printf("Doing sort on array of size %d...\n", length);

    //puts an array and a buffer array in the heap
    int *myArray= (int*) malloc(length*sizeof(int));
    int *bufferArray = (int*) malloc(length/2*sizeof(int));
    srand(time(0));
    //populating array with random ints 
    for(int i = 0; i<length; i++){ 
        myArray[i]=rand() % length;
        //printf(" %d ", myArray[i]); 
    }
    if(length <= 100){
        for(int i = 0; i<length; i++){ 
            printf(" %d ", myArray[i]); 
         }
        printf("\n");
    }

    clock_t t; 
    t = clock(); 

    sort(myArray, 0, length, bufferArray);
    
    t = clock() - t; 
    double time_taken = 1000*((double)t)/CLOCKS_PER_SEC;
  


    if(length <= 100){
        for(int i = 0; i<length; i++){ 
            printf(" %d ", myArray[i]); 
        }
         printf("\n");
    }
    printf("Time elapsed: %f ms \n", time_taken); 
    
    free(myArray);
    free(bufferArray);
    return 0;
}

/*
*Recursive function that sorts the away by making recursive calls and merging arrays
*Currently has a problem with sorting that I believe is coming from one of the for loops
*/
void sort (int *array, int start, int end, int *buffer){
    //bookkeeping;
        if(end-start<=1)return;
        int midpoint=(start+end)/2;
        int leftSize= midpoint-start;

    //recursive calls;
        sort(array, start, midpoint, buffer);
        sort(array, midpoint, end, buffer);

    //merge
        for(int i=0; i<leftSize; i++){
            buffer[i]=array[i+start];
        }

        int j=0;
        int k=midpoint;
        int i= start;
        

        while(j<leftSize && k<end){            
            if(buffer[j]<array[k]){//if the next element in the buffer array is smaller than the next right element copy to actual array
                array[i]=buffer[j];
                j++;
                i++;

            }
            else{
                array[i]=array[k];
                k++;
                i++;
            }
        }
        //if all the right elements are sorted copy the rest of the left elements in the buffer array over to actual array
        //for loop uses j at its current value and iterates i and j
            for(; j<leftSize; j++, i++){
                array[i]=buffer[j];
            }
        
}
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

void swapElements(int *a, int *b);
void sortArray(int *array, const int size);
void changeElements(int *val);
void printArray(int *array, const int size);
double findMean(int *array, const int size);
double findMedian(int *array, const int size);
double findStandardDeviation( int *array, const int size, double average);

int main(){

	int  n, *x;
        double mean = 0.0, median = 0.0, stdDev = 0.0;

	printf("This is the basic part of the program that asks the user to type the number of integers, i.e., 'n'. Next, allocate memory for 'n' integers, read the values of 'n' integers into the allocated memory usining scanf; next, find the mean, median and average of 'n' integers.Lastly, the allocated memory needs to be freed.\n");
        
	printf("\nRead using scanf how many integers you would like to type:\n");
        scanf("%d", &n);

        
	/*****************************************************************/

	//x is not automatically assigned a memory block when it is defined as a pointer variable, you need to allocate a block
	//of memory large enough to hold 'n' integers
        // Write the function that allocates memory to hold 'n' integers
        x=malloc(n*sizeof(int));
        
        printf("Please type 'n' integers: \n");
	/***********************************************************************/ 
	//Read in the list of numbers 'n' into the allocated block using scanf 
	int i;
        for(i = 0; i < n; i++){
                scanf("%d", &x[i]);
        };
		
        printf("\n"); //print new line after all numbers printed out
        printf("Displaying the numbers:\n");

       // Call printArray to display the integers      
        printArray(x,n);
        // Find the mean of integers using findMean function
        mean = findMean(x,n);
        printf("Mean of the numbers is: %f\n", mean);
        
        // Fidn the median of integers using findMedian function
        sortArray(x,n);
        median= findMedian(x,n);
        printf("Median of the numbers is: %f\n", median);
        // Find the standard deviation of integers using findStandardDeviation function
        stdDev= findStandardDeviation(x,n,mean);
         printf("Standard deviation of the numbers is: %f\n", stdDev);
	
       //Deallocate the memory ;
        
        free(x);
        
        return 0;
}





void printArray( int *array, const int size){
        int i ;
        for(i = 0; i < size; i ++){
                printf("%d ",array[i]);
        }
        printf("\n"); //print new line after all numbers printed out

}

void sortArray(int *array, const int size){
        int i,j;
        for(i = 0; i < size-1; i++){
                for(j=0; j < size-i-1; j++){
                        if(array[j] > array[j+1]){
                                swapElements(&array[j],&array[j+1]);
                        }
                }
        }
     //Complete this function
}

void swapElements( int *x, int *y){
        int temp = *x;
        *x=*y;
        *y=temp;
     // Complete this function

}



double findMean(int *array, const int size){
        if(size==0){
               return 0; 
        }else{
                int i;
                double total =0;
                for(i = 0; i < size; i++){
                        total+=(double)array[i];
                }
                double mean = total / size;
                return mean;
        }
    // COmplete this function
}

double findMedian(int *array, const int size){
        if(size % 2 !=0){
                return array[size/2];
        }else{
                int  i, middleIndex=size/2;
                double ind1, ind2;
                ind1=array[(size/2)];
                ind2=array[(size/2)-1];
                return ((ind1+ind2)/2);
        }
   //Complete this function;
}


double findStandardDeviation( int *array, const int size, double average){
        //subtraction part
        int i ;
        double *dptr ;
        dptr=malloc(size * sizeof(double));
        for(i = 0; i < size; i++){
                dptr[i]=array[i]-average;
        }
        
        

        //squaring part
        for(i = 0; i < size; i++){
                dptr[i]=pow(dptr[i],2);
        }

        //total part
        double total;
        for(i = 0; i < size; i++){
                total+=dptr[i];
        }

        //division

        return sqrt(total);
       // Complete this function

}


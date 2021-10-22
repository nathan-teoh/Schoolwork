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

        while(printf("Typing the number: \n"),(scanf("%d",&x)) != "^D"){
                
        }

        
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
}

void swapElements( int *x, int *y){
        int temp = *x;
        *x=*y;
        *y=temp;

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

}


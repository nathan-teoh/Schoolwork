#include <stdio.h>

int main(){
	int i = 0;
	int grade;
	double sum;
	double average;
	printf("Please enter 10 grades:\n");
	while (grade!=-1000){
		scanf("%d", &grade);
		if(grade!=-1000){
			i++;
			sum=sum+grade;
			average=(sum/i);
			printf("Sum is %f \n",sum);
			printf("Average is %f \n",average);
		}
	}
//	printf("You have entered %d grades \n",i);
//	printf("Your sum is %d and your average is %1.1f \n",sum, average);
return 0;
}

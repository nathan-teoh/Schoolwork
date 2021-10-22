#include <stdio.h>
#include <ctype.h>
#define MAXROWS 100
#define NUM_ALPHABETS 26

int main(){

	int count, i, j, k;

	char sentence[MAXROWS];
	char alphabets[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	int frequency[NUM_ALPHABETS];


//step 0: assign all frequency to 0.
	for(k=0;k<NUM_ALPHABETS;k++){
		frequency[k]=0;
}

// step 1:Write a function that reads a sentence from the keyboard (2 pts)

	printf("Type a sentence : \n");
	fgets(sentence,MAXROWS, stdin);
 
// step 2:Find out the frequency of the alphabets  in the sentence, ignore uppercase or lowercase in the sentence(4 pts)
	for(i=0; sentence[i]!='\n';i++){	//outer for loop for sentence array
		sentence[i]=toupper(sentence[i]);	//convert all to upper since alphabets array is upper
		for(j=0; j < NUM_ALPHABETS; j++){	//inner for loop for alphabet array
			if(sentence[i]==alphabets[j]){	//if char matches
				frequency[j]++;	//increment
			}
		}
	}
// step 3: Print the histogram as shown in the screenshot(4 pts)

	printf("Alphabets	Frequency	Histogram\n");
	for(k=0; k<NUM_ALPHABETS;k++){
		printf("%9c %12d %20.*s \n",alphabets[k], frequency[k], frequency[k], "****************");
}
	return 0;



}

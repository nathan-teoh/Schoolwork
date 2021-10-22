#include <stdio.h>
#include <ctype.h> //library to convert upper to lowercase
#define MAXSIZE 100
int main(){
	char ch;
	int i;
	char sentence[MAXSIZE];
	int alphabetsCount =0;

	printf("Type a sentence : \n");
	fgets(sentence, MAXSIZE, stdin);	//get sentence
	printf("Type a character that you'd like to find the number of occurrences in a sentence: \n");
	scanf(" %c",&ch);	//get single char

	for(i = 0; sentence[i]!='\n'; i++){
		ch=tolower(ch); //make sure we convert ch to lower
		sentence[i]=tolower(sentence[i]); //convert to lower
		if (ch==sentence[i]){
			alphabetsCount++;
		}
	}
	printf("Alphabet %c has a frequency of %d\n", ch, alphabetsCount);
return 0;
}


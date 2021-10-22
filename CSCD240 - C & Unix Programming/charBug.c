//The problem with this C program is that getchar will take in \n at the end of every line. This will lead to a
//undesirable behavior.

//in order to prevent this, we add a while loop to "capture" the \n. now the program works fine. 


#include <stdio.h>
int main(){
	int c;
	char dummy[10];

	printf("Enter a value :");
	c = getchar();

	while ( c!='q' && c!='Q' ){
		printf( "You entered: ");
		putchar( c );
		printf("\n");
		printf("Enter a value :");
		while ((c=getchar()) == '\n'){
			
		}
		
	}
return 0;
}

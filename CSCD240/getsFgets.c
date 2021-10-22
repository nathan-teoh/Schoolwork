#include <stdio.h>

int main(){
	char name[100];
	printf("Please enter a name:");
	//fgets(name,100, stdin);	//original fgets function. this prevents possible buffer overflow by checking
					//the size of the input with the size of the memory allocated.
	gets(name);	//because gets does not check the allocated memory for variable name, buffer overflow is possible.
			//compiler will throw errors, but it'll still compile and work. VERY DANGEROUS.
	printf("The name you entered is %s", name);
return 0;
}

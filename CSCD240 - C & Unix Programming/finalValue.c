#include <stdio.h>

int main(){
	int a = 3;
	int b = 4;
	double c = ++a + b++;
	int size = sizeof(c/a);

	printf("size is %d and c is %f", size, c);

return 0;
}


//C will be 8 because a is a prefix increment while b is a postfix  increment. Thus when c is being evaluated, a is 4 but b is still 3
// thus we end up with 8.

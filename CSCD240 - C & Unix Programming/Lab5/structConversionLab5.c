#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Person{
char name[BUFSIZ];
char ssn[BUFSIZ];
int age;
float height;
float weight;
};


struct Person readData(){
	struct Person p1;
	char buff[BUFSIZ];
	char ssn[BUFSIZ];

fgets(buff,BUFSIZ,stdin);
	size_t len = strlen(buff);
	buff[len-1]='\0';
	strcpy(p1.name,buff);

	fgets(ssn,BUFSIZ,stdin);
	//size_t len1 = strlen(ssn);
	//ssn[len-1]='\0';
	strcpy(p1.ssn,ssn);

	char age[BUFSIZ];
	fgets(age,BUFSIZ,stdin);
	p1.age=atoi(age);

	char height[BUFSIZ];
	fgets(height,BUFSIZ,stdin);
	p1.height=atof(height);

	char weight[BUFSIZ];
	fgets(weight,BUFSIZ,stdin);
	p1.weight=atof(weight);



	return p1;
}


void printData(struct Person x){

	printf("%s\n",x.name);
	printf("%s",x.ssn);
	printf("Age = %d\n",x.age);
	printf("Height(cm) = %.1f\n",x.height);
	printf("Weight(cm) = %.1f\n",x.weight);

}


int main(){

struct Person person1;

        printf("Reading data ......\n");
				person1=readData();
        printf("\n");
        printf("Printing data ....\n");
	printData(person1);

	return 0;
}

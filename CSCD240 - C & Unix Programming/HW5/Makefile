output: file.o fileTest.o
	gcc file.o fileTest.o -o fileProgram
	
file.o: file.c fileTest.h
	gcc -c file.c -lm
	
fileTest.o : fileTest.c fileTest.h
	gcc -c fileTest.c -lm

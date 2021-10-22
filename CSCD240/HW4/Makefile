output: main.o LinkedListString.o
	gcc main.o LinkedListString.o -o LinkedListProgram

main.o: main.c LinkedListString.h
	gcc -c main.c -lm

LinkedListString.o: LinkedListString.c LinkedListString.h
	gcc -c LinkedListString.c -lm

clean:
	rm *.o output

#include "fileTest.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

int countString(const char* fileName, char *str){
    int word_count = 0, i;
    convertToLower(str);
    FILE *fptr = fopen(fileName,"r");
    if(fptr==NULL){
      return 0; //if file doesnt exist
    }else{
      char buff[BUFSIZ];
      while(fscanf(fptr,"%[a-zA-Z]%*[^a-zA-Z]", buff)==1){
        convertToLower(buff);
        if(strstr(buff,str)!=NULL){
          word_count++;
        }
      }
    }
    fclose(fptr);
    return word_count;
}



int countEmptyLines(const char *fileName){

    int emptyLine = 0;
    char c;
    char nl;
    FILE *fptr = fopen(fileName,"r");
    if(fptr==NULL){
      return 0; //if file doesnt exist
    }else{
      for(c = fgetc(fptr),nl = fgetc(fptr); c!=EOF; nl=c,c=fgetc(fptr)){
        if((c=='\n'&&nl=='\n')||(c=='\t'&&nl=='\t')||(c==' '&&nl==' ')){  //space or tab or new line
          emptyLine++;
        }
      }
    }

    fclose(fptr);
    return emptyLine;
}


void convertToLower( char *array ){
  int stringlength = strlen(array);
  int i;

  for(i = 0; i < strlen(array); i++){
    array[i] = tolower(array[i]);
  }
}

#include <stdio.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "LinkedListString.h"

void push(struct node** head_ref, char* new_data)
{
        /* allocate node */
        struct node* new_node = malloc(sizeof(struct node));

        /* put in the data  */

        strcpy(new_node->data, new_data);


        /* link the old list off the new node */
        new_node->next = (*head_ref);

        /* move the head to point to the new node */
        (*head_ref) = new_node;
}

void printList(struct node *head)
{
        struct node *temp = head;
        while (temp != NULL)
        {
                printf("%s  ", temp->data);
                temp = temp->next;
        }
}

int listCount(struct node *head)
{
  if(head==NULL){
    return 0;
  }
	int count = 1;
	struct node cur = *head;
	while(head!=NULL&&cur.next!=NULL){
		count++;
		cur=*cur.next;
	}
	return count;
}

void reverseList(struct node** head_ref)
{
   struct node *cur;
   struct node *toReturn = NULL;
   for(cur=*head_ref; cur!=NULL; cur=cur->next){
    //printf("cur is %s and address is %p\n",cur->data,cur);  //for debugging purposes
    push(&toReturn,cur->data);
    }
    *head_ref = toReturn;
}

void listAllDelete(struct node **currP)
{
  struct node *cur = *currP, *next;
  while(cur!=NULL){
    next = cur->next;
    free(cur);
    cur=next;
  }
  *currP = NULL;
}

void deleteElement(struct node **currP, char *value)
{
  struct node *cur = *currP, *prev;
  while(cur!=NULL && strcmp(cur->data,value)==0){ //if first item matches
    *currP = cur->next; //move pointer
    free(cur);  //free meomry
    cur=*currP; //move cur
  }

  while(cur!=NULL){ //while cur is not null, iterate
    while(cur!=NULL&&strcmp(cur->data,value)!=0){ //cur is not null AND value doesnt match
      prev = cur;
      cur = cur->next;
    }
    if(cur==NULL){  //worst case, doenst match. quit.
      return;
    }
    prev->next = cur->next; //runs here if not null and finds match not in first place.
    free(cur);  //free memory
    cur= prev->next;  //reinit cur;
  }
}

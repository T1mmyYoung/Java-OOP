//Timothy Young,CS 202, Program5:
//This script contains the linear linked list acting as the data structure for Program 5. A linear linked list
//typically contains a "has a" relationship with a node class for multiple objects. The LLL contains a Lnode head
// pointer, effectively allowing the list to contain many nodes. The abstract base class acting as the interface
//for this application is also in this file(general_list). The purpose of this is to allow the client to access methods
//within the hierarchy using only a base class pointer(general_list a_list). As required by the client, there are two
//abstract functions. One abstract function finds a matching services in the services array managing a service and deletes
//the entry from the search log. The other abstract function loads data from all three text files into services objects
//being managed by an array. These two functions interact with the hierarchy in services and allow the client to manage
//the service information. The build_list and display list functions manage the linear linked list(the data structure),
//allowing the client to perform all of these operations through one base class pointer that represents the interface.


package Program4;//Package to access BST functions:

import java.io.FileNotFoundException;//Read from text file exception.
//PROGRAM 5/////////////////////////////////
abstract class general_list extends Input {


    abstract protected General_info low(int num);//Read a text file and create a service object. num == priority file.
    abstract protected int findMatch(Info[] postings) throws FileNotFoundException;//Check to see if the service has a match.
    abstract protected void build_list() throws FileNotFoundException;//Build the linear linked list.
    abstract protected void display_list();//Display the linear linked list.

    //Default constructor:
    protected general_list() throws FileNotFoundException {
    }

    //Second findMatch function. If I implement another BST function to return a node upon match,
    //this should be functional. Produces an EXACT search result. This is meant to be a general search however,
    //so it is not necessarily needed and requires another traversal of the tree.
    protected abstract boolean match(Node postings) throws FileNotFoundException;
}

//This is the linear linked list(Data Structure Program 5):
//Functions:
//1: Build/Insert
//2. Display
//3. Find
//4. Delete All
//5. Delete a particular node(Search).
class List extends general_list{

    protected LNode head;//A list has many nodes "has a".

    //Default Constructor:
    protected List() throws FileNotFoundException {
    }

    //Build the linear linked list. Also works as an insert.
    protected void build_list() throws FileNotFoundException {
         int num = 1;//Priority is set for testing/grading purposes:
        do {
            System.out.println("What is the priority of these services?");
            //int num = a_scan.nextInt();//The client may also set the priority.

            LNode temp = new LNode(num);//Allocate a new node, calling the Lnode constructor.
            temp.priority = num;//Set the priority.
            ++num;//Increase the number for loop(priority).

            //If no head, insert at the head:
            if (this.head == null)
                this.head = temp;

            //If the priority is less than head, place at the beginning before head:
            else if (!(this.head.priority < temp.priority)) {
                temp.setNext(head);
                head = temp;
            } else {//If the priority is greater than, set the node after:
                LNode previous = this.head;//Set the previous pointer to head;
                LNode current = this.head.next;//Set current to head's next pointer.
                while (current != null && current.priority < temp.priority) {
                    previous = current;//Reconnect previous and current.
                    current = current.next;//Set current.
                }
                previous.setNext(temp);//Set previous's next pointer to temp.
                temp.setNext(current);//Set temp's next pointer to current.
            }
        } while ((Again()) && num != 4);//Keep adding until the clients inputs N or if num reads more than 3 files.
    }

    //Display the linear linked list:
    protected void display_list(){
        LNode current = this.head;
        //While there is still a node to display, keep traversing.
        while(current != null){
            current.display();//Call the Lnode display function.
            current = current.next;//Traverse with current.
        }
    }

    //Call the findMatch functions down the hierarchy.
    protected int findMatch(Info[] postings) throws FileNotFoundException {
        int num = this.head.findMatch(postings);
        return num;
    }

    //Delete all function to delete the entire list. Java has a garbage collector, does not need to be implemented.
    protected void deleteAll(LNode head){
        if(head == null)//Empty list:
            return;
        deleteAll(head.next);//Traverse the list.
        System.gc();//Calls the garbage collector.
    }

    //Delete a particular node in the list by priority:
    protected LNode deleteSearch(LNode head, int priority){
        if(priority<1)//Priority is not valid. Quit function.
            return head;
        if(head == null)//If the linked list is empty:
            return null;
        if(priority == 1){//Simple Base case:
            LNode obj = head.next;//Set obj to the next node.
            return obj;//Return obj.
        }
        head.next = deleteSearch(head.next,priority-1);//Traverse the list:
        return head;
    }

   //Overridden methods needed for hierarchy. Not actually implemented here:
    @Override
    protected boolean match(Node postings) {
        return false;
    }
    @Override
    protected General_info low(int num) {
        return null;
    }

}


//Timothy Young, CS202, Program5
//This script contains the node class for program 5. This is a node for a linear linked list, consisting of a next
//pointer and a priority score. Each node will be a certain priority, the list is sorted by priority. There are three
//text files(low priority, medium, high) that will load into their respective nodes in the list. Each file is associated
//with a priority or can be set by the client. Each node is derived inherently from services, making this a linear
//linked list of arrays. Services manages the array, doing it's own work. Node purely manages basic node functionalities,
//which include setting/getting a next pointer and calling the parent class constructor. This approach still follows OOP,
//given that there is no way around setters/getters for a node class. The node class still performs it's own work and
//relies on inheritance to let super classes to do their own work, giving us an OO solution.


package Program4;//Package for Program4 BST Functions:

//Program 5//////////////////////////////////////////////////////////
import java.io.FileNotFoundException;//Read from text file exception.

class LNode extends Services {
    protected LNode next;//Next pointer
    protected int priority;//Priority set from client/read function.

    //Constructor with Arguments:
    protected LNode(int num) throws FileNotFoundException {
       super(num);//Call the parent class constructor.
        next = null;//Set the next pointer to NULL.
    }

    //Left child setter:
    public void setNext(LNode a_node){
        this.next = a_node;
    }

    //Right child getter:
    public LNode getNext(){
        return this.next;
    }

    //Find match function, intended to match the entire node object returned from the BST to Services in the Search List.
    //Syntax is correct. I wanted to avoid another traversal of the BST, did not have time to test and fully implement.
    //There is a match and delete function implemented, this one would produce greater accuracy for the client.
    protected int findMatch(Node[] postings) throws FileNotFoundException {
        int num = super.findMatch(postings);//Call the super class find match.
        return num;//Return the index of this array for deletion.
    }
}

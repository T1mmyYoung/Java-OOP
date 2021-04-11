//Timothy Young, CS202, Program 4:
//This file contains the node class for an AVL tree. An AVL tree is a balanced tree,
//so the Node class has a few more data members than usually experienced. Height is needed
//to balance the tree, and is also useful for calculating the time complexities of recursive
//tree algorithms implemented in BinaryTree.java. Setters/Getters are needed to access the Node
//data members without having a direct inheritance relationship. A "has a" relationship is required
//to contain multiple node objects, so we cannot directly extend binary tree from the node class.
//For this reason setters/getters are utilized. The OO framework is still preserved, the application
//is still following the style requirements for OO programming.

package Program4;
//Program 4//////////////////////////////////////////////////////////
import java.io.FileNotFoundException;//Needed to open text file.

class Node extends Info {

    protected Node left_child;//Left child reference.
    protected Node right_child;//Right child reference.

    protected String org;
    protected int count;
    protected int height;//Height of the tree.
    protected int key;//Priority = key, used to sort the tree.

    protected Node(Info obj) throws FileNotFoundException {
        super(obj.name,obj.location,obj.priority);
        this.left_child = null;//Set both children to null at beginning of allocation.
        this.right_child = null;
    }

    protected void display(Info info){
        super.display();
        if(this.name.equals("Agriculture")){
        System.out.println("Organization: " + this.org + "FoodCount:" + this.count);
        System.out.println();
        }
        if(this.name.equals("Healthcare")) {
            System.out.println("Provider: " + this.org + "Wait-time:" + this.count + "hrs");
            System.out.println();
        }
        if(this.name.equals("Delivery")) {
                System.out.println("Service: " + this.org + "Cost:" + this.count + "$");
                System.out.println();
        }
    }


    //Left child setter:
    public void setLeft(Node left_child){
        this.left_child = left_child;
    }

    //Right child setter:
    public void setRight(Node right_child){
        this.right_child = right_child;
    }

    //Right child getter:
    public Node getRight(){
        return this.right_child;
    }

    //Left child getter:
    public Node getLeft(){
        return this.left_child;
    }
}

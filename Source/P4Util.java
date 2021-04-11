//PROGRAM 4///////////////////////////////////////////////////////
//UTILITY CLASS PROGRAM 4/////////////////////////////////////////
package Program4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Containing the scanner methods for input/output.
public class P4Util {
    protected Scanner a_scan,ag,health,deliv;//Scanner objects.

    File type1 = new File("type1.txt");//Agriculture text file.
    File type2 = new File("type2.txt");//Healthcare text file.
    File type3 = new File("type3.txt");//Delivery text file.

    protected P4Util() throws FileNotFoundException {
        a_scan = new Scanner(System.in);//Input froom terminal.
        ag = new Scanner(type1);//Read from ag text file.
        health = new Scanner(type2);//Read from health text file.
        deliv = new Scanner(type3);//Read from Delivery text file.
    }
    //Again function needed for control loop.
    protected boolean Again() {
        System.out.println("Would you like to enter another(Y/N)?:");
        String resp;
        resp = a_scan.next();//Input response character from terminal.
        a_scan.nextLine();

        if(resp.toUpperCase().equals("Y"))//Cast the response to uppercase for character check.
            return true;
        return false;//Return false otherwise.
    }

    public static class BinaryTree extends P4general_tree {

        protected Node root;//A binary tree has many nodes!!!

        //Default Constructor:
        public BinaryTree() throws FileNotFoundException {
            this.root = null;
        }

        //Save Height function(Save the Height as needed):
        protected int saveHeight(Node a_node) {
            return a_node == null ? -1 : a_node.height;
        }

        //Adjust the height as needed:
        protected void newHeight(Node a_node) {
            a_node.height = 1 + Math.max(saveHeight(a_node.left_child), saveHeight(a_node.right_child));
        }

        //Calculate the balance factor:
        protected int getBFactor(Node c) {
            return (c == null) ? 0 : saveHeight(c.right_child) - saveHeight(c.left_child);
        }

        //Right rotation:
        protected Node rightRotate(Node b) {
            Node a = b.getLeft();//Get left child.
            Node c = a.getRight();//Get right child.
            a.right_child = b;//Swap
            b.left_child = c;//Swap
            newHeight(b);//Calculate new height.
            newHeight(a);
            return a;
        }

        //Left rotation:
        protected Node leftRotate(Node b) {
            Node a = b.getRight();//Get right child
            Node c = a.getLeft();//Get left child
            a.left_child = b;//Swap left and right
            b.right_child = c;//Swap left and right.
            newHeight(b);//Calculate new height.
            newHeight(a);
            return a;
        }

        //Rebalance the tree:
        protected Node reBalance(Node c) {
            newHeight(c);//Calculate height.
            int balance = getBFactor(c);//Set balance factor.
            if (balance > 1) {
                if (saveHeight(c.right_child.right_child) > saveHeight(c.right_child.left_child))//Check height
                    c = leftRotate(c);//Perform a left rotation.
                else {
                    c.right_child = rightRotate(c.right_child);//Check height
                    c = rightRotate(c);//Perform a right rotation.
                }
            } else if (balance < -1) {
                if (saveHeight(c.left_child.left_child) > saveHeight(c.left_child.right_child))//Check height.
                    c = rightRotate(c);//Perform a right rotation.
                else {
                    c.left_child = leftRotate(c.left_child);
                    c = rightRotate(c);//All else, left and right rotation.
                }
            }
            return c;
        }



        //Insert a node- Overloaded function.
        protected Node build_tree(Node a_node, int key, char response, Info info) throws FileNotFoundException {
            if (a_node == null) {//Base case:
                Node temp = new Node(info);
                if(info instanceof Agriculture){
                Agriculture to_add = temp.setFacility(response,info);
                temp.org = to_add.organization;
                temp.count = to_add.count;
                }

                if(info instanceof Healthcare) {
                    Healthcare to_add = temp.setHealth(response, info);
                    temp.org = to_add.provider;
                    temp.count = to_add.wait_time;
                }

                if(info instanceof Delivery) {
                    Delivery to_add = temp.setDeliv(response, info);
                    temp.org = to_add.retailer;
                    temp.count = to_add.cost;
                }
                return temp;
            } else if (a_node.key > key)//Insert on the left if less than.
                a_node.left_child = build_tree(a_node.left_child, key, response, info);
            else if (a_node.key < key)//Insert on the right if greater than.
                a_node.right_child = build_tree(a_node.right_child, key, response, info);
            else
                throw new RuntimeException("Please enter a non-duplicate key");//Throw an exception for duplicate keys.
            return reBalance(a_node);//Rebalance after building to maintain the balanced tree!!
        }



        //Build tree function- Overloaded
        //Same as above, except this function considers a brand new list only consisting of root.
        protected Info build_tree(int key, char response, Info info) throws FileNotFoundException {
            root = build_tree(this.root,key,response,info);
            return root;
        }

        //Wrapper method for recursive display.
        protected void display_tree(Info obj){
            display_tree(this.root,obj);
        }

        //Recursive Method for inorder display:
        protected void display_tree(Node a_node, Info obj) {
            if (a_node != null) {

                if (a_node.left_child != null)
                    display_tree(a_node.left_child,obj);

                    a_node.display(obj);//Left,Display,right == inorder

                if (a_node.right_child != null)
                    display_tree(a_node.right_child,obj);
            }
        }
        //Method to calculate the left most node and return this to node to the caller(Needed for delete.)
        protected Node leftMost(Node a_node){
            if(a_node.left_child == null)
                return a_node;

            return leftMost(a_node.left_child);
    }

    //Delete a node recursivley by key search:
        protected Node delete_search(Node a_node, int key) {
            if(a_node == null)
                return a_node;//Simple base case.
            else if (a_node.key > key)
                a_node.left_child = delete_search(a_node.left_child,key);//Left recursive call.
            else if(a_node.key < key)
                a_node.right_child = delete_search(a_node.right_child,key);//Right recursive call.
            else{
                if(a_node.left_child == null || a_node.right_child == null){//All else(No children)
                    a_node = (a_node.left_child == null) ? a_node.right_child : a_node.left_child;
            }
                else{//Calculating the leftMost right child in the tree:
                    Node leftMost = leftMost(a_node.right_child);
                    a_node.key = leftMost.key;//Set the key.
                    a_node.right_child = delete_search(a_node.right_child, a_node.key);//Right recursive call.
                }
            }
            if(a_node != null)
                a_node = reBalance(a_node);//Rebalance the tree if it isn't empty!
            return a_node;
        }

        //Not actually implemented(Needed for ABC interface):
        protected void setFacility(char response) {}

        @Override
        protected Agriculture setFacility(char response, Info info) throws FileNotFoundException {
            return null;
        }

        @Override
        protected Healthcare setHealth(char response, Info info) {
            return null;
        }

        @Override
        Delivery setDeliv(char response, Info info) {
            return null;
        }

        //Not actually implemented(Needed for ABC interface).
        void display(Info info) {}

    }
}

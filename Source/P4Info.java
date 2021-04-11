//Timothy Young, CS202, Program #4
//PROGRAM 4/////////////////////////////////////////////////////////////////////////////////////////////////////
//This file contains the single inheritance hierarchy required for program 4.
//The utility class(Scanner Methods), is at the top of the hierarchy. The reasoning..
//for this is so that all derived classes of Utility will have access to the scanner..
//methods needed for terminal input/output and reading from text files. The abstract..
// base class extends from Utility, acting as the interface for the hierarchy. Extending..
//from general_tree is the Info class managing the general service information. Info has..
//three children making up the three different types of services offered.

//Requirements Met:
//1. Inheritance hierarchy of at least five classes, three in a hierarchy, not being your data structure:
// --Utility-"is a"->General Tree-"is a"-> Info-"is a"-> Agriculture and Healthcare and Delivery

//2. The application using the hierarchy must be in a class on its own(BinaryTree.Java).
//3. Create at least one ABC(General-tree).
//4. Implement One Constructor with Arguments. Please see line 87 of P4Info.java or check main.
//5. Implement Two Methods with function overloading.
//..I thought she said two Overriden functions.. I only did one overloaded function..build_tree(different args).

//6. Implement Member Overriding using Dynamic Binding.
// ..Five Overriden Member functions(Please see Abstract Base Class below).

//7. Try the Super Keyword to invoke a base class constructor.
// ..please see line 174 of P4Info.java.
//8. NO PUBLIC DATA.

//Summary: Missing one overloaded function.

package Program4;
//PROGRAM 4/////////////////////////////////////////////////////////////////
import java.io.FileNotFoundException;//Exception for opening text files.
//The info class makes up the general service information:
class Info extends P4general_tree {

    protected String name;//Service Name
    protected String location;//Service Location
    protected int priority;//Priority used for AVL node key.



    //Constructor taking multiple arguments.
    protected Info(String name, String location, int priority) throws FileNotFoundException
   {
       this.name = name;//Set name
       this.location = location;//Set location
       this.priority = priority;//Set Priority(Key).
   }


    protected void set(Info info){
        this.name = info.name;
        this.location = info.location;
        this.priority =info.priority;
    }

    @Override
    protected Agriculture setFacility(char response, Info info) {

        if(info instanceof Agriculture) {
            Agriculture ag = (Agriculture) info;
            ag = ag.setFacility(response, info);
            return ag;
        }
        return null;
    }

    @Override
    protected Healthcare setHealth(char response, Info info) {
        if(info instanceof Healthcare) {
            Healthcare health = (Healthcare) info;
            health = health.setHealth(response, info);
            return health;
        }
        return null;
    }

    @Override
    Delivery setDeliv(char response, Info info) {
        if (info instanceof Delivery) {
            Delivery deliv = (Delivery) info;
            deliv = deliv.setDeliv(response, info);
            return deliv;
        }
        return null;
    }

    @Override
    //Needed for Abstract Base Class interface. Never actually implemented here.
    protected Info build_tree(int key, char response, Info info) {
        return info;
    }

    @Override
    protected void display_tree(Info obj) {
    }
    /*//Needed for ABC.
    protected void display_tree(Info info,Info info1, Info info2) {}
    */

    //Needed for ABC interface. Never actually implemented here.
    @Override
    protected Node build_tree(Node a_node, int key, char response, Info info) {
        return null;
    }

    protected void display() {
        System.out.print("Name: " + this.name);
        System.out.print("Location: " + this.location);
        System.out.println("Priority: " + this.priority);
    }

    //Display the general information and call a child display using downcasting.
    //Used for checking Object Casts, Binary Tree Display Search:
    protected void display(Info info){
        System.out.print("Name: " + info.name);
        System.out.print("Location: " + info.location);
        System.out.println("Priority: " + info.priority);

        //Checking type cast for an info object of type agriculture.
        if(info instanceof Agriculture && info.name.equals("Agriculture")){
            Agriculture ag = (Agriculture) info;//If the cast is safe, cast an ag object from Info.
            ag.display(info);//Call the child display using the safely casted object.
        }

        //Checking type cast for an info object of type healthcare.
        if(info instanceof Healthcare && info.name.equals("Healthcare")) {
            Healthcare health = (Healthcare) info;
            health.display(info);
        }
        //Checking type cast for an info object of type delivery.
        if(info instanceof Delivery && info.name.equals("Delivery")) {
            Delivery delivery = (Delivery) info;
            delivery.display(info);
        }
    }
}

//Agriculture is the first service type:
class Agriculture extends Info {

    protected String organization;//Sponsor name
    protected int count;//Count of people that can be served/food available.

    //Constructor using super to call a base class constructor.
    protected Agriculture(String name, String loc, int n) throws FileNotFoundException {
        super(name,loc,n);//Calling the base class copy constructor.
        this.organization = new String();//Initialize by allocating dynamic memory for organization.
        this.count = 0;
        /*System.out.println("Reading the Agriculture Service Posts from the text file: ");
        this.organization = ag.nextLine();//Read a line from a text file of type string.
        this.count = ag.nextInt();//Read a integer from a text file.*/
    }


    protected Agriculture setFacility(char response, Info info){
        super.set(info);
        System.out.println("Reading the Agriculture Service Posts from the text file: ");
        this.organization = ag.nextLine();//Read a line from a text file of type string.
        this.count = ag.nextInt();//Read a integer from a text file.
        return this;
    }

    protected Healthcare setHealth(char response, Info info){
       return null;
    }

    protected Delivery setDeliv(char response, Info info){
        return null;
    }



    //Overridden display function!
    protected void display(Info info){
        //super.display(info);
        System.out.print("Organization: " + organization);
        System.out.println("Amount of people this stock can supply: " + count);
    }

}

//Second service type extending from the Info class.
class Healthcare extends Info {

    protected String provider;//Provider name
    protected int wait_time;//Approximate wait time(hours).

    //Constructor

    protected Healthcare(String name, String loc, int n) throws FileNotFoundException {
        super(name,loc,n);//Calling the base class copy constructor.
        this.provider = new String();//Initialize by allocating dynamic memory for organization.
        this.wait_time = 0;
        /*System.out.println("Reading the Agriculture Service Posts from the text file: ");
        this.organization = ag.nextLine();//Read a line from a text file of type string.
        this.count = ag.nextInt();//Read a integer from a text file.*/
    }


    protected Agriculture setFacility(char response, Info info) {
        return null;
    }

    protected Healthcare setHealth(char response, Info info){
        super.set(info);
        System.out.println("Reading the Healthcare Service Posts from the text file: ");
        this.provider = health.nextLine();//Read a line from a text file of type string.
        this.wait_time = health.nextInt();//Read a integer from a text file.
        return this;
    }

    protected Delivery setDeliv(char response, Info info){
       return null;
    }



    //Display a healthcare object:
    protected void display(Info info){
        //super.display(info);//Calling the parent class display.
        System.out.print("Provider:\t" + provider);//Display provider.
        System.out.println("Wait Time:\t" + wait_time);//Display wait_time.
    }

}

//The delivery class is the third service type extending from Info:
class Delivery extends Info {

    protected String retailer;//Name of the delivery company.
    protected int cost;//Delivery fee(USD).

    //Constructor
    protected Delivery(String name, String loc, int n) throws FileNotFoundException {
        super(name,loc,n);//Calling the base class copy constructor.
        this.retailer = new String();//Initialize by allocating dynamic memory for organization.
        this.cost = 0;
        /*System.out.println("Reading the Agriculture Service Posts from the text file: ");
        this.organization = ag.nextLine();//Read a line from a text file of type string.
        this.count = ag.nextInt();//Read a integer from a text file.*/
    }

    protected Agriculture setFacility(char response, Info info){
        return null;
    }
    protected Healthcare setHealth(char response, Info info){
        return null;
    }

    protected Delivery setDeliv(char response, Info info){
        super.set(info);
        System.out.println("Reading the Delivery Service Posts from the text file: ");
        this.retailer = deliv.nextLine();//Read a line from a text file of type string.
        this.cost = deliv.nextInt();//Read a integer from a text file.
        return this;
    }


}

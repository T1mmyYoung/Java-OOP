//Timothy Young, CS202, Program 5
//This file contains the major hierarchy managing the data that makes up a service query. As required by the client
//there is a single inheritance hierarchy utilizing dynamic binding. The four classes making up this hierarchy are:
//input,general_info,service_info, and services The input class acts as a utility class and manages reading from text
//files, and input from the user. The general_info class makes up the basic information for a query(name,location). The
//service_info class directly extends from general and inherits the base class members. The services class contains a
//"has a" relationship with the service_info class, managing multiple objects of this type. Services extends from the
//abstract base class general_list which acts as the interface for this application. This extends into the data..
//structure, please see Lnode.Java and List.Java for the data structures extending this hierarchy.

package Program4;//Needed to access PROGRAM4/BST

//Program 5////////////////////////////////////////////////////////////////////
import java.io.File;//Input/Output TextFile
import java.io.FileNotFoundException;//Read from file exception.
import java.util.Scanner;//Scanner utility library.


//Input class acts as the utility manager for the hierarchy:
//Any class extending from Input inherits the scanner methods and objects to read from text files and the user.
class Input{
    protected Scanner a_scan,lowgeninfo,medgeninfo,highgeninfo,lowservice,medservice,highservice;//Scanner objects.

    File low1 = new File("low1.txt");//Low priority file.(Gen)
    File low2 = new File("low2.txt");//Low priority file.(Service)

    File med1 = new File("med1.txt");//Medium priority file.(Gen)
    File med2 = new File("med2.txt");//Medium priority file.(Service)

    File high1 = new File("high1.txt");//High priority file.(Gen)
    File high2 = new File("high2.txt");//High priority file.(Service)


    protected Input() throws FileNotFoundException {
        a_scan = new Scanner(System.in);//Input from terminal.
        lowgeninfo = new Scanner(low1);//Read from low priority text file.(Gen)
        lowservice = new Scanner(low2);//Read from low priority file.(Service)
        medgeninfo = new Scanner(med1);//Read from med priority file.(Gen)
        medservice  = new Scanner(med2);//Read from med priority file.(Service)
        highgeninfo = new Scanner(high1);//Read from high priority file.(Gen)
        highservice  = new Scanner(high2);//Read from high priority file.(Service)
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

}

//The general_info class manages the basic service information for a search query:
class General_info extends general_list {

    protected String name;//Name of the service
    protected String location;//Location of the service

    //Default Constructor:
    protected General_info() throws FileNotFoundException {
        name = new String();//Allocate a new string.
        location = new String();//Allocate a new string.
    }

    protected General_info low(int num){
        if(num == 1) {//Read low priority services from text file:
            System.out.println("Reading the general information from the text file:(To Search) ");
            this.name = lowgeninfo.nextLine();//Read a line from a text file of type string.
            this.location = lowgeninfo.nextLine();//Read a integer from a text file.
        }
        if(num ==2){//Read med priority services from text file:
            System.out.println("Reading the general information from the text file:(To Search) ");
            this.name = medgeninfo.nextLine();//Read a line from a text file of type string.
            this.location = medgeninfo.nextLine();//Read a integer from a text file.
        }
        if(num == 3){//Read high priority services from text file:
            System.out.println("Reading the general information from the text file:(To Search) ");
            this.name = highgeninfo.nextLine();//Read a line from a text file of type string.
            this.location = highgeninfo.nextLine();//Read a integer from a text file.
        }

        return this;
    }
    //Display the general information(Name/Location):
    protected void display(){
        System.out.println("Name: " + this.name + "\tLocation: " + this.location);
    }

    //Match function. Another approach to this design was to to have a BST traversal function that returns a matching
    //node. Then pass in this node to search the list:This would require two traversal and this application is on a
    //time crunch so it has not been fully implemented/tested. This would produce a better search result for the client.
    protected boolean match(Node postings){
        if(postings.name.equals(this.name) && postings.location.equals(this.location));
        return true;
    }
    //Needed for the abstract base class/dynamic hierarchy:
    @Override
    protected int findMatch(Info[] postings) {
        return 0;
    }
    @Override
    protected void build_list() throws FileNotFoundException {}
    @Override
    protected void display_list() {}
}

//The service info class manages the second set of query information(Eventholder/Count):
//A service_info obj "is a" general_info obj:
class Service_info extends General_info{//extends superclass

    protected String arena;//Eventholder/Company
    protected int count;//Number of items/Wait_time/Cost
    //Program 4 specializes these items. Program 5 is just a basic search.

    protected Service_info() throws FileNotFoundException {
        arena = new String();//Allocate a new string.
        count = 0;//Initialize to zero.
    }

    //Constructor with arguments, initialize from a text file:
    protected Service_info low(int num){
        super.low(num);
        if(num == 1) {// 1 == low priority text file:
            System.out.println("Reading the Service information from the text file(To Search):");
            this.arena = lowservice.nextLine();//Next string read
            this.count = lowservice.nextInt();//Next integer read
        }
        if(num == 2) {//2 == med priority text file:
            System.out.println("Reading the Service information from the text file(To Search):");
            this.arena = medservice.nextLine();//Next string read
            this.count = medservice.nextInt();//Next integer read
        }
        if(num == 3) {//3 == high priority text file:
            System.out.println("Reading the Service information from the text file(To Search):");
            this.arena = highservice.nextLine();//Next string read
            this.count = highservice.nextInt();//Next integer read
        }
        return this;
    }

    //Display the Eventholder/Company and the count/cost/wait_time:
    protected void display(){
        super.display();
        System.out.println("Franchise: " + this.arena + "\tNumber of items/wait-time/cost: " + this.count);
    }

    //Find a match in the list using a returned node from a BST traversal. Implemented but not yet functional.
    //This would produce a search result with greater accuracy for the client.
    protected boolean match(Node postings){
        super.match(postings);
        if(postings.org.equals(this.arena) && postings.count == this.count);
        return true;
    }

}

//The services class manages a collection of service_info objects:
class Services extends general_list{

    protected Service_info[] service_list;//"Has a" relationship required for multiple objects.
    protected int num_services = 1;//Seeded at 1 for test implementation. We can set this equal to the number
    //of lines in the text file using a scanner utility function.

    //Constructor to allocate initial array:
    //Takes one argument:
    protected Services(int num) throws FileNotFoundException {
        int count = 0;
        this.service_list = new Service_info[num_services];
        for(int i = 0; i < num_services; i++) {
            this.service_list[i] = null;
        }
        //Missing a scanner utility function to read last line of file to store into a integer for array size.
        if(num == 1) {
            //while (lowservice.hasNextLine()) {//Endless loop. Ran out of time to implement for-loop and scanner function.
            this.service_list[count] = new Service_info();//Allocate a new service info object at this index.
            this.service_list[count] = service_list[count].low(num);//Set this array equal to the object, calling the low function.
            num_services++;//Set num_services to last index of text file instead of while loop w/ increment.
                            //Scanner function will find the last line and place this into num_services. Use for loop.
        }
        //Number needed to seed which text file to read from in super class constructor.
        if(num == 2) {
            //while (medservice.hasNextLine()) {
            service_list[count] = new Service_info();
            service_list[count] = service_list[count].low(num);
            num_services++;
        }
        //Number needed to seed which text file to read from in super class constructor.
        if(num == 3){
            //while(highservice.hasNextLine()){
            service_list[count] = new Service_info();
            service_list[count] = service_list[count].low(num);
            num_services++;
        }

    }

    //Display the entries in the array:
    protected void display(){
        for(int i = 0; i < num_services-1; i++){
            if(service_list[i] !=null)
                service_list[i].display();//Calling the matching superclass display.
        }
    }


    //Find a matching object from the BST, basic match.
    protected int findMatch(Info[] postings) throws FileNotFoundException {
        String response = new String();
        for(int i = 0;i < num_services-1;i++) {
            if (service_list[i].name.equals(postings[i].name) && service_list[i].location.equals(postings[i].location)){
                System.out.println("We found this entry in the tree: \n");
                service_list[i].display();//Display the entry found.
                System.out.println("\nWould you like to choose this service and delete it from your search query?(Y/N)");
                response = a_scan.next();//Delete(Yes/No)
                if(response.toUpperCase().equals("Y")) {
                    this.service_list[i] = new Service_info();//Only way to to remove without resizing the list.
                    this.service_list[i] = null;//Set to null. Resize list after deletion with a function instead.
                    return i;                   //Note: Resizing the array will require making a copy.
                }
            }
        }
        return 1;
    }

    //Find a matching object returned from the BST:
    protected boolean match(Node postings) throws FileNotFoundException {
        Service_info a_service = new Service_info();
        a_service.match(postings);
        return true;
    }
    //Overridden member functions needed for hierarchy:
    //Not actually implemented..
    @Override
    protected void build_list() throws FileNotFoundException {}
    @Override
    protected void display_list() {}
    @Override
    protected General_info low(int num)
    {
        return null;
    }
}


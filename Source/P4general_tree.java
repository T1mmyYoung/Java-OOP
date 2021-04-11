//Timothy Young, CS202, Program4:

package Program4;
//Program 4////////////////////////////////////////////////
import java.io.FileNotFoundException;

//Abstract Base Class Managing the Hierarchy:
public abstract class P4general_tree extends P4Util {
    protected P4general_tree() throws FileNotFoundException {
    }

    abstract protected Node build_tree(Node a_node, int key, char response, Info info) throws FileNotFoundException;//Insert.
    abstract protected Agriculture setFacility(char response, Info info) throws FileNotFoundException;
    abstract protected Healthcare setHealth(char response, Info info);
    abstract Delivery setDeliv(char response, Info info);

    abstract void display(Info info);//Overridden display.
    abstract protected Info build_tree(int key, char response, Info info) throws FileNotFoundException;//Build tree.
    abstract protected void display_tree(Info obj);
}

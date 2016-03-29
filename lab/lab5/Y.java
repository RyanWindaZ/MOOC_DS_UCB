public class Y extends X implements print{
    
    /*
        1.If the "return value" and "parameter" are different from interface,
        compile error: class Y is not abstract and not override abstract method
        2.The parameter name of interface method is not important. The code compile
        and run correctly even if the parameter name is wrong.
    */
    // interface method
    public void printNum(int num) {
        System.out.println(num);
    }
    
    // superclass method
    public void printName() {
        //override superclass method
        System.out.println("It's subclass Y!");
        
        //call superclass method
        //super.printName();
    }

    public static void main (String[] args) {
        
        //----------------------//
        // Part 3               //
        //----------------------//
        /*
            PARA is the same name in the "interface" and "superclass" of Y.
            PARA is ambiguous that no matter what the value of PARA is the same or not in the
            interface and superclass. This line will be compile error.
        */
        //System.out.println(PARA);  
        
        // can print the same PARA name from superclass and interface
        System.out.println(X.PARA);
        System.out.println(print.PARA);
        
        // call subclass method even cast to superclass
        Y y = new Y();
        ((X)y).printName();
        
        // run-time error because we can't cast the superclass variable to the subclass.
        //X x = new X();
        //((Y)x).printName();
        
        /*
            Can't call superclass method.
            The only way for the subclass to call the superclass method is use super.method
            in the overriden method in the subclass.
        */
        //Y y = new Y();
        //y.printName();
    }
}
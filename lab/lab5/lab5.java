public class lab5 {
    public static void main (String[] args) {
        
        X x = new X();
        Y y = new Y();//subcalss of X

        // compile-time error because x is not calss Y
        //y = x;
        // compile-time check static type: (y)x is class Y, so correct.
        // run-time check dynamic type: x is not class Y, so error.
        //y = (Y)x;
        
        //----------------------//
        // Part 1               //
        //----------------------//
        X[] xa = new X[2];
        xa[0] = new X();
        xa[1] = new X();
        
        Y[] ya = new Y[2];
        ya[0] = new Y();
        ya[1] = new Y();
        
        // run-time error
        //ya = (Y[])xa;
        
        // static type and dynamic type are correct
        //xa = ya;
        //ya = (Y[])xa;
        
        //----------------------//
        // Part 2               //
        //----------------------//
        y.printNum(1);
    }
}
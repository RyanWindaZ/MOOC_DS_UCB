import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Nuke2 {
    
    public static void main (String[] args) throws Exception {
        
        BufferedReader keyboard;
        String line;
        
        keyboard = new BufferedReader(new InputStreamReader(System.in));
        line = keyboard.readLine();
        
        //sol 1
        //for(int i = 0; i < line.length(); i++) {
        //    if(i != 1) {
        //        System.out.print(line.charAt(i));
        //    }
        //}
        
        //sol 2
        if(line == null || line.length() < 2) {
            throw new IllegalArgumentException("The word length should be >= 2");
        }
        else {
            System.out.print(line.substring(0,1) + line.substring(2));
        }
    }

}
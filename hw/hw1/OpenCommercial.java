/* OpenCommercial.java */

import java.net.URL;
import java.net.MalformedURLException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

/**  A class that provides a main function to read five lines of a commercial
 *   Web page and print them in reverse order, given the name of a company.
 */

class OpenCommercial {

  /** Prompts the user for the name X of a company (a single string), opens
   *  the Web site corresponding to www.X.com, and prints the first five lines
   *  of the Web page in reverse order.
   *  @param arg is not used.
   *  @exception Exception thrown if there are any problems parsing the 
   *             user's input or opening the connection.
   */
  public static void main(String[] arg) throws Exception {

    BufferedReader keyboard;
    String inputLine, myUrl;

    keyboard = new BufferedReader(new InputStreamReader(System.in));
    /*
    System.in        : keyboard input or other input
    InputStreamReader: a bridge from byte stream to character stream, import java.io.*
    BufferedReader   : from char stream to char, array and line, import java.io.*
    */
        
    System.out.print("Please enter the name of a company (without spaces): ");
    System.out.flush();        /* Make sure the line is printed immediately. */
    inputLine = keyboard.readLine();

    /* Replace this comment with your solution.  */
    myUrl = "http://www." + inputLine + ".com";
    //System.out.println(myUrl);
    WebReader(myUrl);
    
  }
  
  private static void WebReader(String url) {
    
    URL myUrl;
    BufferedReader myUrlLine;
    String[] stringArray;
    
    try {
        myUrl = new URL(url);
        myUrlLine = new BufferedReader(new InputStreamReader(myUrl.openStream()));
        stringArray = new String[5];
        
        //while(myUrl.openStream().read() != -1) { //print all
        for(int i = 4; i >= 0; i--) {
            stringArray[i] = myUrlLine.readLine();
            System.out.println(myUrlLine.readLine());        
        }
        
        for(String line : stringArray) {
            System.out.println(line);
        }
        
    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
  }
  
}

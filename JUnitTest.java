import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.Collections;


public class JUnitTest {

  //Enter expected output for user
  public static String expectedOutput = 
    "krma\nMARK\n" +
    String.format("%3c%-3C%3c%-3C", 'm', 'a', 'r', 'k');

  // Variable from class testing
  public static String output = "";

  public static void main(String[] args) {
    if(args != null) {
      if(args.length == 1) output += args[0];
    }

    System.out.println(output);

    //Format testing method
    testingSuite();

    JUnitCore junit = new JUnitCore();
    junit.addListener(new CustomExecutionListener());
    Result result = junit.run(JUnitTest.class);

    testingComplete();
  }

  // Checking for correct method name and the paramter amount and type. Parameter order matters
  
  public void checkMethodExists() 
  {
    boolean methodExists = true;
    try 
    {

      // Enter method header in String for checking 
      ArrayList<String> specifiedHeaders = new ArrayList<>();
      specifiedHeaders.add("public static void Main.main(java.lang.String[])");
      specifiedHeaders.add("public static void Main.printCS()");
      

      //Grabs methods in main class
      ArrayList<Method> actualHeaders = new ArrayList<>();
      Collections.addAll(actualHeaders, Main.class.getDeclaredMethods());

      // String stringHeaders = actualHeaders.toString();
      // System.out.println(stringHeaders);

      for(int i = 0; i < specifiedHeaders.size(); i++) 
      {
        // System.out.println(specifiedHeaders.get(i));
        if(!actualHeaders.toString().contains( specifiedHeaders.get(i) )) 
        {
          System.out.println("Does not contain " + specifiedHeaders.get(i));
          methodExists = false;
        }
      }
      
    } catch(Exception e) { }

    assertTrue(methodExists);
  }

  // I/O testing for console. Checks if output is exact.
  @Test
  public void ioStrictTestCase() {
    // Personal Check Starts
    String[] expLines = expectedOutput.split("\n");
    String[] userLines = output.split("\n");

    for(int i = 0; i < expLines.length; i++) {
      if(!expLines[i].equals(userLines[i])) {
        System.out.println("Expected: " + expLines[i]);
        System.out.println("Actual: " + userLines[i]);  
      }
    }
    // Personal Check Ends

    assertEquals(expectedOutput, output);
    System.out.println("\033[1;32mPassed!" + "\u001B[00m");
  }
  
  // I/O testing for console. Disregards case and whitespace when checking output. Flexible.
  
  public void ioSimpleTestCase() {
    assertTrue(output.contains(expectedOutput));
    System.out.println("\033[1;32mPassed!" + "\u001B[00m");
  }

  // I/O Regex testing for console.  

  public void ioRegexTestCase() {
    boolean b3 = Pattern.matches("\\w+", output); //Checks output is a word of any kind.
    assertTrue(b3);
    System.out.println("\033[1;32mPassed!" + "\u001B[00m");
  }

  public static void testingSuite()
  {
    System.out.println("\n*********************************\nTESTING...\n*********************************");
  }

  public static void testingComplete() {
    System.out.println("\n*********************************\nTESTING COMPLETE\n*********************************");
  }


}
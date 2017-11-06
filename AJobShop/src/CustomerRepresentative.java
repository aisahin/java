
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;




//***************************************************************************************************************************************************

public class CustomerRepresentative extends Employee
{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    
    @Override
    public void spendTime(int minMilliseconds, int maxMilliseconds) {
        super.spendTime(minMilliseconds, maxMilliseconds); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void talk(String format, Object... args) {
//        super.talk(format, args); //To change body of generated methods, choose Tools | Templates.
        synchronized ( System.out )  { System.out.printf( ANSI_PURPLE + format + "%n" , args ) ;  System.out.flush() ; }
    }
  //=================================================================================================================================================
    
  private int numberOfProductRequests ;
//  private static final AtomicInteger ones = new AtomicInteger();
  private final int passendNumber;
  //=================================================================================================================================================

  public CustomerRepresentative ( String name , JobShop jobShop )
  {
    super( name , jobShop ) ;

    title                   = "Representative" ;
    numberOfProductRequests = 0                ;
    passendNumber = 0;

    talk( "%s %s : (Constructor finished)" , title , name ) ;
  }
  //=================================================================================================================================================

private String identifyPartName ( Part part ) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException, NoSuchFieldException, Exception {

    String myString = "";  
    Part [] partArray = jobShop.getNextProductRequest();
    part = null;
    
    for ( Part p : partArray ) {

    part = p;
    p.getClass().getSimpleName();
    Class myClass = part.getClass();
    Field[] myfield  = myClass.getDeclaredFields();
    
    try {
        
        for ( Field f : myfield ) {
            
            f.setAccessible(true);

            if ( "name".equals(f.getName()) && "String".equals( f.getType().getSimpleName() ) ) {
                
                myString += (String) f.get (part);
//                System.out.println( ANSI_RED + "================================ " + f.get (part) + ANSI_RESET ); // A
            }

            else if ( "index".equals(f.getName()) && "int".equals( f.getType().getSimpleName() ) ) {

                if ( (int) f.get (part) == 3 ) {

                    String str = Integer.toString( (int) f.get (part) ) ;
                    str = "C";
//                    System.out.println( ANSI_RED + "================================ " + str + ANSI_RESET ); // C
                    myString += str;
                }
            }

            else if ( "method".equals(f.getName()) && "String".equals( f.getType().getSimpleName() ) ) { 

                if ( "getName".equals( f.get (part) ) ) {
                    
                    Method myMethod = myClass.getDeclaredMethod( (String) (f.get (part)), new Class[] {} );
                    myMethod.setAccessible(true);
//                    System.out.println( ANSI_RED + "================================ " + myMethod.invoke(part)+ ANSI_RESET ); // B 
                    myString += (String) myMethod.invoke(part);
                }

                else if ( "getIndex".equals( f.get (part)) ) {
                    Method myMethods [] = myClass.getDeclaredMethods();
                    for ( Method method : myMethods ) {

                        method.setAccessible(true);
                        int returnValue = (int) method.invoke( part, 1538 );
                        
                        if ( returnValue == 4 ) {

                            String str = Integer.toString( returnValue ) ;
                            str = "D";
//                            System.out.println( ANSI_RED + "================================ " + str + ANSI_RESET ); //D
                            myString += str;
                        }
                    }
                }
            }
        }
    }
    
    catch (SecurityException e) {}        
    }
    return myString;
}
  
//=================================================================================================================================================

public Order nextOrder() {

    return null;
}

//=================================================================================================================================================

@Override
public void run ()  {      

    
    boolean check = true;

    while ( check ) {

        synchronized ( this ) {

           spendTime( 750, 1000 ); //1000, 1200
            
            if ( jobShop.productRequests.isEmpty() && jobShop.clock.getHour() < 17 ) {

                talk( "%s %s :  Checking for a standing product request" , title , name ) ;
                talk( "%s %s :  There are no product requests, so I'm waiting" , title , name );
            }

            else if ( jobShop.clock.getHour() == 17 ) {
                
                check = false;
                talk( "%s %s :  Processed a total of " + numberOfProductRequests + " product requests" , title , name ) ;                
            }

            else if ( !(jobShop.productRequests.isEmpty()) && jobShop.clock.getHour() < 17 ){ 
                
                try {
                    ++numberOfProductRequests;
//                    ones.incrementAndGet();                   
//                    long threadId = Thread.currentThread().getId();
//                    System.out.println( ANSI_RED + "Thread ID : " + "" +  ANSI_RESET );
                    this.talk( "%s %s :  Checking for a standing product request ", title , name ) ;
                    jobShop.addWorkingOrder( new Order( jobShop.generateNewOrderID(), identifyPartName(null) ) );
                    this.talk( "%s %s :  I am adding a new order " + jobShop.workingOrders.peek(), title , name ) ;
                    spendTime( 250, 500 );
//                    System.out.println( ANSI_RED + "================================ " + numberOfProductRequests + ANSI_RESET );
                    
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(CustomerRepresentative.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(CustomerRepresentative.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
  //=================================================================================================================================================
}

//***************************************************************************************************************************************************


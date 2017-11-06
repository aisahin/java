 //***************************************************************************************************************************************************

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random ;
import java.util.logging.Level;
import java.util.logging.Logger;

//***************************************************************************************************************************************************




//***************************************************************************************************************************************************

public class Customer extends Thread implements Person
{    
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    
  //=================================================================================================================================================

  public        String  title                   ;
  public        String  name                    ;
  public        JobShop jobShop                 ;
  //-------------------------------------------------------------------------------------------------------------------------------------------------

  private       int     numberOfProductRequests ;
  private final Random  random                  ;
  public        int     partsCounter            ;
  //=================================================================================================================================================
  
  public Customer ( String name , JobShop jobShop )
  {
    this.title                   = "Customer      " ;
    this.name                    = name             ;
    this.jobShop                 = jobShop          ;
    this.numberOfProductRequests = 0                ;
    this.random                  = new Random()     ;

    talk( "%s %s : (Constructor finished)" , title , name ) ;
  }

  //=================================================================================================================================================

  @Override
  public void talk ( String format , Object ... args )  // This is a synchronized wrapper for printf method
  {
    synchronized ( System.out )  { System.out.printf( ANSI_BLUE + format + "%n" , args ) ;  System.out.flush() ; }
  }

  //=================================================================================================================================================

  @Override
  public void spendTime ( int minMilliseconds , int maxMilliseconds )  // This is a wrapper for Thread.sleep
  {
    int duration = minMilliseconds + (int) ( Math.random() * ( maxMilliseconds - minMilliseconds ) ) ;

    try { Thread.sleep( duration ) ; } catch ( InterruptedException ex ) { /* Do nothing */ }
  }
  
  //=================================================================================================================================================
  
  private Part generateRandomPart () throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {

      
    Class partA = PartA.class;
    Constructor constructorA  = partA.getConstructor();
    Part objA = (PartA) constructorA.newInstance(); 

    Class partB = PartB.class;
    Constructor constructorB  = partB.getConstructor();
    Part objB = (PartB) constructorB.newInstance();

    Class partC = PartC.class;
    Constructor constructorC  = partC.getConstructor();
    Part objC = (PartC) constructorC.newInstance();

    Class partD = PartD.class;      
    Constructor constructorD  = partD.getConstructor();
    Part objD = (PartD) constructorD.newInstance();
    
    switch  ( random.nextInt( 4 ) ) {
    
        case 0:
            return objA;
        case 1:
            return objB;
        case 2:
            return objC;
        case 3:
            return objD;
    }
      return null;
  }

  //=================================================================================================================================================

  private Part [] generateRandomProductRequest () throws ClassNotFoundException, InstantiationException, IllegalAccessException {
      
    int     numberOfParts = 2 + random.nextInt( 4 )   ;
    Part [] parts         = new Part[ numberOfParts ] ;

    for ( int i = 0 ; i < numberOfParts ; i++ )  { 
        try {
            
        parts[ i ] = generateRandomPart() ;
        
        } 
        catch (NoSuchMethodException | IllegalArgumentException | InvocationTargetException ex) {
            
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    partsCounter = numberOfParts;
    return parts ;
  }

  //=================================================================================================================================================

  @Override
  public void run () {
      
    boolean check = true;
    
    while ( check ) {

        spendTime( 500, 750 ); //1000, 1500
        if ( jobShop.clock.getHour() < 17 ) {

            try {
                
                jobShop.addProductRequest( generateRandomProductRequest() );
                talk( "%s %s :  Submitting a product request of " + partsCounter + " parts" , title , name ) ;
                ++numberOfProductRequests;
            }

            catch (IllegalArgumentException | ClassNotFoundException | InstantiationException | IllegalAccessException  ex) {

                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        else if ( jobShop.clock.getHour() == 17 ){           

            talk( "%s %s :  Submitted a total of " + numberOfProductRequests + " product requests to the jobshop ", title , name ) ;
            check = false;
        }
    }
  }
  //=================================================================================================================================================
}

//***************************************************************************************************************************************************


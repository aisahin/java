
import java.util.logging.Level;
import java.util.logging.Logger;

//***************************************************************************************************************************************************

public class Worker extends Employee
{
  //=================================================================================================================================================
    
    private int numberOfPartsAssembled          ;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
  //=================================================================================================================================================

    @Override
    public void spendTime(int minMilliseconds, int maxMilliseconds) {

        super.spendTime(minMilliseconds, maxMilliseconds); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void talk(String format, Object... args) {

    synchronized ( System.out )  { System.out.printf( ANSI_RED + format + "%n" , args ) ;  System.out.flush() ; }
    }

  public Worker ( String name , JobShop jobShop )
  {
    super( name , jobShop ) ;

    title                  = "Worker        " ;
    numberOfPartsAssembled = 0                ;

    talk( "%s %s : (Constructor finished)" , title , name ) ;
  }

  //=================================================================================================================================================

@Override
public void run (){

    boolean check = true;
    
    while ( check ) {
        
        spendTime( 1000, 1250 ); //1500, 2000
        
        synchronized ( this ) {
            
            if ( jobShop.clock.getHour() < 17  && jobShop.workingOrders.isEmpty() ) {
                
                talk( "%s %s :  Checking for a working order" , title , name );
                talk( "%s %s :  There are no working orders, so I'm waiting" , title , name );
            }
            else if ( !( jobShop.workingOrders.isEmpty() ) && jobShop.clock.getHour() < 17 ) {
                
                try {
                    talk( "%s %s :  Checking for a working order" , title , name );
                    Order myOrder = jobShop.getNextWorkingOrder();
                    talk( "%s %s :  Currently working on order " + myOrder, title , name );
                    
                    if ( !myOrder.isCompleted() ) {
                        
                        if ( !jobShop.missingParts.isEmpty() ) {
                        
                            talk( "%s %s :  Cannot work order, reporting missing part "  + jobShop.missingParts.peek(), title , name );
                        }
                        
                        else {
                        
                        myOrder.completeNextRemainingPart();
                        ++numberOfPartsAssembled;
                        talk( "%s %s :  Assembled last part of order "  + myOrder.toString(), title , name );                            
                        }
                    }
                    
                } catch (Exception ex) {
                    Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            else if ( jobShop.clock.getHour() == 17 ){ 

                check = false;
                talk( "%s %s :  Assembled a total of " + numberOfPartsAssembled + " parts " , title , name );
            }
            
        }
    }
}

  //=================================================================================================================================================
}

//***************************************************************************************************************************************************


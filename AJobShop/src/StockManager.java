

//***************************************************************************************************************************************************

public class StockManager extends Employee
{
  //=================================================================================================================================================

    private int numberOfPartsSupplied ;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    @Override
    public void spendTime ( int minMilliseconds, int maxMilliseconds)  {

        super.spendTime(minMilliseconds , maxMilliseconds ) ; //To change body of generated methods, choose Tools | Templates.
    }

    //=================================================================================================================================================
    @Override
    public void talk(String format, Object... args) {
        
//        super.talk(format, args); //To change body of generated methods, choose Tools | Templates.
        synchronized ( System.out )  { System.out.printf( ANSI_GREEN + format + "%n" , args ) ;  System.out.flush() ; }
    }

    public StockManager(String name, JobShop jobShop) {
        
        super( name , jobShop ) ;
        
        title                 = "Stock Manager " ;
        numberOfPartsSupplied = 0                ;
        
        talk( "%s %s : (Constructor finished)" , title , name ) ;
    }

  //=================================================================================================================================================

  @Override
  public void run ()
  {
    // ...
    spendTime( 1500, 2000 ); //5000, 9000 
    boolean check = true;
    
    while ( check ) {
        
        synchronized ( this ) {
        
            if ( jobShop.clock.getHour() < 17  && !jobShop.missingParts.isEmpty() ) {
                
                talk( "%s %s :  Checking for a reported missing part " , title , name ) ;
                talk( "%s %s :  Ordering part " + jobShop.missingParts.poll() , title , name ) ;
                ++numberOfPartsSupplied;
            }
            
            else if (jobShop.clock.getHour() < 17  && jobShop.missingParts.isEmpty()) {
                
                talk( "%s %s :  Checking for a reported missing part " , title , name ) ;
                talk( "%s %s :  There is no missing part" , title , name ) ;  
                spendTime( 500, 1000 ); //1000, 1500
            }

            else if ( jobShop.clock.getHour() == 17 ) {

                talk( "%s %s :  Restocked a total of " + numberOfPartsSupplied + " parts" , title , name ) ;
                check = false;
            }            
        }
    

    }      
  }

  //=================================================================================================================================================
}

//***************************************************************************************************************************************************


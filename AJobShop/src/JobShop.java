
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.Timer;

//***************************************************************************************************************************************************

// import ...

//***************************************************************************************************************************************************




//***************************************************************************************************************************************************

public class JobShop
{
  //=================================================================================================================================================

  public  final Queue< Part []                > productRequests         ;
  public  final Queue< Order                  > workingOrders           ;
  public  final Queue< Order                  > completedOrders         ;
  public  final Queue< String                 > missingParts            ;

  public  final List < CustomerRepresentative > customerRepresentatives ;
  public  final List < Worker                 > workers                 ;
  public  final List < StockManager           > stockManagers           ;

  public        Clock                           clock                   ;
  public        boolean                         isOpen                  ;
  public        DBServices                      db                      ;

  //-------------------------------------------------------------------------------------------------------------------------------------------------

  private       Integer                         nextOrderID             ;
  private final Timer                           timer                   ;

  //=================================================================================================================================================
  
  public JobShop
  (
    List< String> customerRepresentativeNames ,
    List< String> workerNames                 ,
    List< String> stockManagerNames
  )
    throws Exception
  {
    this.productRequests         = new LinkedList<>() ;
    this.workingOrders           = new LinkedList<>() ;
    this.completedOrders         = new LinkedList<>() ;
    this.missingParts            = new LinkedList<>() ;
    this.customerRepresentatives = new ArrayList <>() ;
    this.workers                 = new ArrayList <>() ;
    this.stockManagers           = new ArrayList <>() ;
    this.clock                   = new Clock( 8 , 0 ) ;
    this.isOpen                  = true               ;
    this.db                      = new DBServices()   ;
    this.nextOrderID             = 1                  ;

    db.setPartCount( "A" , 50 ) ;
    db.setPartCount( "B" , 50 ) ;
    db.setPartCount( "C" , 50) ;
    db.setPartCount( "D" , 50 ) ;
    db.setPartCount( "E" , 50 ) ;

    for ( String name : customerRepresentativeNames )  { customerRepresentatives.add( new CustomerRepresentative( name , this ) ) ; }
    for ( String name : workerNames                 )  { workers                .add( new Worker                ( name , this ) ) ; }
    for ( String name : stockManagerNames           )  { stockManagers          .add( new StockManager          ( name , this ) ) ; }

    ActionListener clockTicker = new ActionListener()
                                 {
                                   @Override public void actionPerformed ( ActionEvent e )
                                   {
                                     int [] time   = clock.tick().getTime() ;
                                     int    hour   = time[0]                ;
                                     int    minute = time[1]                ;

                                     isOpen = ( ( hour >= 8 ) && ( hour < 17 ) ) ;

                                     if ( minute % 30 == 0 )  { print() ; }

                                     if ( ( hour == 17 ) && ( minute == 0 ) )
                                     {
                                       synchronized( productRequests )  { productRequests.notifyAll() ; }
                                       synchronized( workingOrders   )  { workingOrders  .notifyAll() ; }
                                       synchronized( missingParts    )  { missingParts   .notifyAll() ; }

                                       timer.stop() ;
                                     }
                                   }
                                 } ;

    timer = new Timer( 50 , clockTicker ) ;

    timer.setInitialDelay( 0 ) ;
    timer.start          (   ) ;

    Thread.sleep( 50 ) ;

    print() ;

    for ( Employee e  : customerRepresentatives     )  { e.start() ; }
    for ( Employee e  : workers                     )  { e.start() ; }
    for ( Employee e  : stockManagers               )  { e.start() ; }
  }

  //=================================================================================================================================================

  /* Suggested */ 
  public synchronized void addProductRequest ( Part [] pr    ) {
      
    productRequests.add( pr );
    this.notify();
  }
  
  /* Suggested */ 
  public synchronized void addWorkingOrder   ( Order   order ) throws Exception {
      
    workingOrders.add( order );
    this.notify();
  }
  
  /* Suggested */ 
  public synchronized void addCompletedOrder ( Order   order ) {
      
    completedOrders.add( order );
    this.notify();
  }
  
  /* Suggested */ 
  public synchronized void addMissingPart    ( String  part  ) { 
      
    missingParts.add(part);
    this.notify();
  }
  
  //=================================================================================================================================================

  /* Suggested */ 
  public  synchronized Part [] getNextProductRequest () {

     Part [] partArray = productRequests.poll();
     this.notify();
     return partArray;
  }
  
  /* Suggested */ 
  public  synchronized Order   getNextWorkingOrder   () throws Exception  {
      
      Order myOrder = workingOrders.poll();
      
      if ( !myOrder.isCompleted() ) {
          
        for ( int i = 0; i < myOrder.nextRemainingPart().length() ; i++ ) {
//            System.out.println("INDEX AT : " + i + " " +  myOrder.nextRemainingPart().substring(i, i+1) );
            if ( db.askDatabes(myOrder.nextRemainingPart().substring(i, i+1)) ) {
            
                db.decrementPartCount( myOrder.nextRemainingPart().substring(i, i+1) );
                
            }
            
            else {
                
                missingParts.add( myOrder.nextRemainingPart().substring(i, i+1) );
                getNextMissingPart    ();
            }
        }
      }
      this.notify();
      return myOrder;      
  }
  
  /* Suggested */ 
  public  synchronized String  getNextMissingPart    () throws Exception  {
      
      db.incrementPartCount(missingParts.peek());
      this.notify();
      return null ;
  }

  //=================================================================================================================================================

  public int generateNewOrderID ()  { synchronized ( nextOrderID )  { return ( nextOrderID++ ) ; } }

  //=================================================================================================================================================

  public void print ()
  {
    synchronized ( System.out )  { System.out.println( this.toString() ) ;  System.out.flush() ; }
  }

  //=================================================================================================================================================

  @Override
  public String toString ()
  {
    synchronized ( this )
    {
      String result = String.format( "-------------------%n"                                                                            +
                                     "JobShop (%s : Time = %s , Employees = < R:%d , W:%d , S:%d > , Orders = < R:%d , W:%d , C:%d >%n" +
                                     ">>>> Inventory    :"                                                                              ,
                                     isOpen ? "Open)   " : "Closed) "                                                                   ,
                                     clock                                                                                              ,
                                     customerRepresentatives.size()                                                                     ,
                                     workers                .size()                                                                     ,
                                     stockManagers          .size()                                                                     ,
                                     productRequests        .size()                                                                     ,
                                     workingOrders          .size()                                                                     ,
                                     completedOrders        .size()                                                                     ) ;

      List< Pair< String , Integer > > inventory = null ;

      try                    { inventory = db.getInventory() ; }
      catch ( Exception e )  { /* Do nothing */                }

      if ( inventory != null )
      {
        for ( int i = 0 ; i < inventory.size() ; i++ )
        {
          Pair< String , Integer > pair = inventory.get( i ) ;

          result += " " + pair.first + " : " + pair.second ;

          if ( i < inventory.size() - 1 )  { result += " ," ; }
        }
      }

      result += "\n-------------------" ;

      return result ;
    }
  }

  //=================================================================================================================================================
}

//***************************************************************************************************************************************************


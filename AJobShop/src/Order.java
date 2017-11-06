//***************************************************************************************************************************************************

import java.util.Queue      ;
import java.util.Arrays     ;
import java.util.LinkedList ;

//***************************************************************************************************************************************************




//***************************************************************************************************************************************************

public class Order
{
    public String ANSI_YELLOW = "\u001B[33m"	;
    public String ANSI_RESET = "\u001B[0m"      ;
  //=================================================================================================================================================
    
  private final int             id             ;
  private final Queue< String > completedParts ;
  private final Queue< String > remainingParts ;

  //=================================================================================================================================================

  public Order ( int id , String ... parts )
  {
    this.id             = id                 ;
    this.completedParts = new LinkedList<>() ;
    this.remainingParts = new LinkedList<>() ;

    remainingParts.addAll( Arrays.asList( parts ) ) ;
//    System.out.println( ANSI_YELLOW + "Order remaining Parts "+ remainingParts + ANSI_RESET );
  }

  //=================================================================================================================================================

  public synchronized boolean isCompleted               ()  { 
  
      return              remainingParts.isEmpty()   ; 
  }
  public synchronized String  nextRemainingPart         ()  {
      
//      System.out.println( ANSI_YELLOW + "Order remaining Parts "+ remainingParts.peek() + ANSI_RESET );
      return              remainingParts.peek();
  }
  public synchronized void    completeNextRemainingPart ()  { 
      
      completedParts.add( remainingParts.poll   () ) ; 
//    System.out.println( ANSI_YELLOW + "Order  completeNextRemainingPart () "+ completedParts.element() + ANSI_RESET );
  }

  //=================================================================================================================================================

  @Override
  public String toString ()
  {
    String result = String.format( "[%03d : " , id ) ;

    for ( String partName : completedParts )  { result += partName ; }    result += "|" ;
    for ( String partName : remainingParts )  { result += partName ; }    result += "]" ;

    return result ;
  }

  //=================================================================================================================================================
}

//***************************************************************************************************************************************************


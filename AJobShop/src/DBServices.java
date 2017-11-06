
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//***************************************************************************************************************************************************

// import ...

//***************************************************************************************************************************************************




//***************************************************************************************************************************************************

public class DBServices
{
  //=================================================================================================================================================

  public  String     driver     ;
  public  String     url        ;
  public  String     database   ;
  public  String     username   ;
  public  String     password   ;

  //-------------------------------------------------------------------------------------------------------------------------------------------------

  private Connection connection ;
  private Statement  statement  ;

  //=================================================================================================================================================

  public DBServices () throws Exception
  {
    driver   = "com.mysql.jdbc.Driver"        ;
    url      = "jdbc:mysql://localhost:3306/" ;
    database = "CENG"                      ;
    username = "root"                         ;
    password = "1234qqqQ"                         ;

    Class.forName( driver ) ;

    connection = DriverManager.getConnection( url + database , username , password ) ;
    statement  = connection.createStatement()                                        ;
  }

  //=================================================================================================================================================

  /* Suggested */ 
  public List< Pair< String , Integer > > getInventory () throws Exception  {  // ...
      
    List<Pair<String, Integer>> myInventory = new ArrayList<Pair<String, Integer>>();
    
    try {

        String sql = "SELECT * FROM inventory";
        ResultSet rs = statement.executeQuery(sql);

        while ( rs.next() ) {

            myInventory.add( new Pair< String, Integer > ( rs.getString(1), rs.getInt(2)) );
        }

    } catch (Exception e) {  } 
    
//    statement.close();
    return myInventory;
  }

  //=================================================================================================================================================

  /* Suggested */ 
  public void setPartCount ( String partName , int partCount ) throws Exception  {
      
    statement = connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE );
    String sql = "SELECT * FROM inventory WHERE Part = " + "'" + partName + "'";
    ResultSet rs = statement.executeQuery(sql);

    while( rs.next() ) {

        if ( rs.getInt( "Count" ) < 50 ) {
         
            int newCount = 50;
            rs.updateDouble( "Count", newCount);
            rs.updateRow();
        }
    }       
  }

  //=================================================================================================================================================

  /* Suggested */ 
  public void incrementPartCount ( String partName ) throws Exception {
      
    statement = connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE );
    String sql = "SELECT * FROM inventory WHERE Part = " + "'" + partName + "'";
    ResultSet rs = statement.executeQuery(sql);

    while( rs.next() ) {

        if ( rs.getInt( "Count" ) == 0 ) {
         
            int newCount = rs.getInt( "Count" ) + 1;
            rs.updateDouble( "Count", newCount);
            rs.updateRow();
        }
    }      
  }

  //=================================================================================================================================================

  /* Suggested */ 
  public void decrementPartCount ( String partName ) throws Exception  {
      
    statement = connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE );
    String sql = "SELECT * FROM inventory WHERE Part = " + "'" + partName + "'";
    ResultSet rs = statement.executeQuery(sql);

    while( rs.next() ) {

        if ( rs.getInt( "Count" ) > 0 ) {

            int newCount = rs.getInt( "Count" ) - 1;
            rs.updateDouble( "Count", newCount);
            rs.updateRow();
        }
    }
  }
  
  public boolean askDatabes (  String partName ) throws SQLException {

    statement = connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE );
    String sql = "SELECT * FROM inventory WHERE Part = " + "'" + partName + "'";
    ResultSet rs = statement.executeQuery(sql);
    
    while( rs.next() ) {
    
        if ( rs.getInt( "Count" ) > 0 ) {
        
            return true;
        }
        else { return false; }
    }
   
    return false;
  }

  //=================================================================================================================================================

  public void close () throws Exception
  {
    if ( statement  != null )  { statement .close() ; }
    if ( connection != null )  { connection.close() ; }
  }

  //=================================================================================================================================================
}

//***************************************************************************************************************************************************


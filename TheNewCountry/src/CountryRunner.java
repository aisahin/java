import javax.swing.JFrame;

public class CountryRunner {
    
    public static Environment environment;
    public static Display    display;

    public static void main(String[] args) throws InterruptedException {
        // TODO implement here
        // Incomplete
        environment = new Environment() ;
        display = new Display   ( environment ) ;

        JFrame frame = new JFrame( "The New Country : Analysis of Terrorist Attacks" ) ;
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ) ;       
       
        frame.add( display )                                   ;
        frame.pack()                                           ;
        frame.setVisible( true )                               ;
        
        while ( true )
        {     
          environment.stepAll ( 2.0 ) ;
          display   .repaint () ;
          Thread    .sleep ( 50 ) ;          
        }        
    }        
}
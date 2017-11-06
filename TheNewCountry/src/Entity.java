
import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Entity {

    public Color         color       ;
    public String        name        ;
    public Position      direction   ;    
    public Position      position    ;
    public double        speed       ;
    public StepStrategy  strategy    ;
  
  //=================================================================================================================================================
    public Entity ( Color myColor, String myName, Position myDirection, Position myPosition, double mySpeed, StepStrategy myStrategy ) {
        
        color       =   myColor     ;
        name        =   myName      ;     
        direction   =   myDirection ;
        position    =   myPosition  ;
        speed       =   mySpeed     ;
        strategy    =   myStrategy  ;        
    }

    public void step( double deltaTime ){
        
        strategy.step( this, deltaTime );
    }
    
    public abstract void draw( Graphics2D g2d );
}
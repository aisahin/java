
import java.awt.Dimension;

public class Position {

    public double x;
    public double y;
    
    public Position( double x, double y ) {
        
        this.x = x;
        this.y = y;        
    }

    public void Position( Dimension d ) {
        
        this.x = d.getWidth();
        this.y = d.getHeight();        
    }

    public void normalize() {
        
        double length = Math.sqrt( Math.pow( this.x, 2 ) + Math.pow( this.y, 2 ) );
        this.x = x / length;
        this.y = y / length;
    }

    public double distanceTo(Position other) {

    double deltaX = x - other.x ;
    double deltaY = y - other.y ;

    return Math.sqrt( (deltaX * deltaX) + (deltaY * deltaY) ) ;
    }

    public Position ( double angleDegrees ) {
        
        double angleRadians = Math.toRadians( angleDegrees ) ;

        this.x = Math.cos( angleRadians ) ;
        this.y = Math.sin( angleRadians ) ;
    } 
    
    public void travel (double speed, Position myDirection, double deltaTime) {
    
        this.x += myDirection.x * speed * deltaTime;
        this.y += myDirection.y * speed * deltaTime;
    }
    
//=================================================================================================================================================
//  Getter and Setter.
    
    public double getX() {
        
        return x;
    }

    public void setX(double x) {
        
        this.x = x;
    }

    public double getY() {
        
        return y;
    }

    public void setY(double y) {
        
        this.y = y;
    }
//=================================================================================================================================================
}
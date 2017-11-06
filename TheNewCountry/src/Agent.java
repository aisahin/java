
import java.awt.Color;
import java.awt.Graphics2D;

public class Agent extends Entity {

    public Agent( Color myColor, String myName, Position myDirection, Position myPosition, double mySpeed, StepStrategy myStrategy ) {
        
        // StepStrategies are SS, MR
        super( myColor,myName, myDirection, myPosition, mySpeed, myStrategy );
    }

    @Override
    public void step(double deltaTime) {
        
        this.strategy.step(this, deltaTime);
    }

    @Override
    public void draw(Graphics2D g2d) {
        
        String entityName = "AGENT";
        g2d.setColor( this.color );
        g2d.drawOval( (int) position.getX(), (int) position.getY(), Environment.diameter/2, Environment.diameter/2 );
        g2d.drawString( entityName , (int) this.position.getX() + 10 , (int) this.position.getY() - 5 );
        g2d.drawString( strategy.getName() , (int) this.position.getX() + 20 , (int) this.position.getY() +80 );
    }
}
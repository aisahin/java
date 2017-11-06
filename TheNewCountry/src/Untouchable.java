
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

public class Untouchable extends Entity {

    public Untouchable( Color myColor, String myName, Position myDirection, Position myPosition, double mySpeed, StepStrategy myStrategy ) {
        
        // StepStrategies ate SS, ML
        super( myColor, myName, myDirection, myPosition, mySpeed, myStrategy );
    }

    @Override
    public void step(double deltaTime) {
        
        this.strategy.step(this, deltaTime);
    }

    @Override
    public void draw(Graphics2D g2d) {
        
        String entityName = "TUBA";
        int diameter = 40;        
        g2d.setColor( this.color );
        g2d.drawRect( (int) position.getX(), (int) position.getY(), diameter, diameter);
        Image tuba = Toolkit.getDefaultToolkit().getImage("src/untouchable.png");
        g2d.drawImage(tuba, (int) position.getX(), (int) position.getY(), diameter, diameter, null);        
        g2d.drawString( entityName , (int) this.position.getX() +5 , (int) this.position.getY() - 5 );
        g2d.drawString( strategy.getName() , (int) this.position.getX() + 10 , (int) this.position.getY() +60 );        
    }
    
}
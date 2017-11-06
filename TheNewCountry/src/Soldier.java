
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

public class Soldier extends Entity {

    public Soldier( Color myColor, String myName, Position myDirection, Position myPosition, double mySpeed, StepStrategy myStrategy ) {
        
        // StepStrategies are SS, ML, P
        super( myColor, myName, myDirection, myPosition, mySpeed, myStrategy );
    }

    @Override
    public void step(double deltaTime) {
        
        this.strategy.step(this, deltaTime);
    }

    @Override
    public void draw(Graphics2D g2d) {
        // TODO implement here
        String entityName = "SOLDIER";
        g2d.setColor( this.color );
        g2d.drawOval( (int) position.getX(), (int) position.getY(), (int) Environment.diameter/2, (int) Environment.diameter/2 );
        Image soldier = Toolkit.getDefaultToolkit().getImage("src/soldier.png");
        g2d.drawImage(soldier, (int) position.getX() + 5, (int) position.getY() + 5, (int) Environment.diameter/2 - 10, (int) Environment.diameter/2 - 10, null); 
        g2d.drawString( entityName , (int) this.position.getX() + 5 , (int) this.position.getY() - 5 );
        g2d.drawString( strategy.getName() , (int) this.position.getX() + 25, (int) this.position.getY() + 80 );        
    }    
}
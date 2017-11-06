
import java.awt.Color;
import java.awt.Graphics2D;

public class Citizen extends Entity {

    public Citizen( Color myColor, String myName, Position myDirection, Position myPosition, double mySpeed, StepStrategy myStrategy ) {
        
        // StepStrategies are SS, MR, ML
        super( myColor, myName, myDirection, myPosition, mySpeed, myStrategy );
    }

    @Override
    public void step(double deltaTime) {
        
        this.strategy.step(this, deltaTime);
    }

    @Override
    public void draw(Graphics2D g2d) {
        // TODO implement here
        String entityName = "CITIZEN";
        int diameter = 15;        
        g2d.setColor( this.color );
        g2d.fillOval( (int) position.getX(), (int) position.getY() , diameter , diameter ); 
        g2d.drawString( entityName , (int) this.position.getX() - diameter , (int) this.position.getY() );
        g2d.drawString( strategy.getName() , (int) this.position.getX() , (int) this.position.getY() + diameter*2 );     
    }
}
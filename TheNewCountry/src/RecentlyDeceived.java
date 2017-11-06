
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

public class RecentlyDeceived extends Terrorist {
    
    Environment environment;
    protected Terrorist decoratedTerrorist;

    public RecentlyDeceived( Color myColor, String myName, Position myDirection, Position myPosition, double mySpeed, StepStrategy myStrategy ) {
        
        super( myColor, myName, myDirection, myPosition, mySpeed, myStrategy );
    }

    @Override
    public void Terrorist(Terrorist t) {
        decoratedTerrorist = t;
    }

    @Override
    public void step(double deltaTime) {
        
        this.strategy.step(this, deltaTime);
    }

    public void RecentlyDeceived(Environment e) {
        // TODO implement here
        environment = e;
    }

    public void draw(Graphics2D g2d) {
        // TODO implement here
        String entityName = "TERRORIST";
        g2d.setColor( this.color );
        g2d.drawOval( (int) position.getX(), (int) position.getY(), Environment.diameter, Environment.diameter );
        Image terrorist = Toolkit.getDefaultToolkit().getImage("src/terrorist.png");
        g2d.drawImage(terrorist, (int) position.getX()+15, (int) position.getY()+5, 100, 100, null); 
        g2d.drawString( entityName , (int) this.position.getX() + 30 , (int) this.position.getY() - 5 );       
    }
}
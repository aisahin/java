
import java.awt.Color;
import java.awt.Graphics2D;

public class Terrorist extends Entity {
    
    protected Terrorist decoratedTerrorist;

    public Terrorist( Color myColor, String myName, Position myDirection, Position myPosition, double mySpeed, StepStrategy myStrategy ) {
        
        // StepStrategies are SS, MR, ML, GP
        super( myColor, myName, myDirection, myPosition, mySpeed, myStrategy );
    }

    public void  Terrorist( Terrorist t ) {
        // TODO implement here
        decoratedTerrorist = t;
    }

    @Override
    public void step(double deltaTime) {
        
        this.strategy.step(this, deltaTime);
    }

    @Override
    public void draw(Graphics2D g2d) {
        // TODO implement here        
    }    
}
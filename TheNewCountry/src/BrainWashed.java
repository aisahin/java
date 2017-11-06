
import java.awt.Color;
import java.awt.Graphics2D;

public class BrainWashed extends TerroristDecorator {

    protected Terrorist decoratedTerrorist;

    public BrainWashed ( Terrorist t ) {
        
        super(t);
        decoratedTerrorist = t;
    }    

    public void BrainWashed( Terrorist t ) {
        // TODO implement here
        this.decoratedTerrorist = t;
    }

    @Override
    public void draw(Graphics2D g2d) {
        
        String entityName = "BW";
        g2d.setColor(Color.BLACK);
        g2d.drawString( entityName , (int) this.position.getX() +10  , (int) this.position.getY() +50  );
        
        decoratedTerrorist.draw(g2d);
    }

    @Override
    public void step(double deltaTime) {
        
        this.strategy.step(this, deltaTime);
    }
}
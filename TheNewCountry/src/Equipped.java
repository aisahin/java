
import java.awt.Color;
import java.awt.Graphics2D;

public class Equipped extends TerroristDecorator {
    
    protected Terrorist decoratedTerrorist;

    public Equipped ( Terrorist t ) {
        
        super(t);
        decoratedTerrorist = t;
    }
    
    public void Equipped(Terrorist t) {
        
        this.decoratedTerrorist = t;
    }

    @Override
    public void draw(Graphics2D g2d) {
        
        String entityName = "BOMB!";
        g2d.setColor(Color.BLACK);
        g2d.drawString( entityName , (int) this.position.getX() + 10  , (int) this.position.getY() +60  );
        
        decoratedTerrorist.draw(g2d);     
    }

    @Override
    public void step(double deltaTime) {
        
        this.strategy.step(this, deltaTime);
    }
}
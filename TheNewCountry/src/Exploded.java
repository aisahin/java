
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

public class Exploded extends TerroristDecorator {
    
    private Terrorist decoratedTerrorist;

    public Exploded ( Terrorist t ) {
        
        super(t);
    }
    
    public void Exploded(Terrorist t) {
        
        this.decoratedTerrorist = t;
    }

    @Override
    public void draw(Graphics2D g2d) {
         // TODO implement here
         // Incomplete
        String entityName = "TERRORIST";
        g2d.setColor( this.color );
        g2d.drawOval( (int) position.getX(), (int) position.getY(), Environment.diameter, Environment.diameter );
        Image terrorist = Toolkit.getDefaultToolkit().getImage("src/terrorist.png");
        g2d.drawImage(terrorist, (int) position.getX()+15, (int) position.getY()+5, 100, 100, null); 
        g2d.drawString( entityName , (int) this.position.getX() + 30 , (int) this.position.getY() - 5 );
        g2d.drawString( strategy.getName() , (int) this.position.getX()+50 , (int) this.position.getY() +140 );
        g2d.setPaint( Color.BLACK );
        g2d.drawString( "DELI" , (int) this.position.getX() + 40 , (int) this.position.getY() - 10 );
        
        decoratedTerrorist.draw(g2d);        
    }

    @Override
    public void step(double deltaTime) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
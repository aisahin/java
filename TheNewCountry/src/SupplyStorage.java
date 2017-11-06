
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class SupplyStorage extends Entity {

    public SupplyStorage(Color myColor, Position myPosition, StepStrategy myStrategy ) {
        
        super( myColor, null, null, myPosition, 0.0, myStrategy );
    }
    
        public static  ArrayList<Integer> getPosition() {
        ArrayList<Integer> elements = new ArrayList<>();
        elements.add(300);
        elements.add(350);
        elements.add(650);
        elements.add(500);
        elements.add(1400);
        elements.add(680);        
        return elements;
    }

    @Override
    public void step(double deltaTime) {
//        super.step(deltaTime); //To change body of generated methods, choose Tools | Templates.
                this.strategy.step(this, deltaTime);
    }

    @Override
    public void draw(Graphics2D g2d) {
    
        String entityName = "STORAGE";
        g2d.setColor( this.color );
        g2d.drawOval(300, 350, Environment.campStorageDiameter, Environment.campStorageDiameter );
        g2d.setColor( Color.BLACK );
        g2d.drawString( entityName , 315 , 395 );
        
        g2d.setColor( this.color );
        g2d.drawOval( 650, 500, Environment.campStorageDiameter, Environment.campStorageDiameter );
        g2d.setColor( Color.BLACK );
        g2d.drawString( entityName , 665 , 545 );   
        
        g2d.setColor( this.color );
        g2d.drawOval( 1400, 680, Environment.campStorageDiameter, Environment.campStorageDiameter );
        g2d.setColor( Color.BLACK );
        g2d.drawString( entityName , 1415 , 725 );          
    }
}
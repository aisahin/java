
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Camp extends Entity {

    public Camp(Color myColor, Position myPosition, StepStrategy myStrategy ) {
        
        super( myColor, null, null, myPosition, 0.0, myStrategy );
    }

    public static  ArrayList<Integer> getPosition() {
        ArrayList<Integer> elements = new ArrayList<>();
        elements.add(1200);
        elements.add(900);
        elements.add(1700);
        elements.add(850);
        elements.add(1750);
        elements.add(300);        
        return elements;
    }

    @Override
    public void step(double deltaTime) {
        
        super.step(deltaTime); //To change body of generated methods, choose Tools | Templates.//
        this.strategy.step(this, deltaTime);
    }

    @Override
    public void draw(Graphics2D g2d) {
        
        String entityName = "CAMP";
        g2d.setColor( this.color );
        g2d.drawOval( 1200, 900, (int) Environment.diameter*3/4, (int) Environment.diameter*3/4 );
        g2d.setColor( Color.BLACK );
        g2d.drawString( entityName + "SURIYE" , 1225 , 945 );
        
        g2d.setColor( this.color );
        g2d.drawOval( 1700, 850, (int) Environment.diameter*3/4, (int) Environment.diameter*3/4 );
        g2d.setColor( Color.BLACK );
        g2d.drawString( entityName + "IRAK" + "" , 1725 , 895 );   
        
        g2d.setColor( this.color );
        g2d.drawOval( 1750, 300, (int) Environment.diameter*3/4, (int) Environment.diameter*3/4 );
        g2d.setColor( Color.BLACK );
        g2d.drawString( entityName + "ERMENISTAN", 1775 , 345 );         
    }    
}

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class Display extends JPanel{
    
    Environment environment;
    
    public Display (Environment myEnvironment) {
        
        environment = myEnvironment;
        setBackground(Color.WHITE);
    }

    public Dimension getPreferedSize() {
        
        return new Dimension(environment.windowWidth, environment.windowHeight);
    }

    public void paintComponent(Graphics g) {        
        // TODO implement here
        // Incomplete : add information panel and logo
        super.paintComponent( g ) ;
        Graphics2D g2d = (Graphics2D) g ;
//=================================================================================================================================================        
// Draw Turkey map
        Image turkeyMap = Toolkit.getDefaultToolkit().getImage("src/turkey_map.png");
        g.drawImage(turkeyMap, 0, Environment.topPanelHeight, getWidth(), getHeight() -Environment.topPanelHeight , this);
//=================================================================================================================================================        
// Draw vertical line
        g2d.drawLine(0, environment.topPanelHeight, getWidth(), environment.topPanelHeight);
//=================================================================================================================================================        
// Draw horizontal line up to veritcal line above
        g2d.drawLine(getWidth()-environment.distanceToLogo, 0, getWidth()-environment.distanceToLogo, environment.topPanelHeight);
//=================================================================================================================================================        
// Draw entities
        synchronized (environment) {}
        for ( Entity e : environment.entities ) {
            
            e.draw( g2d ) ;          
        }
//=================================================================================================================================================
// Draw Information panel
        g2d.setPaint( Color.BLACK   ) ;  
        g2d.drawString(  "Step : " + environment.stepCounter , 100, 100 ) ;
//        g2d.drawString(  "Camp Position X : " + environment.camp.position.x , 100, 150 ) ;
//        g2d.drawString(  "Camp Position Y : " + environment.camp.position.y , 100, 200 ) ;

        g2d.drawString(  "burası " , 800, 800 ) ;
//=================================================================================================================================================
    }
}
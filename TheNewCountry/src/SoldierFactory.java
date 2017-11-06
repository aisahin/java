
import java.awt.Color;


public class SoldierFactory extends EntityFactory {

    public SoldierFactory() {
    }
    
    @Override
    public Entity CreateEntity( Color myColor, String myName, Position myDirection, Position myPosition, double mySpeed, StepStrategy myStrategy ) {
        
        return new Soldier( myColor, myName, myDirection, myPosition, mySpeed, myStrategy );
    }
}
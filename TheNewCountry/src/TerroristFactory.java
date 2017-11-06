
import java.awt.Color;

public class TerroristFactory extends EntityFactory {

    @Override
    public Entity CreateEntity( Color myColor, String myName, Position myDirection, Position myPosition, double mySpeed, StepStrategy myStrategy ) {
        
        return new Terrorist( myColor, myName, myDirection, myPosition, mySpeed, myStrategy );
    }

}
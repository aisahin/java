
import java.awt.Color;


public abstract class CitizenFactory extends EntityFactory {

    public CitizenFactory() {
    }

    public Entity CreateEntity( Color myColor, String myName, Position myDirection, Position myPosition, double mySpeed, StepStrategy myStrategy ) {
        
        return new Citizen ( myColor, myName, myDirection, myPosition, mySpeed, myStrategy );
    }
}
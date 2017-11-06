
import java.awt.Color;


public class AgentFactory extends EntityFactory {

    @Override
    public Entity CreateEntity( Color myColor, String myName, Position myDirection, Position myPosition, double mySpeed, StepStrategy myStrategy ) {
        
        return new Agent ( null, myName, myDirection, myPosition, mySpeed, myStrategy );
    }
}

import java.awt.Color;


public abstract class EntityFactory {
    
    public abstract Entity CreateEntity( Color myColor, String myName, Position myDirection, Position myPosition, double mySpeed, StepStrategy myStrategy );
}
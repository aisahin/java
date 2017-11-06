
import java.awt.Color;

public class UntouchableFactory extends EntityFactory {

    public Entity CreateEntity ( Color myColor, String myName, Position myDirection, Position myPosition, double mySpeed, StepStrategy myStrategy ) {
        
        return new Untouchable ( myColor, myName, myDirection, myPosition, mySpeed, myStrategy );
    }
}

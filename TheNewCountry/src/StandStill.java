
public class StandStill extends StepStrategy {

    public StandStill( Environment myEnvironment, int myNumberOfTruns ) {
//        // TODO implement here
        super( myEnvironment, myNumberOfTruns );
    }

    public void StandStill() {
        // TODO implement here
    }
//=================================================================================================================================================
    @Override
    public String getName() {
        
        return "SS";
    }

//    @Override
//    public boolean isFinished() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public void step(Entity e, double deltaTime) {
        // TODO implement here
        numberOfTruns--;
    }
//=================================================================================================================================================
}
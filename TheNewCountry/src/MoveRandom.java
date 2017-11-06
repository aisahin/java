
public class MoveRandom extends StepStrategy{ 
    
    private Position direction ;
    
    public MoveRandom ( Environment myEnvironment, int myNumberOfTruns ) {
        // TODO implement here
        super( myEnvironment, myNumberOfTruns );
    }
//=================================================================================================================================================
    @Override
    public String getName() {
        
        return "MR";
    }

//    @Override
//    public boolean isFinished() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public void step(Entity e, double deltaTime) {
        // TODO implement here
        numberOfTruns--;
        e.direction = environment.generateRandomDirection();
        e.position.travel(e.speed , e.direction , deltaTime ) ;
    }
}    
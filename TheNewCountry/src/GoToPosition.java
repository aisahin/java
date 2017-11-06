
public class GoToPosition extends StepStrategy {

    private Position direction ;
    
    public GoToPosition( Environment myEnvironment, int myNumberOfTruns ) {
        // TODO implement here
        super( myEnvironment, myNumberOfTruns );
    }
//    public Position target;

//=================================================================================================================================================

    @Override
    public String getName() {
        
        return "GT";
    }

//    @Override
//    public boolean isFinished() {
//        
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public void step(Entity e, double deltaTime) {
        
        // TODO implement here
        numberOfTruns--;
        
        
//        e.position = environment.predefinedDirection();
        e.direction = environment.predefinedDirection();
        e.position.travel(e.speed , e.direction, deltaTime ) ;

//        e.position.travel(e.speed , environment.predefinedDirection() , deltaTime ) ;

//        if ( ( e.position.x < 0 ) && ( e.direction.x < 0 ) )  {
//            
//            e.direction.x *= -1 ; 
//        }
//        
//        if ( ( e.position.x > Environment.windowWidth ) && (e.direction.x > 0 ) )  {
//            
//            e.direction.x *= -1 ; 
//        }
//        
//        if ( ( e.position.y < Environment.topPanelHeight ) && ( e.direction.y < 0 ) )  {
//            
//            e.direction.y *= -1 ; 
//        }
//        
//        if ( ( e.position.y > Environment.windowHeight ) && (e.direction.y > 0 ) )  {
//            
//            e.direction.y *= -1 ; 
//        }
        
        //e.direction = environment.predefinedDirection();
    }
//=================================================================================================================================================
}
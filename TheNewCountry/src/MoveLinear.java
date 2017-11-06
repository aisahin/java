
public class MoveLinear extends StepStrategy {

    private Position direction ;
    
    public MoveLinear( Environment myEnvironment, int myNumberOfTruns ) {
        // TODO implement here
        super( myEnvironment, myNumberOfTruns );
    }

//=================================================================================================================================================
    @Override
    public String getName() {
        
        return "ML";
    }

//    @Override
//    public boolean isFinished() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public void step(Entity e, double deltaTime) {
        // TODO implement here

        numberOfTruns--;
        e.position.travel(e.speed, e.direction, deltaTime);
        
        if ( ( e.position.x < 0 ) && ( e.direction.x < 0 ) )  {
            
            e.direction.x *= -1 ; 
        }
        
        if ( ( e.position.x > Environment.windowWidth ) && (e.direction.x > 0 ) )  {
            
            e.direction.x *= -1 ; 
        }
        
        if ( ( e.position.y < Environment.topPanelHeight ) && ( e.direction.y < 0 ) )  {
            
            e.direction.y *= -1 ; 
        }
        
        if ( ( e.position.y > Environment.windowHeight ) && (e.direction.y > 0 ) )  {
            
            e.direction.y *= -1 ; 
        }
        //e.direction = direction;        
    }
//=================================================================================================================================================
}
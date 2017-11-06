
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Environment {
    
    Random random = new Random();
    
    public ArrayList<Entity> entities;
    
    StepStrategy stepStrategy;
    
    Position position = new Position(0, 0);
    
    Camp camp = new Camp(Color.RED, position, stepStrategy);
    
    SupplyStorage supplyStorage = new SupplyStorage(Color.RED, position, stepStrategy);
    
    Display display = new Display(this);
        
    EntityFactory ef = new EntityFactory () {
        
        @Override
        public Entity CreateEntity(Color myColor, String myName, Position myDirection, Position myPosition, double mySpeed, StepStrategy myStrategy) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };    
    
    public static int numberOfCitizens      = 5                     ;
    public static int numberOfAgents        = 5                    ;
    public static int numberOfSoldiers      = 5                   ;
    public static int numberOfTerrrorist    = 5                     ;
    public static int numberOfUntouchables  = 3                     ;
    
    public static int windowWidth           = 1800                  ;
    public static int windowHeight          = 1000                  ;
    public static int topPanelHeight        = 250                   ;
    public static int distanceToLogo        = 250                   ;
    
    public static int diameter              = 120                   ;
    public static int campStorageDiameter   = (int) diameter*3/4    ; 
    
    public int stepCounter ;
//=================================================================================================================================================
// Getters  and Setters   
    public int getStepCounter() {
        
        return stepCounter;
    }

    public void setStepCounter(int stepCounter) {
        
        this.stepCounter = stepCounter;
    }
//================================================================================================================================================= 

    public Environment() {
        
        this.stepCounter = 0;
        entities = new ArrayList<Entity>() ;
        createEntity( ef );
    }
    
//================================================================================================================================================= 
//          ENTITIES    
//=================================================================================================================================================     
    public ArrayList<Entity> createEntity(EntityFactory f) {
        // TODO implement here

        String        name              = ""; 
        for(int i = 0; i < 3; i++) {
        
            entities.add(new Camp( Color.RED, position, generateCampSupplyStrategy() ) );
        }
        for(int i = 0; i < 3; i++) {
        
            entities.add(new SupplyStorage ( Color.RED , position, generateCampSupplyStrategy() ) );
        }        
        for(int i = 0; i < numberOfAgents; i++ ) {
            
            entities.add( new Agent( 
                    Color.BLUE, name, generateRandomDirection(), generateRandomLocation(), generateRandomSpeed(2.0, 4.0), generateAgentStrategy()) );         
        }
        for(int i = 0; i < numberOfCitizens; i++ ) {
            
            entities.add( new Citizen( 
                    generateRandomColor (), name, generateRandomDirection(), generateRandomLocation(), generateRandomSpeed(2.0, 4.0), generateCitizenStrategy()) );
        }
        for(int i = 0; i < numberOfSoldiers; i++ ) {
            
            entities.add( new Soldier( 
                    Color.GREEN, name, generateRandomDirection(), generateRandomLocation(), generateRandomSpeed(2.0, 4.0), generateSoldierStrategy()) );
        }
        for(int i = 0; i < numberOfTerrrorist; i++ ) {
            
            entities.add( new RecentlyDeceived( 
                    Color.RED, name, generateRandomDirection(), generateRandomLocation(), generateRandomSpeed(3.0, 4.0), generateTerroristStrategy()) );
        }
        for(int i = 0; i < numberOfUntouchables; i++ ) {
            
            entities.add( new Untouchable(
                    generateRandomColor (), name, generateRandomDirection(), generateRandomLocation(), generateRandomSpeed(2.0, 4.0), generateUntouchableStrategy()) );
        }
        
        return entities;
    }
    
    public StepStrategy generateStrategy(Entity e) {
        // TODO implement here
        // Move the StepStrategies here
        return null;
    }    
//=================================================================================================================================================
//          STRATEGIES    
//=================================================================================================================================================
    public StepStrategy generateCitizenStrategy() {
        
    int numberOfTurns = generateRandomNumberOfTurns ( 30, 60 ) ;
    
        switch ( random.nextInt( 3 ) ) {
            case 0  : return new StandStill( this , numberOfTurns ) ;
            case 1  : return new MoveRandom( this , numberOfTurns ) ;
            case 2  : return new MoveLinear( this , numberOfTurns ) ;
            default : return null                                   ;
        }        
    }
    
    public StepStrategy generateAgentStrategy() {
        
    int numberOfTurns = generateRandomNumberOfTurns ( 30, 60 ) ;
    
        switch ( random.nextInt( 2 ) ) {
            case 0  : return new StandStill( this , numberOfTurns ) ;
            case 1  : return new MoveRandom( this , numberOfTurns ) ;
            default : return null                                   ;
        }        
    }    
    
    public StepStrategy generateSoldierStrategy() {
        
    int numberOfTurns = generateRandomNumberOfTurns ( 30, 60 ) ;
//    return new GoToPosition ( this , numberOfTurns )  ;

        switch ( random.nextInt( 2 ) ) {
             
//            case 0  : return new StandStill( this , numberOfTurns )     ;
            case 0  : return new MoveLinear( this , numberOfTurns )     ;
            case 1  : return new GoToPosition ( this , numberOfTurns )  ;
//            case 3  : return new Patrol(this , numberOfTurns )          ;
            default : return null                                       ;
        }
    }

    public StepStrategy generateUntouchableStrategy() {
        
    int numberOfTurns = generateRandomNumberOfTurns ( 30, 60 ) ;
    
        switch ( random.nextInt( 2 ) ) {
            case 0  : return new StandStill( this , numberOfTurns ) ;
            case 1  : return new MoveLinear(this , numberOfTurns ) ;
            default : return null                                   ;
        }        
    }
    
    public StepStrategy generateCampSupplyStrategy () {
        
        int numberOfTurns = generateRandomNumberOfTurns ( 30, 60 ) ;
        return new StandStill(this, numberOfTurns);
    }

    public StepStrategy generateTerroristStrategy() {
        
    int numberOfTurns = generateRandomNumberOfTurns ( 30, 60 ) ;
    
        switch ( random.nextInt( 2 ) ) {
            case 0  : return new StandStill( this , numberOfTurns ) ;
            case 1  : return new MoveLinear(this , numberOfTurns ) ;
            default : return null ;
        }        
    }
    
    public StepStrategy generateBWTerroristStrategy() {
        
    int numberOfTurns = generateRandomNumberOfTurns ( 30, 60 ) ;
    
        switch ( random.nextInt( 2 ) ) {
            case 0  : return new StandStill( this , numberOfTurns ) ;
            case 1  : return new MoveLinear(this , numberOfTurns )  ;
            default : return null                                   ;
        }        
    }
    
    public StepStrategy generateEqTerroristStrategy() {
        
    int numberOfTurns = generateRandomNumberOfTurns ( 30, 60 ) ;
    
        switch ( random.nextInt( 1 ) ) {
            case 0  : return new StandStill( this , numberOfTurns ) ;
            default : return null                                   ;
        }        
    }
//================================================================================================================================================= 
// Random Generaters    
//================================================================================================================================================= 

    public Color generateRandomColor ()
    {
    return new Color( random.nextInt(256) , random.nextInt(256) , random.nextInt(256) ) ;
    }
    
    public Position generateRandomDirection () {
        
        return new Position( random.nextInt( 360 ) ) ;
    }
    
    public Position predefinedDirection () {
    
        position.x = generateRandomLocation().x;
        position.y = generateRandomLocation().y;
        
        return new Position( 800, 800) ;
    }
    
    public double generateRandomSpeed ( double minimumSpeed , double maximumSpeed ) {
      
        return minimumSpeed + (maximumSpeed - minimumSpeed) * random.nextDouble() ;
    }
    
    public Position generateRandomLocation ( ) {
        
        int xMin = 200;
        int xMax = windowWidth ;
        int yMin = topPanelHeight;
        int yMax = windowHeight ;

        position.x = xMin + random.nextInt( xMax - xMin + 1 ) ;
        position.y = yMin + random.nextInt( yMax - yMin + 1 ) ;
        
        return new Position( position.x, position.y ) ;
    }

    public int generateRandomNumberOfTurns ( int minimum , int maximum ) {
        
        return minimum + random.nextInt( maximum - minimum + 1 ) ;
    }
//=================================================================================================================================================
//              STEP ALL    
//=================================================================================================================================================    
    public synchronized void stepAll (double deltaTime) {
        
        for ( Entity e: entities ) {
            
            if ( ! (e instanceof Camp || e instanceof SupplyStorage )) {
                
                e.step(deltaTime);
            
                if ( e.strategy.isFinished() ) {                

                    if ( e instanceof Citizen) { 
                        
                        e.strategy = generateCitizenStrategy(); 
                    }

                    if ( e instanceof  Agent ) {

                        e.strategy = generateAgentStrategy();
                    }

                    if ( e instanceof  Soldier ) {

                        e.strategy = generateSoldierStrategy();
                    }

                    if ( e instanceof  Untouchable ) {
                        
                        e.strategy = generateUntouchableStrategy();
                    } 
//================================================================================================================================================= 
                    if ( e instanceof Terrorist ) {
                        
                      
                       if ( ( Math.abs( ( ( (Terrorist) e).position.x - camp.getPosition().get(0)) ) < 150 
                                && Math.abs ( ( ((Terrorist) e).position.y  - camp.getPosition().get(1)) ) < 150 ) 
                                || ( Math.abs( ( ( (Terrorist) e).position.x - camp.getPosition().get(2)) ) < 150 
                                && Math.abs ( ( ((Terrorist) e).position.y  - camp.getPosition().get(3)) ) < 150 ) 
                                || ( Math.abs( ( ( (Terrorist) e).position.x - camp.getPosition().get(4)) ) < 150 
                                && Math.abs ( ( ((Terrorist) e).position.y  - camp.getPosition().get(5)) ) < 150 )  )  {
                            
                            entities.set(entities.indexOf(e), new BrainWashed( (Terrorist) e) );                                
                        }
                        else  {e.strategy = generateTerroristStrategy();}
                    }
//=================================================================================================================================================                 
                }
            }            
        }
        
        for (Entity ee : entities) {
            
            ee.step(deltaTime);
        
            if ( ee instanceof BrainWashed) {            

                    if ( ( Math.abs( ( ( (BrainWashed) ee).position.x - supplyStorage.getPosition().get(0)) ) < 50 
                        && Math.abs ( ( ((BrainWashed) ee).position.y  - supplyStorage.getPosition().get(1)) ) < 50 ) 
                        || ( Math.abs( ( ( (BrainWashed) ee).position.x - supplyStorage.getPosition().get(2)) ) < 50 
                        && Math.abs ( ( ((BrainWashed) ee).position.y  - supplyStorage.getPosition().get(3)) ) < 50) 
                        || ( Math.abs( ( ( (BrainWashed) ee).position.x - supplyStorage.getPosition().get(4)) ) < 50 
                        && Math.abs ( ( ((BrainWashed) ee).position.y  - supplyStorage.getPosition().get(5)) ) < 50)  ){

                        entities.set( entities.indexOf(ee), new Equipped((Terrorist)  ee) );                        
                    }
                    
                    else {ee.strategy = generateBWTerroristStrategy();}                
            }
            
            if (ee instanceof Equipped) {
            
                ee.strategy = generateEqTerroristStrategy();
            }
        }
        stepCounter++;
    }
//================================================================================================================================================= 
   }
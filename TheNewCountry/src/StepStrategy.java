
public abstract class StepStrategy {
    
    public Environment environment;
    public int numberOfTruns ;
    public Position position;

    public StepStrategy(Environment myEnvironment, int myNumberOfTruns) {
        
        this.environment = myEnvironment;
        this.numberOfTruns = myNumberOfTruns;
    }

    public abstract String getName();

    public boolean isFinished() {
    
        return ( numberOfTruns <= 0 ) ;
    }

    public abstract void step(Entity e, double deltaTime);

//=================================================================================================================================================
//  Getter and Setter.
    
    public int getNumberOfTruns() {
        return numberOfTruns;
    }

    public void setNumberOfTruns(int numberOfTruns) {
        this.numberOfTruns = numberOfTruns;
    }
//=================================================================================================================================================
}
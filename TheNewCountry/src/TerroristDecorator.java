
public abstract class TerroristDecorator extends Terrorist {
    
    protected Terrorist decoratedTerrorist;
    
    public  TerroristDecorator(Terrorist t) {
        
        super( t.color, t.name, t.direction, t.position, t.speed, t.strategy );
        
        decoratedTerrorist = t;        
    } 
}
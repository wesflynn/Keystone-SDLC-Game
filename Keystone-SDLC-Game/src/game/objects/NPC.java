package game.objects;

import javafx.scene.paint.Color;

/**
 * Class for Non-Playable Characters
 */
public class NPC extends Character
{
    private static long counter = 1L;
    private final long ID = counter;
    
    private Message message;
    
    public NPC(double x, double y, Color color, Message message)
    {
        super(x, y, color);
        counter++;
        
        this.message = message;
    }
    
    @Override
    public void update()
    {
        
    }
    
    public Message getMessage()
    {
        return this.message;
    }
}

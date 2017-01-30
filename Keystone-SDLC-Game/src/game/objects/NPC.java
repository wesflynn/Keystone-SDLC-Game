package game.objects;

import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 * Class for Non-Playable Characters
 */
public class NPC extends Character
{
    private static long counter = 1L;
    private final long ID = counter;
    // since each npc has more then one message maybe we should put them in array then pass them into the construtor?
    private ArrayList<Message> messageArray;
    
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

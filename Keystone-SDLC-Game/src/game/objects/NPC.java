package game.objects;

/**
 * Class for Non-Playable Characters
 */
public class NPC extends Character
{
    private static long counter = 1L;
    private final long ID = counter;
    
    public NPC()
    {
        counter++;
    }
    /*
    TODO:
        - Hitbox
        - Some way of getting the specific text for the NPC. Whether that means
        storing the text in the class, or giving the NPC object an ID that refers
        to text
        - basic interaction
    */
    
    /*
    OPTIONAL
    
    public boolean checkCollision(Player player)
    {
        - if player position is close enough,
        return true (Start conversation)
        - else
        return false
    }
    */
    @Override
    public void update()
    {
        
    }
    
}

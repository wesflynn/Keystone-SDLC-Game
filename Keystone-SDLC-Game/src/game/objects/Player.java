package game.objects;

import javafx.scene.paint.Color;

/**
 * Character controlled by the user
 * 
 * @author adamb
 */
public class Player extends Character
{
    /*
    TODO:
        - movement
        - hitbox (put in character??)
        - interaction
    */
    private final int DEAFULT_POS_X = 250;
    private final int DEFAULT_POS_Y = 530;
    private final Color DEFAULT_COLOR = Color.RED;
    
    public Player() {
        super.setPosX(this.DEAFULT_POS_X);
        super.setPosY(this.DEFAULT_POS_Y);
        super.setColor(this.DEFAULT_COLOR);
    }

    @Override
    public void update()
    {
        
    }
    
}

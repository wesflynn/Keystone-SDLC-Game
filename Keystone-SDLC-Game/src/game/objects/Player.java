package game.objects;

import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 * Character controlled by the user
 * 
 * @author adamb
 */
public final class Player extends Character
{
    /*
    TODO:
        - expanded hitbox (bigger then npc or player objects, possibly getboundy +50 pixels or something?)
        - interaction (add conditional key pressed plus collision detection to trigger event, like bringing up message window)
    */
    private static final double DEAFULT_POS_X = 250;
    private static final double DEFAULT_POS_Y = 530;
    
    public static final double SPEED = 4;
    
    private static final Color DEFAULT_COLOR = Color.RED;
    
    private double velocityX;
    private double velocityY;
    
    public Player()
    {
        super(Player.DEAFULT_POS_X,
                Player.DEFAULT_POS_Y,
                Player.DEFAULT_COLOR);
        
        this.setVelocityX(0);
        this.setVelocityY(0);
    }

    @Override
    public void update()
    {
        this.setPositionX(this.getPosX() + this.getVelocityX());
        this.setPositionY(this.getPosY() + this.getVelocityY());
    }
    
    /**
     * Controls the player's movement
     * 
     * @param input 
     */
    public void move(ArrayList<String> input)
    {
        if(input.contains("RIGHT")
                && !input.contains("LEFT"))
        {
            this.setVelocityX(SPEED);
        }
        else if(!input.contains("RIGHT")
                && input.contains("LEFT"))
        {
            this.setVelocityX(-SPEED);
        }
        else if(!input.contains("RIGHT")
                && !input.contains("LEFT"))
        {
            this.setVelocityX(0);
        }
        
        if(input.contains("UP")
                && !input.contains("DOWN"))
        {
            this.setVelocityY(-SPEED);
        }   
        else if(!input.contains("UP")
                && input.contains("DOWN"))
        {
            this.setVelocityY(SPEED);
        }
        else if(!input.contains("UP")
                && !input.contains("DOWN"))
        {
            this.setVelocityY(0);
        }
    }
    
    public double getVelocityX()
    {
        return this.velocityX;
    }
    
    public double getVelocityY()
    {
        return this.velocityY;
    }

    public void setVelocityX(double velocity)
    {
        velocityX = velocity;
    }
    
    public void setVelocityY(double velocity)
    {
        this.velocityY = velocity;
    }
        
    public boolean intersects(NPC npc)
    {
        return this.getBoundary().intersects( npc.getBoundary() );
    }
    
}

package game.objects;

import java.util.ArrayList;
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
        - hitbox (put in character??)
        - interaction
    */
    private double velocityX;
    private double velocityY;
    private final double DEAFULT_POS_X = 250;
    private final double DEFAULT_POS_Y = 530;
    private final Color DEFAULT_COLOR = Color.RED;
    
    public Player() {
        super.setPosX(this.DEAFULT_POS_X);
        super.setPosY(this.DEFAULT_POS_Y);
        super.setColor(this.DEFAULT_COLOR);
        velocityX = 0;
        velocityY = 0;
    }

    @Override
    public void update(double time)
    {
        posX += velocityX * time;
        posY += velocityY * time;
    }

    public void move(ArrayList<String> input)
    {
        setVelocity(0,0);
        if(input.contains("UP"))
            addVelocity(0,-100);
        if(input.contains("LEFT"))
            addVelocity(-100,0);    
        if(input.contains("RIGHT"))
            addVelocity(100,0);    
        if(input.contains("DOWN"))
            addVelocity(0,100);
    }
    
        public void setPosition(double x, double y)
    {
        super.setPosX(x);
        super.setPosY(y);
    }

    public void setVelocity(double x, double y)
    {
        velocityX = x;
        velocityY = y;
    }
        public void addVelocity(double x, double y)
    {
        velocityX += x;
        velocityY += y;
    }
        
       public boolean intersects(Player p,NPC npc)
    {
        return p.getBoundary().intersects( npc.getBoundary() );
    }
    
}

package game.objects;

import game.platform.Drawable;
import java.awt.geom.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Framework for character objects within the game
 * 
 * @author adamb
 */
public abstract class Character implements Drawable
{
    public final double WIDTH = 50;
    public final double HEIGHT = 50;
    // Global private variables
    public double posX, posY;
    private Color color;
    
    // Accessors
    public final double getWidth()
    {
        return this.WIDTH;
    }
    
    public final double getHeight()
    {
        return this.HEIGHT;
    }
    
    public double getPosX()
    {
        return this.posX;
    }
    
    public double getPosY()
    {
        return this.posY;
    }
    
    public Color getColor()
    {
        return this.color;
    }
    
    // Mutators
    public void setPosX(double x)
    {
        this.posX = x;
    }
    
    public void setPosY(double y)
    {
        this.posY = y;
    }
    
    public void setColor(Color color)
    {
        this.color = color;
    }
    
    @Override
    public void draw(GraphicsContext brush) {
        brush.setFill(color);
        brush.fillOval(posX, posY, WIDTH, HEIGHT);
        brush.setStroke(Color.BLACK);
        brush.strokeOval(posX, posY, WIDTH, HEIGHT);
    }
    
    public Rectangle2D getBoundary()
    {
        //couldnt get return methods to work so had to make vars public
        return new Rectangle2D.Double(posX,posY,WIDTH,HEIGHT);
    }
}

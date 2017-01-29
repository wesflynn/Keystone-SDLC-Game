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
    private final double WIDTH = 50;
    private final double HEIGHT = 50;
    
    // Global private variables
    public double posX, posY;
    private Color color;
    
    public Character(double x, double y, Color color)
    {
        this.setPositionX(x);
        this.setPositionY(y);
        this.setColor(color);
    }
    
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
    public final void setPositionX(double x)
    {
        this.posX = x;
    }
    
    public final void setPositionY(double y)
    {
        this.posY = y;
    }
    
    public final void setColor(Color color)
    {
        this.color = color;
    }
    
    @Override
    public void draw(GraphicsContext brush)
    {
        brush.setFill(color);
        brush.fillOval(posX, posY, WIDTH, HEIGHT);
        
        brush.setStroke(Color.BLACK);
        brush.strokeOval(posX, posY, WIDTH, HEIGHT);
    }
    
    public Rectangle2D getBoundary()
    {
        // adding extra to the height and width allows a buffer zone between player and npc
        double buffer = 5;
        
        return new Rectangle2D.Double(this.getPosX(),
                this.getPosY(),
                WIDTH + buffer,
                HEIGHT + buffer);
    }
}

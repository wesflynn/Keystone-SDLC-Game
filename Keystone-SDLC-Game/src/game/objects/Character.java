package game.objects;

import game.platform.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Character implements Drawable
{
    private final int WIDTH = 50;
    private final int HEIGHT = 50;
    
    // Global private variables
    private int posX, posY;
    private Color color;
    
    // Accessors
    public final int getWidth()
    {
        return this.WIDTH;
    }
    
    public final int getHeight()
    {
        return this.HEIGHT;
    }
    
    public int getPosX()
    {
        return this.posX;
    }
    
    public int getPosY()
    {
        return this.posY;
    }
    
    public Color getColor()
    {
        return this.color;
    }
    
    // Mutators
    public void setPosX(int x)
    {
        this.posX = x;
    }
    
    public void setPosY(int y)
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
}

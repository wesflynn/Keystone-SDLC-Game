package game.objects;

import game.platform.Drawable;
import javafx.scene.canvas.GraphicsContext;

/**
 * Progress bar showing the current progress of the game
 */
public class ProgressBar implements Drawable
{
    private int progress;
    
    public ProgressBar()
    {
        this.progress=5;
    }
    
    @Override
    public void update()
    {
        
    }

    @Override
    public void draw(GraphicsContext brush)
    {
        
    }
    
}

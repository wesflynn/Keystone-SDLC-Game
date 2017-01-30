package game.objects;

import game.platform.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Progress bar showing the current progress of the game
 */
public class ProgressBar implements Drawable
{
    private int progress;
    private int barChange=200;
    
    public ProgressBar()
    {
        this.progress=5;
    }
    
    @Override
    public void update()
    {
        
    }
// progress bar will need to refresh in the game loop so it stays correct
    @Override
    public void draw(GraphicsContext brush)
    {
        for (int i = 0;i<this.progress;i++)
        {
            brush.setFill(Color.RED);
            brush.fillRect(barChange, 100, 40, 20);
            barChange +=40;
        }
        
        
        brush.setStroke(Color.BLACK);
        brush.strokeRect(200, 100, 400, 20);        
    }

    public void addProgress(int progress) {
        this.progress += progress;
    }
    public void removeProgress(int progress) {
        this.progress -= progress;
    }    

    public int getProgress() {
        return progress;
    }

    @Override
    public String toString() {
        return "ProgressBar{" + "progress=" + this.progress + '}';
    }
    
    
}

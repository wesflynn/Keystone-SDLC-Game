package game.platform;

import javafx.scene.canvas.GraphicsContext;

/**
 * Any object being drawn on the window should implement this interface.
 * 
 * Please refer to Adam before changing.
 * 
 * @author adam-bates
 */
public interface Drawable
{
    public void update(double time);
    public void draw(GraphicsContext brush);
}

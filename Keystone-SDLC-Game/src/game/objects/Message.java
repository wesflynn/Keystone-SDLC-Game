/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects;

import game.platform.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author wes_4
 */
public class Message implements Drawable{
    
    private final double POSX = 120;
    private final double POSY = 450;
    private final double HEIGHT = 200;
    private final double WIDTH = 500;
    private final Color color = Color.BLACK;
    private String text;
    public Message(String text)
    {
      this.text=text;

    }

    @Override
    public void update(double time) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(GraphicsContext brush) {
        brush.setFill(color);
        brush.fillRect(POSX, POSY, WIDTH, HEIGHT);
        brush.setStroke(Color.WHITE);
        brush.strokeRect(POSX, POSY, WIDTH, HEIGHT);
        brush.setFill(Color.WHITE);
        brush.fillText(text, POSX+20, POSY+20);
    }
    
    public void addText()
    {
        
    }
    
}

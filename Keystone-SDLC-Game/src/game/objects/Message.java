/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects;

import game.platform.Drawable;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author wes_4
 */
public class Message implements Drawable{
    
    private final double POSX, POSY;
    private final double HEIGHT;
    private final double WIDTH;
    public Message(String text)
    {
      // placeholder values
      HEIGHT=100;
      WIDTH=100;
      POSX=450;
      POSY=450;
    }

    @Override
    public void update(double time) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(GraphicsContext brush) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

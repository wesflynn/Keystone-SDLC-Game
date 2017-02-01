/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects;

import game.platform.Drawable;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author wes_4
 */
public class Message implements Drawable
{
    private final double POS_X = 120;
    private final double POS_Y = 450;
    private final double HEIGHT = 200;
    private final double WIDTH = 500;
    
    private final Color TEXT_COLOR = Color.WHITE;
    private final Color BACKGROUND_COLOR = Color.BLACK;
    private final Color BORDER_COLOR = Color.WHITE;
    
    private String text;
    private int points;
    
    private boolean hasPages = false;
    private ArrayList<String> pages;
    
    public Message(String text)
    {
      this(text, 0);
    }
    
    public Message(String text, int points)
    {
        this.text = text;
        this.points = points;
    }
    
    @Override
    public String toString()
    {
        return this.getText();
    }
    
    public String getText()
    {
        return this.text;
    }
    
    public int getPoints()
    {
        return this.points;
    }
    
    public boolean hasPages()
    {
        return this.hasPages;
    }
    
    public ArrayList<String> getPages()
    {
        return this.pages;
    }
    
    public void setText(String text)
    {
        this.text = text;
    }
    
    public void setPages(ArrayList<String> pages)
    {
        this.pages = pages;
        this.hasPages = true;
    }

    @Override
    public void update()
    {
        //TODO: Implement 
    }

    @Override
    public void draw(GraphicsContext brush)
    {
        brush.setFill(this.BACKGROUND_COLOR);
        brush.fillRect(this.POS_X, this.POS_Y, this.WIDTH, this.HEIGHT);
        
        brush.setStroke(this.BORDER_COLOR);
        brush.strokeRect(this.POS_X, this.POS_Y, this.WIDTH, this.HEIGHT);
        
        brush.setFill(this.TEXT_COLOR);
        // there is another text option which has max coordinates this may be useful for making text stay in box.
        brush.fillText(this.getText(),
                this.POS_X + 20,
                this.POS_Y + 20);
    }
}

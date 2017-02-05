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
import javafx.scene.text.Font;

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
    
    private final int LINE_WIDTH = 49; //monospaced characters
    private final int TEXT_HEIGHT = 8;
    
    private final Color TEXT_COLOR = Color.WHITE;
    private final Color BACKGROUND_COLOR = Color.BLACK;
    private final Color BORDER_COLOR = Color.WHITE;
    
    private ArrayList<String> pages;
    private int currentPage = 0;
    
    private String[] answers;
    private ArrayList<String> responses;
    private int[] points;
    
    private int questionPagesSize;
    
    public Message(String question, String[] answers, ArrayList<String> responses, int[] points)
    {
        this.answers = answers;
        this.responses = responses;
        this.points = points;
        
        this.pages = new ArrayList();
        
        String tempStr = question;
        int lineCount = 0;
        String currPage = "";
        while(tempStr.length() > 0)
        {
            if(lineCount < TEXT_HEIGHT)
            {
                if(tempStr.length() > LINE_WIDTH)
                {
                    int tempInt = tempStr.lastIndexOf(" ", LINE_WIDTH);
                    currPage += tempStr.substring(0, tempInt) + "\n";
                    tempStr = tempStr.substring(tempInt+1);
                }
                else
                {
                    currPage += tempStr;
                    for(int i = 0; i < 8-lineCount; i++)
                    {
                        currPage += "\n";
                    }
                    currPage += "\n          Press [ENTER] to continue...";
                    tempStr = "";
                }
                lineCount++;
            }
            else
            {
                pages.add(currPage + "\n          Press [ENTER] to continue...");
                currPage = "";
                lineCount = 0;
            }
        }
        pages.add(currPage);
        questionPagesSize = pages.size();
        currPage = "";
        lineCount = 0;
        for(int i = 0; i < answers.length; i++)
        {
            tempStr = (i+1) + ". " + answers[i];
            while(tempStr.length() > 0)
            {
                if(lineCount < TEXT_HEIGHT)
                {
                    if(tempStr.length() > LINE_WIDTH -2)
                    {
                        int tempInt = tempStr.lastIndexOf(" ", LINE_WIDTH);
                        currPage += tempStr.substring(0, tempInt) + "\n";
                        tempStr = "  " + tempStr.substring(tempInt);
                    }
                    else
                    {
                        currPage += tempStr + "\n";
                        tempStr = "";
                    }
                    lineCount++;
                }
                else
                {
                    pages.add(currPage + "\n          Press [ENTER] to continue...");
                    currPage = "";
                    lineCount = 0;
                }
            }
        }
        pages.add(currPage);
        
        for(String pg : pages)
        {
            System.out.println("-------------\n" + pg + "\n-------------");
        }
    }
    
    @Override
    public String toString()
    {
        return this.getText();
    }
    
    public String getText()
    {
        return this.getPages().get(currentPage);
    }
    
    public int[] getPoints()
    {
        return this.points;
    }
    
    public ArrayList<String> getPages()
    {
        return this.pages;
    }
    
    public int getPage()
    {
        return this.currentPage;
    }
    
    public String getResponse(int i)
    {
        return this.responses.get(i);
    }
    
    public int getPoints(int i)
    {
        return this.points[i];
    }
    
    public int getQuestionPagesSize()
    {
        return this.questionPagesSize;
    }
    
    public void addText(String txt)
    {
        this.getPages().add(txt);
        currentPage = this.getPages().indexOf(txt);
    }
    
    public void setText(String txt)
    {
        this.pages.set(this.currentPage, txt);
    }
    
    public void setPage(int i)
    {
        this.currentPage = i;
    }
    
    public void showQuestion()
    {
        for(int i = 0; i < responses.size(); i++)
        {
            this.pages.remove(responses.get(i));
        }
        this.setPage(0);
    }
    
    public void showResponse(int i)
    {
        this.pages.add(this.responses.get(i));
        this.currentPage = this.pages.size() - 1;
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
        brush.fillText(this.getText(),
                this.POS_X + 20,
                this.POS_Y + 20);
    }
}

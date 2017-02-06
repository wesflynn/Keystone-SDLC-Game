package game.objects;

import javafx.scene.paint.Color;

/**
 * Class for Non-Playable Characters
 */
public class NPC extends Character
{
    private final Message question;
    
    private boolean questionListener = true;
    private boolean nextListener = false;
    private boolean backListener = false;
    private boolean answerListener = false;
    private boolean endcontact=false;
    private int currentMessage = 0;
    
    public boolean firstEnter = true;
    
    public NPC(double x, double y, Color color,
            Message question)
    {
        super(x, y, color);
        
        this.question = question;
    }
    
    @Override
    public void update()
    {
        
    }
    
    public Message getQuestion()
    {
        return this.question;
    }
    
    public boolean getQuestionListener()
    {
        return this.questionListener;
    }
    
    public boolean getNextListener()
    {
        return this.nextListener;
    }
    
    public boolean getBackListener()
    {
        return this.backListener;
    }
    
    public boolean getAnswerListener()
    {
        return this.answerListener;
    }
    
    public int getCurrentMessage()
    {
        return this.currentMessage;
    }
    
    public void setQuestionListener(boolean listener)
    {
        this.questionListener = listener;
    }
    
    public void setNextListener(boolean listener)
    {
        this.nextListener = listener;
    }
    
    public void setBackListener(boolean listener)
    {
        this.backListener = listener;
    }
    
    public void setAnswerListener(boolean listener)
    {
        this.answerListener = listener;
    }
    
    public void setCurrentMessage(int i)
    {
        this.currentMessage = i;
    }

    public boolean isEndcontact() {
        return endcontact;
    }

    public void setEndcontact(boolean endcontact) {
        this.endcontact = endcontact;
    }
    
}

package game.objects;

import javafx.scene.paint.Color;

/**
 * Class for Non-Playable Characters
 */
public class NPC extends Character
{
    private static long counter = 1L;
    private final long ID = counter;
    
    private final Message question;
    private final String[] answers;
    private final Message[] responses;
    
    private boolean questionListener = true;
    private boolean answerListener = false;
    private int currentMessage = 0;
    
    public NPC(double x, double y, Color color,
            Message question, String[] answers, Message[] responses)
    {
        super(x, y, color);
        counter++;
        
        this.question = question;
        this.answers = answers;
        this.responses = responses;
        
        this.question.setText(
                this.question.getText() + "\n"
                + "\n1. " + this.answers[0]
                + "\n2. " + this.answers[1]
                + "\n3. " + this.answers[2]
                + "\n4. " + this.answers[3]
        );
    }
    
    @Override
    public void update()
    {
        
    }
    
    public Message getQuestion()
    {
        return this.question;
    }
    
    public String[] getAnswers()
    {
        return this.answers;
    }
    
    public Message[] getResponses()
    {
        return this.responses;
    }
    
    public boolean getQuestionListener()
    {
        return this.questionListener;
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
    
    public void setAnswerListener(boolean listener)
    {
        this.answerListener = listener;
    }
    
    public void setCurrentMessage(int i)
    {
        this.currentMessage = i;
    }
}

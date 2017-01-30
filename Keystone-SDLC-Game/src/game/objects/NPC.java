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
}

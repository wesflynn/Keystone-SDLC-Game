package game.platform;

import game.objects.*;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class GameLevel
{
    public static GameLevel CURRENT_LEVEL;
    
    private final Scene scene;
    private final Player player;
    private final ProgressBar bar;
    private final ArrayList<NPC> npcs;
    private final Image bgImage;
    private final ArrayList<String> input;
    
    /**
     * Builds a new level
     * 
     * @param scene environment that the level is being drawn on
     * @param player character controlled by player
     * @param npcs array list of npc characters in level
     * @param background image of level background
     */
    public GameLevel(Scene scene, Player player, ArrayList<NPC> npcs, Image background)
    {
        this.scene = scene;
        this.player = player;
        this.npcs = npcs;
        this.bgImage = background;
        
        this.bar = new ProgressBar();
        
        input = GameLevel.getInput(scene);
    }
    
    public void updateAndDraw(GraphicsContext brush)
    {
        //  draw background
        brush.drawImage(bgImage, 0, 0);
        
        for(NPC npc : npcs)
        {
            npc.update();
            npc.draw(brush);
            
        }
        
        player.move(input);
        player.update();
        player.draw(brush);
        
        bar.update();
        bar.draw(brush);
        
        for(NPC npc : npcs)
        {
            if(this.player.intersects(npc))
            {
                if (npc.getQuestionListener())
                {
                    if(input.contains("ENTER"))
                    {
                        npc.setQuestionListener(false);
                        npc.setAnswerListener(true);
                    }
                    else
                    {
                        //show enter message
                    }
                }

                if(!npc.getQuestionListener())
                {
                    switch(npc.getCurrentMessage())
                    {
                        case 0:
                            npc.getQuestion().draw(brush);
                            break;
                        case 1:
                            npc.getResponses()[0].draw(brush);
                            break;
                        case 2:
                            npc.getResponses()[1].draw(brush);
                            break;
                        case 3:
                            npc.getResponses()[2].draw(brush);
                            break;
                        case 4:
                            npc.getResponses()[3].draw(brush);
                            break;
                    }
                }

                if(input.contains("DIGIT1") && npc.getAnswerListener())
                {
                    npc.setAnswerListener(false);
                    npc.setCurrentMessage(1);
                    this.bar.addProgress(npc.getResponses()[npc.getCurrentMessage()-1].getPoints());
                }
                else if(input.contains("DIGIT2") && npc.getAnswerListener())
                {
                    npc.setAnswerListener(false);
                    npc.setCurrentMessage(2);
                    this.bar.addProgress(npc.getResponses()[npc.getCurrentMessage()-1].getPoints());
                }
                else if(input.contains("DIGIT3") && npc.getAnswerListener())
                {
                    npc.setAnswerListener(false);
                    npc.setCurrentMessage(3);
                    this.bar.addProgress(npc.getResponses()[npc.getCurrentMessage()-1].getPoints());
                }   
                else if(input.contains("DIGIT4") && npc.getAnswerListener())
                {
                    npc.setAnswerListener(false);
                    npc.setCurrentMessage(4);
                    this.bar.addProgress(npc.getResponses()[npc.getCurrentMessage()-1].getPoints());
                }
            }
            else
            {
                npc.setAnswerListener(false);
                npc.setQuestionListener(true);
                npc.setCurrentMessage(0);
            }
        }
    }
    
    private static ArrayList<String> getInput(Scene scene)
    {
        ArrayList<String> input = new ArrayList<>();
        
        scene.setOnKeyPressed((KeyEvent e) -> {
            String code = e.getCode().toString();
            
            if (!input.contains(code))
            {
                input.add(code);
            }
        });

        scene.setOnKeyReleased((KeyEvent e) -> {
            String code = e.getCode().toString();
            
            input.remove(code);
        });
        
        return input;
    }
}

package game.platform;

import game.objects.*;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class GameLevel
{
    public static GameLevel CURRENT_LEVEL;
    public boolean levelComplete=false;
    private boolean titlecard;
    private Image card;
    private Image dead;
    private Image good;
    
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
    public GameLevel(Scene scene, Player player, ArrayList<NPC> npcs, Image background ,Image titleCard)
    {
        this.scene = scene;
        this.player = player;
        this.npcs = npcs;
        this.bgImage = background;
        this.card=titleCard;
        this.bar = new ProgressBar();
        this.titlecard=true;
        this.good=new Image("images/GOODENDING.png",800,780,false,false);
        this.dead=new Image("images/YOURDEAD.png",800,780,false,false);
        input = GameLevel.getInput(this.scene);
    }
    
    public void updateAndDraw(GraphicsContext brush)
    {
        if(input.contains("ENTER"))
            titlecard=false;
        
        if(input.contains("SPACE")&&(input.contains("CONTROL")))
            levelComplete=true;
        
        if(levelComplete==true)
        {
           Game.loadNextLevel(scene);
           levelComplete=false;
        }
        
        if(this.levelComplete)
        {
            Game.loadNextLevel(scene);
        }
        
        //  draw background
        brush.drawImage(bgImage, 0, 0);
        
        // update and draw npcs
        for(NPC npc : npcs)
        {
            npc.update();
            npc.draw(brush);   
        }
        // read input for player movement
        player.move(input);
        
        // check for collision with edge of screen
        if(player.getPosX() - Player.SPEED <= 0
                && player.getVelocityX() < 0)
        {
            player.setVelocityX(0);
        }
        else if(player.getPosX() + player.getWidth() + Player.SPEED > Game.DEFAULT_WIDTH
                && player.getVelocityX() > 0)
        {
            player.setVelocityX(0);
        }
        
        if(player.getPosY() - Player.SPEED <= 0
                && player.getVelocityY() < 0)
        {
            player.setVelocityY(0);
        }
        else if(player.getPosY() + player.getHeight() + Player.SPEED > Game.DEFAULT_HEIGHT
                && player.getVelocityY() > 0)
        {
            player.setVelocityY(0);
        }
        
        // update and draw player
        player.update();
        player.draw(brush);
        
        // update and draw progress bar
        bar.update();
        bar.draw(brush);
        
        if(titlecard==true){
            brush.drawImage(card, 0, 0);
        }
        // interpret message for any npc close to player
        // and show
        for(NPC npc : npcs)
        {
            this.handleInteraction(brush, npc);
        }
        
        if (bar.getProgress()>=10)
        {
            brush.drawImage(good, 0, 0);
        }
        if (bar.getProgress()<=0)
        {
            brush.drawImage(dead, 0, 0);
        }
    }
    
    private void handleInteraction(GraphicsContext brush, NPC npc)
    {
        if(this.player.intersects(npc))
            {
                // tell the player to hit enter when near a player
                if (npc.getQuestionListener())
                {
                    if(input.contains("ENTER"))
                    {
                        npc.setQuestionListener(false);
                        npc.setAnswerListener(true);
                        npc.setNextListener(true);
                        npc.setBackListener(true);
                    }
                    else
                    {
                        //show enter message
                    }
                }

                // show appropriate message
                if(!npc.getQuestionListener())
                {
                    npc.getQuestion().draw(brush);
                }
                
                // Read input if none have been recieved
                if(npc.getQuestion().getPage() > npc.getQuestion().getQuestionPagesSize()-1)
                {
                    if(input.contains("DIGIT1") && npc.getAnswerListener())
                    {
                        System.out.println("Digit 1");
                        npc.setAnswerListener(false);
                        npc.getQuestion().showResponse(0);
                        this.bar.addProgress(npc.getQuestion().getPoints(0));
                        npc.setEndcontact(true);
                    }
                    else if(input.contains("DIGIT2") && npc.getAnswerListener())
                    {
                        System.out.println("Digit 1");
                        npc.setAnswerListener(false);
                        npc.getQuestion().showResponse(1);
                        this.bar.addProgress(npc.getQuestion().getPoints(1));
                        npc.setEndcontact(true);
                    }
                    else if(input.contains("DIGIT3") && npc.getAnswerListener())
                    {
                        System.out.println("Digit 1");
                        npc.setAnswerListener(false);
                        npc.getQuestion().showResponse(2);
                        this.bar.addProgress(npc.getQuestion().getPoints(2));
                        npc.setEndcontact(true);
                    }   
                    else if(input.contains("DIGIT4") && npc.getAnswerListener())
                    {
                        System.out.println("Digit 1");
                        npc.setAnswerListener(false);
                        npc.getQuestion().showResponse(3);
                        this.bar.addProgress(npc.getQuestion().getPoints(3));
                        npc.setEndcontact(true);
                    }
                }
                
                if(!npc.firstEnter && input.contains("ENTER") && npc.getNextListener())
                {
                    npc.setNextListener(false);
                    System.out.println("Enter!");
                    
                    if(npc.getQuestion().getPage() < npc.getQuestion().getPages().size() - 1)
                    {
                        npc.getQuestion().setPage(npc.getQuestion().getPage()+1);
                    }
                }
                else if(!input.contains("ENTER") && !npc.getNextListener())
                {
                    npc.setNextListener(true);
                }
                else if(input.contains("BACK_SPACE") && npc.getBackListener())
                {
                    npc.setBackListener(false);
                    System.out.println("Backspace!");
                    
                    if(npc.getQuestion().getPage() > 0 && npc.getAnswerListener())
                    {
                        npc.getQuestion().setPage(npc.getQuestion().getPage()-1);
                    }
                }
                else if(!input.contains("BACK_SPACE") && !npc.getBackListener())
                {
                    npc.setBackListener(true);
                }
                
                if(npc.firstEnter && input.contains("ENTER") && npc.getNextListener())
                {
                    npc.firstEnter = false;
                    npc.setNextListener(false);
                }
                
                if(npc.isEndcontact() && input.contains("ENTER") && !npc.getNextListener())
                {
                    levelComplete=true;
                    npc.setEndcontact(false);
                }
            }
            //if not near npc, reset vars
            else
            {
                npc.firstEnter = true;
                npc.setAnswerListener(false);
                npc.setQuestionListener(true);
                npc.getQuestion().showQuestion();
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

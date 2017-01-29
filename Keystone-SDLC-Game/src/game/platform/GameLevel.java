package game.platform;

import game.objects.*;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class GameLevel
{
    private Scene scene;
    private Player player;
    private ArrayList<NPC> npcs;
    private Image bgImage;
    private int messageCounter =0;
    private ArrayList<String> input;
    
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
                    
        for(NPC npc : npcs)
        {
            if (player.intersects(npc)&&(input.contains("ENTER"))&&(messageCounter==0))
            {
                messageCounter++; 
            }
            if(!player.intersects(npc))
            {
                messageCounter=0;
            }
            if (messageCounter==1)
            {    
                npc.getMessage().draw(brush);
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

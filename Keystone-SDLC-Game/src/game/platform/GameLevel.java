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
    // messages should probably be stored in arrays and assigned to indviual npcs 
    private ArrayList<Message> level1Messages;
    
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
        
        ProgressBar bar=new ProgressBar();
        bar.update();
        bar.draw(brush);
        // just a quick fix maybe it makes more sense to use enums or have some sort of static message counter that increments when a new message object is created???
        // cant get follow up dialog working with same key system must be spamming it too fast
        // cant get it to add to progressbar for some reason?
        // messagecounter is in place so it you were hitting the same key over for additonal dialog you could have more then 2 states
        for(NPC npc : npcs)
        {
            if (player.intersects(npc)&&(input.contains("ENTER"))&&(messageCounter==0))
            {
                messageCounter++; 
            }
            if(!player.intersects(npc))
            {
                messageCounter=0;
                npc.getMessage().setText("press 1 for 1 point, 2 for 2, 3 to lose a point and 4 to lose 2");
            }
            if (messageCounter==1)
            {    
                npc.getMessage().draw(brush);
            }
            npc.getMessage().dialogOptions(input,"DIGIT1", "this should give you one point", messageCounter, bar, 1);
            npc.getMessage().dialogOptions(input,"DIGIT2", "this should give you two points", messageCounter, bar, 2);
            npc.getMessage().dialogOptions(input,"DIGIT3", "this should lose you one point", messageCounter, bar, -1);
            npc.getMessage().dialogOptions(input,"DIGIT4", "this should lose you two points", messageCounter, bar, -2);
            // this last one is here to check if the point system is working properly
            npc.getMessage().dialogOptions(input,"SPACE",bar.toString() , messageCounter, bar, 0);           
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

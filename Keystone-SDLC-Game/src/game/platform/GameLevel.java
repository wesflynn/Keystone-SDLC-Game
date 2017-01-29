package game.platform;

import game.objects.*;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class GameLevel
{
    /**
     * Builds a new level
     * 
     * @param player character controlled by player
     * @param npcs arraylist of npc characters in level
     * @param background image of level background
     */
    public GameLevel(Player player, ArrayList<NPC> npcs, Image background) {
        
        
        
        
    }
    
    public static ArrayList<String> getInput(Scene scene)
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

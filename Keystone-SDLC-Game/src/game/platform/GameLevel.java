package game.platform;

import game.objects.*;
import java.util.ArrayList;
import javafx.scene.image.Image;

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
        
        
        player.update();
        
        
    }
}

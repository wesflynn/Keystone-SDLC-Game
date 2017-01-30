package game.platform;

import game.objects.Message;
import game.objects.NPC;
import game.objects.Player;
import game.objects.ProgressBar;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * main class. Builds and runs the game.
 * 
 * @author adamb
 */
public class Game extends Application
{
    final private double DEFAULT_WIDTH = 800;
    final private double DEFAULT_HEIGHT = 780;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws Exception
    {
        // Use group if stackpane givees problems
        StackPane root = new StackPane();
        Scene scene = new Scene(root);    
        Canvas canvas = new Canvas(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        root.getChildren().add(canvas);
        
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        
        mainStage.setTitle("Keystone SDLC 2D Game");
        
        GraphicsContext brush = canvas.getGraphicsContext2D();
        
        // start of game loop logic
        AnimationTimer gameLoop = new AnimationTimer()
        {
            
            GameLevel testLevel = Game.buildTestLevel(scene);
            
            long then = System.nanoTime();
            @Override
            public void handle(long now)
            {
                GameLevel.CURRENT_LEVEL = testLevel;
                // Sleep for at least 1000000 nanoseconds
                //               a.k.a 1/1000th of a second
                if(now - then > 10000000)
                {
                    then = now;
                    GameLevel currentLevel = GameLevel.CURRENT_LEVEL;
                    
                    // Reset Canvas
                    this.resetCanvas(brush);
                    
                    // Update and Draw level
                    currentLevel.updateAndDraw(brush);
                }
            }

            public void resetCanvas(GraphicsContext brush)
            {
                brush.setFill(Color.BLACK);
                brush.fillRect(-10, -10,
                        canvas.getWidth()*2,
                        canvas.getHeight()*2);
            }
        };
        
        gameLoop.start();
        mainStage.show();
    }
    
    private static GameLevel buildTestLevel(Scene scene)
    {
        Player player = new Player();
        ProgressBar bar=new ProgressBar();
        ArrayList<NPC> npcs = new ArrayList<>();
        Image bgImage = new Image("images/office-bg1.jpg");
        
        // Build NPC 1
        Message npc1Question = new Message("press 1 for 1 point, 2 for 2, 3 to lose a point and 4 to lose 2");
        String[] npc1Answers = {
            "this should give you one point",
            "this should give you two points",
            "this should lose you one point",
            "this should lose you two points"
        };
        Message[] npc1Responses = {
            new Message("1p", 1),
            new Message("2p", 2),
            new Message("-1p", -1),
            new Message("-2p", -2)
        };
        
        NPC npc1 = new NPC(235, 225, Color.BLUE,
                npc1Question, npc1Answers, npc1Responses);
        
        
        // Build NPC 2
        Message npc2Question = new Message("press 1 for 1 point, 2 for 3, 3 to lose a point and 4 to lose 3");
        String[] npc2Answers = {
            "this should give you one point",
            "this should give you three points",
            "this should lose you one point",
            "this should lose you three points"
        };
        Message[] npc2Responses = {
            new Message("1p", 1),
            new Message("3p", 3),
            new Message("-1p", -1),
            new Message("-3p", -3)
        };
        
        NPC npc2 = new NPC(485, 575, Color.GREEN,
                npc2Question, npc2Answers, npc2Responses);
        
        // Add npcs to array list
        npcs.add(npc1);
        npcs.add(npc2);
        
        return new GameLevel(scene, player, npcs, bgImage);
    }
}

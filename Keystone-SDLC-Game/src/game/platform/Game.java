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
        
        Message question = new Message("press 1 for 1 point, 2 for 2, 3 to lose a point and 4 to lose 2");
        String[] answers = {
            "this should give you one point",
            "this should give you two points",
            "this should lose you one point",
            "this should lose you two points"
        };
        Message[] responses = {
            new Message("1p"),
            new Message("2p"),
            new Message("-1p"),
            new Message("-2p")
        };
        
        NPC npc1 = new NPC(235, 225, Color.BLUE,
                question, answers, responses);
        npcs.add(npc1);
        
        return new GameLevel(scene, player, npcs, bgImage);
    }
}

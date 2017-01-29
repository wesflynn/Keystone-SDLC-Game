package game.platform;

import game.objects.Message;
import game.objects.Player;
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
                   
        // update and draw these things can likley be moved to game level class later
        Image bgImage = new Image("images/office-bg1.jpg");
        brush.drawImage(bgImage, 0, 0);
        Player player = new Player();
        // dont know why i had to do this to get npc to work in the game class , but other wise it wouldnt recognize NPC
        game.objects.NPC npc = new game.objects.NPC(235,225,Color.BLUE);
        // testing message class inputing text
        Message test = new Message("this is a simple test to show how text boxes work.");
        
        // start of game loop logic
        AnimationTimer gameLoop = new AnimationTimer() {
            
            ArrayList<String> input = GameLevel.getInput(scene);
            
            long then = System.nanoTime();
            @Override
            public void handle(long now) {
                if(now - then > 1000000) {
                    then = now;
                    
                    // Reset Canvas
                    this.resetCanvas(brush);

                   //  draw background
                    brush.drawImage(bgImage, 0, 0);

                    // player movement
                    player.move(input);
                    player.update();
                    player.draw(brush);
                    //can move drawing npc out of the loop after just put in here for test purposes
                    npc.draw(brush);
                    
                    //just for testing purposes
                    if (player.intersects(npc))
                    {
                        test.draw(brush);
                    }
                }
            }

            public void resetCanvas(GraphicsContext brush) {
                brush.setFill(Color.BLACK);
                brush.fillRect(-10, -10, canvas.getWidth()*2, canvas.getHeight()*2);
            }
        };
        
        gameLoop.start();
        mainStage.show();
    }
    
}

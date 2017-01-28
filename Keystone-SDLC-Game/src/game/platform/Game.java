package game.platform;

import game.objects.Player;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
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
    long then = System.nanoTime();;
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
        
        ArrayList<String> input = new ArrayList<String>();
                    
        scene.setOnKeyPressed(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        if ( !input.contains(code) )
                            input.add( code );
                    }
            });

        scene.setOnKeyReleased(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    input.remove( code );
                }
            });
                   
        Image bgImage = new Image("images/office-bg1.jpg");

        // update and draw
        brush.drawImage(bgImage, 0, 0);
        Player player = new Player();
                    
        AnimationTimer gameLoop = new AnimationTimer() {
            
            @Override
            public void handle(long currentNanoTime)
            {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - then) / 1000000000.0;
                then = currentNanoTime;
                
                //couldn't figure out how to make game loop work using previous logic so i commented it out
//            @Override
//            public void handle(long now) {
//                if(now - then > 1000000) {
//                    then = now;
                    
                    // Reset Canvas
                    this.resetCanvas(brush);

                   //  draw background
                    brush.drawImage(bgImage, 0, 0);

                    // player movement                
                    player.setVelocity(0,0);
                        if(input.contains("UP"))
                            player.addVelocity(0,-50);
                        if(input.contains("LEFT"))
                            player.addVelocity(-50,0);    
                        if(input.contains("RIGHT"))
                            player.addVelocity(50,0);    
                        if(input.contains("DOWN"))
                            player.addVelocity(0,50);
                    player.update(elapsedTime);

                    
                    player.draw(brush);
                    
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

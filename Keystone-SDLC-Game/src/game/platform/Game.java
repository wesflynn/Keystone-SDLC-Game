package game.platform;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
        StackPane root = new StackPane();
        Scene scene = new Scene(root);
        
        Canvas canvas = new Canvas(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        root.getChildren().add(canvas);
        
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        
        mainStage.setTitle("Keystone SDLC 2D Game");
        
        GraphicsContext brush = canvas.getGraphicsContext2D();
        
        new AnimationTimer() {
            
            long then = System.nanoTime();
            @Override
            public void handle(long now) {
                if(now - then > 1000000) {
                    then = now;
                    
                    // Reset Canvas
                    this.resetCanvas(brush);

                    // update and draw
                    brush.drawImage(new Image("http://images.hanleywood.com/cvv/HomeOffice_Rem_birdseye.jpg"), 0, 0);
                }
            }
            
            public void resetCanvas(GraphicsContext brush) {
                brush.setFill(Color.RED);
                brush.fillRect(-10, -10, canvas.getWidth()*2, canvas.getHeight()*2);
            }
        }.start();
        
        mainStage.show();
    }
    
}

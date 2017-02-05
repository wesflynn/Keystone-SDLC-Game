package game.platform;

//import predefinded libs
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

//import custom libs
import game.objects.*;
import javafx.scene.text.Font;

/**
 * main class. Builds and runs the game.
 * 
 * @author adamb
 */
public class Game extends Application
{
    public static final double DEFAULT_WIDTH = 800;
    public static final double DEFAULT_HEIGHT = 780;
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
        brush.setFont(
                new Font(brush.getFont().getSize()).font("monospaced", 16)
        );
        
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
                    
                    // Reset Canvas
                    this.resetCanvas(brush);
                    
                    // Update and Draw level
                    GameLevel.CURRENT_LEVEL.updateAndDraw(brush);
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
        ArrayList<NPC> npcs = new ArrayList<>();
        Image bgImage = new Image("images/office-bg1.jpg");
        
        // Build NPC 1
        String[] npc1Answers = {
            "this should give you one point", //1
            "this should give you two points", //2
            "this should lose you one point", //3
            "this should lose you two points" //4
        };
        ArrayList<String> npc1Responses = new ArrayList()
        {{
            add("1p");
            add("2p");
            add("-1p");
            add("-2p");
        }};
        int[] npc1Points = {
            1, 2, -1, -2
        };
        Message npc1Question = new Message("This is a fake test question. Use ENTER to go forward a page. BACKSPACE to go back a page. and numbers 1 through 4 to answer questions.", npc1Answers, npc1Responses, npc1Points);
        
        NPC npc1 = new NPC(235, 225, Color.BLUE,
                npc1Question);
        
        
        // Build NPC 2
        String[] npc2Answers = {
            "this should give you one point", //1
            "this should give you two points Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sit amet finibus eros. Ut ipsum mauris, mattis sit amet erat facilisis, iaculis convallis felis. Ut fringilla sodales libero,", //2
            "this should lose you one point", //3
            "this should lose you two points" //4
        };
        ArrayList<String> npc2Responses = new ArrayList()
        {{
            add("1p");
            add("2p");
            add("-1p");
            add("-2p");
        }};
        int[] npc2Points = {
            1, 2, -1, -2
        };
        Message npc2Question = new Message("Sed tempus ullamcorper velit at porta. Donec venenatis laoreet sapien, a pharetra orci ornare condimentum. Nulla nec nisl sed risus tempus porta vel vitae tortor. Etiam eget nulla orci. Vivamus semper, metus vitae pretium aliquet, risus erat condimentum turpis, non porta ligula ligula gravida dui. Aliquam interdum quis ligula vitae blandit. Maecenas id quam dignissim, malesuada justo quis, eleifend augue. Quisque ut malesuada nibh. Maecenas bibendum, enim et rutrum tincidunt, nisl enim posuere quam, ac ullamcorper felis ante vel velit. In sagittis et tellus ac aliquet. Cras eget egestas elit. Aenean sollicitudin mi quis leo finibus aliquam. Sed dictum, quam et interdum posuere, purus elit gravida dolor, nec laoreet nibh velit et odio. Proin nec orci sed felis maximus laoreet. Quisque hendrerit, nisl non posuere interdum, lorem velit elementum nulla, vel bibendum ex metus in urna. Pellentesque eleifend condimentum tellus, nec aliquet nisl consectetur convallis.", npc2Answers, npc2Responses, npc2Points);
        
        NPC npc2 = new NPC(485, 575, Color.GREEN,
                npc2Question);
        
        // Add npcs to array list
        npcs.add(npc1);
        npcs.add(npc2);
        
        return new GameLevel(scene, player, npcs, bgImage);
    }
    
    private static GameLevel level2(Scene scene)
    {
        
        
        
        //temp
        return null;
    }
}

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
            
            //GameLevel testLevel = Game.buildTestLevel(scene);
            GameLevel level1 = Game.level1(scene);
            
            long then = System.nanoTime();
            @Override
            public void handle(long now)
            {
                GameLevel.CURRENT_LEVEL = level1;
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
            add("1 point");
            add("2 point");
            add("-1 point");
            add("-2 point");
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
    
    private static GameLevel level1(Scene scene)
    {
       
        Player player = new Player();
        ArrayList<NPC> npcs = new ArrayList<>();
        Image bgImage = new Image("images/office-bg1.jpg");
        
        // Build NPC 1
        String[] npc1Answers = {
            "People from the team have become increasing interested in wearable tech,\n" +
"		with team members purchasing iPhone watches. Perhaps creating a watch that \n" +
"		can work with both iPhone and Android is a good move to make.", //Should give you one point
            
            "Spend some extra time thinking of a new product that no one else seems to be producing and introduce it to the market", //2
            
            "The team has suggested that we just buy a bunch of fake iPhones from \n" +
"		the Chinese market and rebrand them and translate them into English. ", //This should make you lose one point
            
            "A member of the team remembers reading something about nanotechnology and \n" +
"		cybernetic bodily enhancements. He thinks that pushing an untested and \n" +
"		slightly dangerous technology is a ballsy move that could push the company\n" +
"		into the future." //Should make you lose two points 
        };
        ArrayList<String> npc1Responses = new ArrayList()
        {{
            add("1 point");
            add("2 point");
            add("-1 point");
            add("-2 point");
        }};
        int[] npc1Points = {
            1, 2, -1, -2
        };
        Message npc1Question = new Message("Without giving you the details the company expects a lot from you, only telling you that they\n" +
"\"Want a new idea that will bring profit and secure for the company\" The problem you are \n" +
"facing seems to be quite vague. The team has met and conversed and come up with a few ideas\n" +
"in terms of what to create for the future.", npc1Answers, npc1Responses, npc1Points);
        
        NPC npc1 = new NPC(235, 225, Color.BLUE,
                npc1Question);
        
        
        // Build NPC 2
        String[] npc2Answers = {
            "You look at previous jobs that your team members have done and their job\n" +
"		within the project and assign roles based on that.", //1
            
            "You ask all your employees to list what skills they have and send them to\n" +
"		you. You look at the skills and jobs ahead and assign an employee to each one.", //2
            
            "Since the project is in its early phase it doesn’t really matter who works\n" +
"		on what so let every pick the job they want to have no matter what their skills\n" +
"		are in that area.", //3
            
            "Stressed with the task that you face, you decided that you should let\n" +
"		fate decided who does what. You write the jobs on a piece of paper and \n" +
"		the names of all your employees on the other. You close your eyes and \n" +
"		at random point to a name and job." //4
        };
        ArrayList<String> npc2Responses = new ArrayList()
        {{
            add("1 point");
            add("2 points");
            add("-1 point");
            add("-2 points");
        }};
        int[] npc2Points = {
            1, 2, -1, -2
        };
        Message npc2Question = new Message("I know the company has given you a monumental task to come up with something to save the \n" +
"company. But we need to distribute our resources appropriately in order to increase \n" +
"efficiency.", npc2Answers, npc2Responses, npc2Points);
        
        NPC npc2 = new NPC(485, 575, Color.GREEN,
                npc2Question);
        
         // Build NPC 3
        String[] npc3Answers = {
            "Ditch every idea you have had so far and go with the cybernetic bodily \n" +
"		enhancements and ask the company for nothing short of a fortune to get\n" +
"		it off the ground.", //1
            
            "Bring the team together and ask them based on their skills what they think \n" +
"		the budget should be, both with R&D, building and production. Giving you\n" +
"		the best possible chance to stay within budget.", //2
            
            "You think that in a crunch the team could pull it off with a conservative\n" +
"		budget, but this also leaves room for you to go over budget.", //3
            
            "You decide to calculate the value of each team members life savings based \n" +
"		on nothing but pure speculation. You add it together, put a zero on the end\n" +
"		and send the boss an email saying \"We need this amount of money." //4
        };
        ArrayList<String> npc3Responses = new ArrayList()
        {{
            add("-2 points");
            add("2 points");
            add("1 point");
            add("-1 point");
        }};
        int[] npc3Points = {
            -2, 2, 1, -1
        };
        Message npc3Question = new Message("The company doesn't really have a blank check to give us for this project, in fact I don't\n" +
"think they have any cheques left to right, but they did give us a chance to write a proposal\n" +
"to say how much we need.", npc3Answers, npc3Responses, npc3Points);
        
        NPC npc3 = new NPC(385, 475, Color.ORANGE,
                npc3Question);
        
       // Build NPC 4
        String[] npc4Answers = {
            "Each team member has to report on how much time they think is required \n" +
"		for each section of the project. Based on the teams report you create the \n" +
"		schedule for the project.", //1
            
            "As project lead you don’t think that a time line would solve anything.\n" +
"		Its going to get done when it gets done. Let the team be free and work at \n" +
"		liesure.", //2
            
            "You set sprints and smaller due dates to get the project on track.", //3
            
            "You send an end date in the future and tell the team the project must be \n" +
"		done by that date, keeping the team on a tight leash." //4
        };
        ArrayList<String> npc4Responses = new ArrayList()
        {{
            add("2 points");
            add("-2 points");
            add("1 point");
            add("-1 point");
        }};
        int[] npc4Points = {
            2, -2, 1, -1
        };
        Message npc4Question = new Message("I hear the company is in a tight spot right now and can't afford any delays or we are all\n" +
"out of a job. We should probably set some time lines so that we can get this product to \n" +
"market.", npc4Answers, npc4Responses, npc4Points);
        
        NPC npc4 = new NPC(285, 275, Color.YELLOW,
                npc4Question); 
        
        // Build NPC 5
        String[] npc5Answers = {
            "Reassure him that the team has been hand picked and that we need to support our team as much as we can.", //1
            
            "You think that anything can be done you ignore the complaint and proceed\n" +
"		to tell the employee about a more grand idea that is completely beyond the \n" +
"		realm of possibility.", //2
            
            "Within the group you decide to have a debate and appoint the employee who\n" +
"		had the concern to the \"con\" side and have someone else appointed to the\n" +
"		\"pro\" side.", //3
            
            "Ignore the complaint and tell the employee that if they speak out again they\n" +
"		will be off the project." //4
        };
        ArrayList<String> npc5Responses = new ArrayList()
        {{
            add("1 points");
            add("-2 points");
            add("2 point");
            add("-1 point");
        }};
        int[] npc5Points = {
            1, -2, 2, -1
        };
        Message npc5Question = new Message("I have doubts that this product is going to be feasible on the market and think that we should \n" +
"have another look at just exactly we are trying to do.", npc5Answers, npc5Responses, npc5Points);
        
        NPC npc5 = new NPC(285, 275, Color.YELLOW,
                npc5Question); 
        
        
        // Add npcs to array list
        npcs.add(npc1);
        npcs.add(npc2);
        npcs.add(npc3);
        npcs.add(npc4);
        npcs.add(npc5);
        
        return new GameLevel(scene, player, npcs, bgImage);
    }
}

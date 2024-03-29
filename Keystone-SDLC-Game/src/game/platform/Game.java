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
    
    private static int currentLevel = 0;
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
        
        Game.loadNextLevel(scene);
        
        // start of game loop logic
        AnimationTimer gameLoop = new AnimationTimer()
        {
            long then = System.nanoTime();
            @Override
            public void handle(long now)
            {
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
    
    public static void loadNextLevel(Scene scene)
    {   
        currentLevel++;
        switch(currentLevel)
        {
            case 1:
                GameLevel.CURRENT_LEVEL = Game.level1(scene);
                break;
            case 2:
                GameLevel.CURRENT_LEVEL = Game.level2(scene);
                break;
            case 3:
                GameLevel.CURRENT_LEVEL = Game.level3(scene);
                break;
            case 4:
                GameLevel.CURRENT_LEVEL = Game.level4(scene);
                break;
            case 5:
                GameLevel.CURRENT_LEVEL = Game.level5(scene);
                break;
                
            default:
                new Exception("ERROR: Trying to load unknown game level: " + currentLevel);
        }
    }

    private static GameLevel level1(Scene scene)
    {
       
        Player player = new Player();
        ArrayList<NPC> npcs = new ArrayList<>();
        Image bgImage = new Image("images/office-bg1.jpg");
        Image title= new Image("images/LevelOne.png",800,780,false,false);
        
        // Build NPC 1
        String[] npc1Answers = {
            "People from the team have become increasing interested in wearable tech, with team members purchasing iPhone watches. Perhaps creating a watch that can work with both iPhone and Android is a good move to make.", //Should give you one point
            
            "Spend some extra time thinking of a new product that no one else seems to be producing and introduce it to the market", //2
            
            "The team has suggested that we just buy a bunch of fake iPhones from the Chinese market and rebrand them and translate them into English. ", //This should make you lose one point
            
            "A member of the team remembers reading something about nanotechnology and cybernetic bodily enhancements. He thinks that pushing an untested and slightly dangerous technology is a ballsy move that could push the company into the future." //Should make you lose two points 
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
        
        Message npc1Question = new Message("Without giving you the details the company expects a lot from you, only telling you that they " +
"\"Want a new idea that will bring profit and secure for the company\" The problem you are " +
"facing seems to be quite vague. The team has met and conversed and come up with a few ideas " +
"in terms of what to create for the future.", npc1Answers, npc1Responses, npc1Points);
        
        NPC npc1 = new NPC(235, 225, Color.BLUE,
                npc1Question);
        
        // Add npcs to array list
        npcs.add(npc1);
        
        return new GameLevel(scene, player, npcs, bgImage,title);
    }
    
    private static GameLevel level2(Scene scene)
    {
        Player player = new Player();
        ArrayList<NPC> npcs = new ArrayList<>();
        Image bgImage = new Image("images/office-bg1.jpg");
       Image title= new Image("images/LevelTwo.png",800,780,false,false); 
        // Build NPC 2
        String[] npc2Answers = {
            "You look at previous jobs that your team members have done and their job within the project and assign roles based on that.", //1
            
            "You ask all your employees to list what skills they have and send them to you. You look at the skills and jobs ahead and assign an employee to each one.", //2
            
            "Since the project is in its early phase it doesn’t really matter who works on what so let every pick the job they want to have no matter what their skills are in that area.", //3
            
            "Stressed with the task that you face, you decided that you should let fate decided who does what. You write the jobs on a piece of paper and the names of all" +
                "your employees on the other. You close your eyes and at random point to a name and job." //4
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
        Message npc2Question = new Message("I know the company has given you a monumental task to come up with something to save the company. But we need to distribute our " +
                "resources appropriately in order to increase efficiency.", npc2Answers, npc2Responses, npc2Points);
        
        NPC npc2 = new NPC(485, 575, Color.GREEN,
                npc2Question); 
        
        npcs.add(npc2);
        
        return new GameLevel(scene, player, npcs, bgImage,title);
    }
    
    private static GameLevel level3(Scene scene)
    {
        Player player = new Player();
        ArrayList<NPC> npcs = new ArrayList<>();
        Image bgImage = new Image("images/office-bg1.jpg");
        Image title= new Image("images/LevelThree.png",800,780,false,false);
        // Build NPC 3
        String[] npc3Answers = {
            "Ditch every idea you have had so far and go with the cybernetic bodily enhancements and ask the company for nothing short of a fortune to get it off the ground.", //1
            
            "Bring the team together and ask them based on their skills what they think the budget should be, both with R&D, building and production. Giving you " +
                "the best possible chance to stay within budget.", //2
            
            "You think that in a crunch the team could pull it off with a conservative budget, but this also leaves room for you to go over budget.", //3
            
            "You decide to calculate the value of each team members life savings based on nothing but pure speculation. You add it together, put a zero on the end " +
                "and send the boss an email saying \"We need this amount of money.\"" //4
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
        Message npc3Question = new Message("The company doesn't really have a blank check to give us for this project, in fact I don't think they have any " +
                "cheques left to right, but they did give us a chance to write a proposal to say how much we need.", npc3Answers, npc3Responses, npc3Points);
        
        NPC npc3 = new NPC(385, 475, Color.ORANGE,
                npc3Question); 
        
        npcs.add(npc3);
        
        return new GameLevel(scene, player, npcs, bgImage,title);
    }
        
    private static GameLevel level4(Scene scene)
    {
        Player player = new Player();
        ArrayList<NPC> npcs = new ArrayList<>();
        Image bgImage = new Image("images/office-bg1.jpg");
        Image title= new Image("images/LevelFour.png",800,780,false,false);
       // Build NPC 4
        String[] npc4Answers = {
            "Each team member has to report on how much time they think is required for each section of the project. Based on the teams report you create " +
                "the schedule for the project.", //1
            
            "As project lead you don’t think that a time line would solve anything. Its going to get done when it gets done. Let the team be free and work at liesure.", //2
            
            "You set sprints and smaller due dates to get the project on track.", //3
            
            "You send an end date in the future and tell the team the project must be done by that date, keeping the team on a tight leash." //4
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
        Message npc4Question = new Message("I hear the company is in a tight spot right now and can't afford any delays or we are all out of a job. We should " +
                "probably set some time lines so that we can get this product to market.", npc4Answers, npc4Responses, npc4Points);
        
        NPC npc4 = new NPC(285, 275, Color.YELLOW,
                npc4Question); 

        npcs.add(npc4);
        
        return new GameLevel(scene, player, npcs, bgImage,title);
    }
            
    private static GameLevel level5(Scene scene)
    {
        Player player = new Player();
        ArrayList<NPC> npcs = new ArrayList<>();
        Image bgImage = new Image("images/office-bg1.jpg");
        Image title= new Image("images/LevelFive.png",800,780,false,false);
        // Build NPC 5
        String[] npc5Answers = {
            "Reassure him that the team has been hand picked and that we need to support our team as much as we can.", //1
            
            "You think that anything can be done you ignore the complaint and proceed to tell the employee about a more grand idea that is completely beyond the realm of possibility.", //2
            
            "Within the group you decide to have a debate and appoint the employee who had the concern to the \"con\" side and have someone else appointed to the \"pro\" side.", //3
            
            "Ignore the complaint and tell the employee that if they speak out again they will be off the project." //4
        };
        ArrayList<String> npc5Responses = new ArrayList()
        {{
            add("1 points");
            add("-2 points");
            add("2 point");
            add("-1 point");
        }};
        int[] npc5Points = {
            10, -10, 20, -10
        };
        Message npc5Question = new Message("I have doubts that this product is going to be feasible on the market and think that we should have another look at just exactly we are trying to do.", npc5Answers, npc5Responses, npc5Points);
        
        NPC npc5 = new NPC(285, 275, Color.YELLOW,
                npc5Question); 
        
        npcs.add(npc5);
        
        return new GameLevel(scene, player, npcs, bgImage,title);
    }    

}
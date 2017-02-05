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
import static javafx.application.Application.launch;
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
            GameLevel level2 = Game.level2(scene);
            GameLevel level3 = Game.level3(scene);
            GameLevel level4 = Game.level4(scene);
            GameLevel level5 = Game.level5(scene);
            
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
    
    //Building level 1 of the SDLC
    private static GameLevel level1(Scene scene)
    {
       
        Player player = new Player();
        ArrayList<NPC> npcs = new ArrayList<>();
        Image bgImage = new Image("images/office-bg1.jpg");
        
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
        Message npc1Question = new Message("Without giving you the details the company expects a lot from you, only telling you that they \"Want a new idea that will bring profit and secure for the company\" The problem you are facing seems to be quite vague. The team has met and conversed and come up with a few ideas in terms of what to create for the future.", npc1Answers, npc1Responses, npc1Points);
        
        NPC npc1 = new NPC(235, 225, Color.BLUE,
                npc1Question);
        
        
        // Build NPC 2
        String[] npc2Answers = {
            "You look at previous jobs that your team members have done and their job within the project and assign roles based on that.", //1
            
            "You ask all your employees to list what skills they have and send them to you. You look at the skills and jobs ahead and assign an employee to each one.", //2
            
            "Since the project is in its early phase it doesn’t really matter who works on what so let every pick the job they want to have no matter what their skills 	are in that area.", //3
            
            "Stressed with the task that you face, you decided that you should let fate decided who does what. You write the jobs on a piece of paper and the names of all your employees on the other. You close your eyes and at random point to a name and job." //4
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
        Message npc2Question = new Message("I know the company has given you a monumental task to come up with something to save the company. But we need to distribute our resources appropriately in order to increase efficiency.", npc2Answers, npc2Responses, npc2Points);
        
        NPC npc2 = new NPC(485, 575, Color.GREEN,
                npc2Question);
        
         // Build NPC 3
        String[] npc3Answers = {
            "Ditch every idea you have had so far and go with the cybernetic bodily enhancements and ask the company for nothing short of a fortune to get it off the ground.", //1
            
            "Bring the team together and ask them based on their skills what they think the budget should be, both with R&D, building and production. Giving you the best possible chance to stay within budget.", //2
            
            "You think that in a crunch the team could pull it off with a conservative budget, but this also leaves room for you to go over budget.", //3
            
            "You decide to calculate the value of each team members life savings based on nothing but pure speculation. You add it together, put a zero on the end and send the boss an email saying \"We need this amount of money." //4
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
        Message npc3Question = new Message("The company doesn't really have a blank check to give us for this project, in fact I don't think they have any cheques left to right, but they did give us a chance to write a proposal to say how much we need.", npc3Answers, npc3Responses, npc3Points);
        
        NPC npc3 = new NPC(385, 475, Color.ORANGE,
                npc3Question);
        
       // Build NPC 4
        String[] npc4Answers = {
            "Each team member has to report on how much time they think is required for each section of the project. Based on the teams report you create the schedule for the project.", //1
            
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
        Message npc4Question = new Message("I hear the company is in a tight spot right now and can't afford any delays or we are all out of a job. We should probably set some time lines so that we can get this product to market.", npc4Answers, npc4Responses, npc4Points);
        
        NPC npc4 = new NPC(285, 275, Color.YELLOW,
                npc4Question); 
        
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
            1, -2, 2, -1
        };
        Message npc5Question = new Message("I have doubts that this product is going to be feasible on the market and think that we should have another look at just exactly we are trying to do.", npc5Answers, npc5Responses, npc5Points);
        
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
    
    //Building level 2 of the SDLC
     private static GameLevel level2(Scene scene)
    {
        Player player = new Player();
        ArrayList<NPC> npcs = new ArrayList<>();
        Image bgImage = new Image("images/office-bg1.jpg");
        
        // Build NPC 1
        String[] npc1Answers = {
            "Check to see if there is a Reddit page about it, if not create one, and proceed to start the discussion.", //1
            "Based on the skills on your employees, assign a member to do research based on the missing information your team needs.", //2
            "Wikipedia is a reliable resource, check there for any information you need.", //3
            "On your own time research a few things and apply them to the project." //4
        };
        ArrayList<String> npc1Responses = new ArrayList()
        {{
            add("-2 points");
            add("2 point");
            add("-1 point");
            add("1 point");
        }};
        int[] npc1Points = {
            -2, 2, -1, 1
        };
        Message npc1Question = new Message("I think that this is a good idea what we are trying to do, but I think that more information is needed before we can proceed with peace of mind.", npc1Answers, npc1Responses, npc1Points);
        
        NPC npc1 = new NPC(285, 275, Color.BLUE,
                npc1Question);
        
         // Build NPC 2
        String[] npc2Answers = {
            "Converse with your work team and decide which flaws and talents the crew has to decide what will be the main functions of the prototype.", //1
            
            "Make sure that the prototype has just enough for it to be alright.", //2
            
            "Get a list of ideas for the main functions and go through and define with your workers what functions will be in the prototype.", //3
            
            "You hand pick functions that you want to be in the prototype." //4
        };
        ArrayList<String> npc2Responses = new ArrayList()
        {{
            add("2 points");
            add("-2 point");
            add("1 point");
            add("-1 point");
        }};
        int[] npc2Points = {
            2, -2, 1, -1
        };
        Message npc2Question = new Message("So I was looking over all the requirements that this thing is going to need and I think we are going to need to consider what will be the main functions of the prototype?", npc2Answers, npc2Responses, npc2Points);
        
        NPC npc2 = new NPC(235, 225, Color.YELLOW,
                npc2Question);
        
        // Build NPC 3
        String[] npc3Answers = {
            "Reassure that as long as we push out a working and non bug filled product at launch it will do well on the market.", //1
            
            "Put loads of miniature add ons on the product, working or not, as long as it catches the customers eye. We will be sure to get them to purchase the product.", //2
            
            "Build the design based on time requirements and leave it as is, its only a prototpye anyway.", //3
            
            "Look back on the plans of the original design and develop the original then consider small things to add into the design once the main product is working well and will appeal to the customers." //4
        };
        ArrayList<String> npc3Responses = new ArrayList()
        {{
            add("1 point");
            add("-2 points");
            add("-1 point");
            add("2 points");
        }};
        int[] npc3Points = {
            1, -2, -1, 2
        };
        Message npc3Question = new Message("Boss, I think that we need a prototype of this product to ensure user compatibility and functionality. How complex should we make this thing.", npc3Answers, npc3Responses, npc3Points);
        
        NPC npc3 = new NPC(335, 325, Color.ORANGE,
                npc3Question);
        
        // Build NPC 4
        String[] npc4Answers = {
            "Perfect the features we have on the products and scrap the other features.", //1
            
            "Polish the features we have done. We will guarantee a line up after launch of applications that will be free to the public once released.", //2
            
            "Move on from the applications you’ve almost finished and start the main focus on the other applications.", //3
            
            "Skip to the features we need to finish, the consumers will enjoy more things to do when the watch finally launches." //4
        };
        ArrayList<String> npc4Responses = new ArrayList()
        {{
            add("1 point");
            add("2 points");
            add("-1 point");
            add("-2 points");
        }};
        int[] npc4Points = {
            1, 2, -1, -2
        };
        Message npc4Question = new Message("Boss, we are falling behind on schedule and we won’t be able to push out on some of the products features. What should we do?", npc4Answers, npc4Responses, npc4Points);
        
        NPC npc4 = new NPC(435, 425, Color.ORANGE,
                npc4Question);
        
        // Build NPC 5
        String[] npc5Answers = {
            "Just make a quick change that hardly does anything different.", //1
            
            "Gather the team and talk about an alternative that could be made in cause the original design is not quite as expected.", //2
            
            "Come up with a basic back up plan that involves building another prototype if needed.", //3
            
            "Scrap the entire project and redo everything based on the fact that its not perfect it right away." //4
        };
        ArrayList<String> npc5Responses = new ArrayList()
        {{
            add("-1 point");
            add("2 points");
            add("1 point");
            add("-2 points");
        }};
        int[] npc5Points = {
            -1, 2, 1, -2
        };
        Message npc5Question = new Message("The product is good and the prototypes should work out well, I dont really have any doubts but I think some people in the office dont feel the same way.", npc5Answers, npc5Responses, npc5Points);
        
        NPC npc5 = new NPC(135, 125, Color.GREEN,
                npc5Question);
        
        // Add npcs to array list
        npcs.add(npc1);
        npcs.add(npc2);
        npcs.add(npc3);
        npcs.add(npc4);
        npcs.add(npc5);
        
        return new GameLevel(scene, player, npcs, bgImage);
    }
     
     //Building level 3 of the SDLC
     private static GameLevel level3(Scene scene)
    {
        Player player = new Player();
        ArrayList<NPC> npcs = new ArrayList<>();
        Image bgImage = new Image("images/office-bg1.jpg");
        
        // Build NPC 1
        String[] npc1Answers = {
            "The design for the product came to you in a dream and youre sure itll be a hit even though its already been done before.", //1
            
            "Tell the graphic designer to just \"run with it\" based on the idea of the product.", //2
            
            "Use the specs as a guideline to create the design.", //3
            
            "Use the specs as a frame work to build upon leaving room for particular design changes based on testing." //4
        };
        ArrayList<String> npc1Responses = new ArrayList()
        {{
            add("-2 points");
            add("-1 point");
            add("1 point");
            add("2 point");
        }};
        int[] npc1Points = {
            -2, 2, -1, 1
        };
        Message npc1Question = new Message("The design specs have come back from our test group how do you want to proceed with the graphical interface?", npc1Answers, npc1Responses, npc1Points);
        
        NPC npc1 = new NPC(285, 275, Color.BLUE,
                npc1Question);
        
        //Build NPC 2 
        String[] npc2Answers = {
            "Do it, I'll get someone to help you on it so we can get that idea off the ground ", //1
            
            "Connecting to the internet is overrated.", //2
            
            "Go for it, but if it starts to take too long we need to abandon it.", //3
            
            "Connecting to the internet is good, but we cant go for it now since its not in the design plan." //4
        };
        ArrayList<String> npc2Responses = new ArrayList()
        {{
            add("2 points");
            add("-2 point");
            add("1 point");
            add("-1 point");
        }};
        int[] npc2Points = {
            2, -2, 1, -1
        };
        Message npc2Question = new Message("I have a great idea for the product to be able to connect to the internet like all other devices are now adays but it might take a little while for me to impliment it.", npc2Answers, npc2Responses, npc2Points);
        
        NPC npc2 = new NPC(185, 175, Color.YELLOW,
                npc2Question);
        
        //Build NPC 3
        String[] npc3Answers = {
            "Take the complaints and find the valid ones to change the system.", //1
            
            "Use the complaints to launch version 2.0 of the user interface.", //2
            
            "Ignore the complaints and base the UI on what needs to be done.", //3
            
            "Pick and choose the easy ones to fix." //4
        };
        ArrayList<String> npc3Responses = new ArrayList()
        {{
            add("1 points");
            add("2 point");
            add("-2 point");
            add("-1 point");
        }};
        int[] npc3Points = {
            1, 2, -2, -1
        };
        Message npc3Question = new Message("We have created a UI for the product but we have some user complaints and issues how should we proceed?", npc3Answers, npc3Responses, npc3Points);
        
        NPC npc3 = new NPC(385, 375, Color.GREEN,
                npc3Question);
        
        
        //Building NPC 4
        String[] npc4Answers = {
            "Email him and ask, hopefully he answers.", //1
            
            "Gather the team working on the system interface design and work up a solution together.", //2
            
            "Leave it, and potentially put the project a couple weeks behind.", //3
            
            "Make the change yourself." //4
        };
        ArrayList<String> npc4Responses = new ArrayList()
        {{
            add("1 points");
            add("2 point");
            add("-2 point");
            add("-1 point");
        }};
        int[] npc4Points = {
            1, 2, -2, -1
        };
        Message npc4Question = new Message("We have a system inferface designer thats out on vacation for a couple weeks but we really need for him to fix this problem what do you want us to do?", npc4Answers, npc4Responses, npc4Points);
        
        NPC npc4 = new NPC(485, 475, Color.PINK,
                npc4Question);
        
        //Build NPC 5
        String[] npc5Answers = {
            "Keep with the estimate, you figure that the product wont end up being that successful anyway.", //1
            
            "Choice a database that can scale but is ultimately restricted in the end.", //2
            
            "Leave the database design to a third party, but that would mean you need to warn the users that a third party would have access to their info, which might make some users decline to use your product.", //3
            
            "Find a database that can scale to your needs and protects users personal information." //4
        };
        ArrayList<String> npc5Responses = new ArrayList()
        {{
            add("-2 points");
            add("1 point");
            add("-1 point");
            add("2 point");
        }};
        int[] npc5Points = {
            -2, 1, -1, 2
        };
        Message npc5Question = new Message("You are trying to create a database to store users information and you have estimated the number of users you will have and the amount of data you need to store.", npc5Answers, npc5Responses, npc5Points);
        
        NPC npc5 = new NPC(485, 475, Color.PINK,
                npc5Question);
        
        
        // Add npcs to array list
        npcs.add(npc1);
        npcs.add(npc2);
        npcs.add(npc3);
        npcs.add(npc4);
        npcs.add(npc5);
        
        return new GameLevel(scene, player, npcs, bgImage);
    }
    //Building level 4 of the SDLC 
     private static GameLevel level4(Scene scene)
    {
        Player player = new Player();
        ArrayList<NPC> npcs = new ArrayList<>();
        Image bgImage = new Image("images/office-bg1.jpg");
        
        // Build NPC 1
        String[] npc1Answers = {
            "Send Jermey home, investigate the prototype and consider changing the material of band used on the product.", //1
            
            "Get Jermey to go to the doctor and investigate the prototype with another tester.", //2
            
            "Fire Jermey for not being able to test your unique product.", //3
            
            "Its going to be fine." //4
        };
        ArrayList<String> npc1Responses = new ArrayList()
        {{
            add("2 points");
            add("1 point");
            add("-1 point");
            add("-2 point");
        }};
        int[] npc1Points = {
            2, 1, -1, -2
        };
        Message npc1Question = new Message("Yo Boss, Jermey is testing the prototype out and says that it keeps hurting his eyes, what do you propose we do?", npc1Answers, npc1Responses, npc1Points);
        
        NPC npc1 = new NPC(285, 275, Color.BLUE,
                npc1Question);
        
        //Building NPC 2
        String[] npc2Answers = {
            "Offer the new employee some coffee and stand around awkwardly until you pat him on the back and go to your office.", //1
            
            "Give the new employee all the documentation you have on the product and set him free.", //2
            
            "Get one of your best employees who is fimiliar with the system to train the new employee.", //3
            
            "Sit down with the new employee for the rest of the week and make sure that he is up to speed with everthing that has been done so far." //4
        };
        ArrayList<String> npc2Responses = new ArrayList()
        {{
            add("-2 points");
            add("-1 point");
            add("1 point");
            add("2 point");
        }};
        int[] npc2Points = {
            -2, -1, 1, 2
        };
        Message npc2Question = new Message("Hey, so the data that we recieved from the control groups is in, what do you want me to do with it?", npc2Answers, npc2Responses, npc2Points);
        
        NPC npc2 = new NPC(385, 375, Color.GREEN,
                npc2Question);
        
        //Building NPC 3
        String[] npc3Answers = {
            "Hold off on the launch until all the products are up to date.", //1
            
            "Don't worry about the updates, no one pays attention to them anyway.", //2
            
            "Ship the watch but put out an update the same day.", //3
            
            "Ship the product but do a firmware update after release." //4
        };
        ArrayList<String> npc3Responses = new ArrayList()
        {{
            add("2 points");
            add("-2 point");
            add("-1 point");
            add("1 point");
        }};
        int[] npc3Points = {
            2, -2, -1, 1
        };
        Message npc3Question = new Message("The product is ready to ship but the problem is that we haven't installed the latest firmwire onto the product. What do you want to do?", npc3Answers, npc3Responses, npc3Points);
        
        NPC npc3 = new NPC(485, 475, Color.YELLOW,
                npc3Question);
        
        
        
        
        
         // Add npcs to array list
        npcs.add(npc1);
        npcs.add(npc2);
        npcs.add(npc3);
       
        
        return new GameLevel(scene, player, npcs, bgImage);
    }
     
      private static GameLevel level5(Scene scene)
    {
        Player player = new Player();
        ArrayList<NPC> npcs = new ArrayList<>();
        Image bgImage = new Image("images/office-bg1.jpg");
    
     //Building NPC 1 
        String[] npc1Answers = {
            "Wait to see what happens in the next few days and do nothing and blame any problems on the IT guy.", //1
            
            "Get some server space from a third party right away to handle the load, and get some more servers for the launch.", //2
            
            "Order another server so that the launch of the product will be a success and the server load can be handled.", //3
            
            "Limit the amount of people that can use the service at the same time." //4
        };
        ArrayList<String> npc1Responses = new ArrayList()
        {{
            add("-2 points");
            add("2 point");
            add("1 point");
            add("-1 point");
        }};
        int[] npc1Points = {
            -2, 2, 1, -1
        };
        Message npc1Question = new Message("Our product has be out for a while in beta testing but we seem to be having an overload on the server systems, Ive pumped as much juice out of them as I can, but its going to fail within the next few days as we add more users.", npc1Answers, npc1Responses, npc1Points);
        
        NPC npc1 = new NPC(185, 175, Color.PINK,
                npc1Question);
        
        //Building NPC 2 
        String[] npc2Answers = {
            "go low, just be cheap about it.", //1
            
            "Go high, we need to prevent hackers and other sites from building there own programs to modify the software we have already built inside the device for the customer.", //2
            
            "Go high, make sure none of those people get in our systems.", //3
            
            "Do we really need security for a this product?" //4
        };
        ArrayList<String> npc2Responses = new ArrayList()
        {{
            add("-1 points");
            add("2 point");
            add("1 point");
            add("-2 point");
        }};
        int[] npc2Points = {
            -1, 2, 1, -2
        };
        Message npc2Question = new Message("Boss, we are ugrading the security for our product, Should we do high security or low security?", npc2Answers, npc2Responses, npc2Points);
        
        NPC npc2 = new NPC(185, 375, Color.GREEN,
                npc2Question);
        
        //Building NPC 3 
        String[] npc3Answers = {
            "Create a hotline for users to call in complaints and questions about the product and an online email address where you can communicate with users as well as social media.", //1
            
            "Just stick it in small fine print in the instructions, make it a game for them.", //2
            
            "Honestly? It should be so simple to handle. If they don’t know how to do it from the start then they dont deserve to have our product anyway.", //3
            
            "Include a phone/email for users to contact us on." //4
        };
        ArrayList<String> npc3Responses = new ArrayList()
        {{
            add("2 points");
            add("-1 point");
            add("-2 point");
            add("1 point");
        }};
        int[] npc3Points = {
            2, -1, -2, 1
        };
        Message npc3Question = new Message("What system should be put in place to handle system issues from the customers?", npc3Answers, npc3Responses, npc3Points);
        
        NPC npc3 = new NPC(385, 375, Color.BLUE,
                npc3Question);
           // Add npcs to array list
        npcs.add(npc1);
        npcs.add(npc2);
        npcs.add(npc3);
       
        
        return new GameLevel(scene, player, npcs, bgImage);
    }
        
}

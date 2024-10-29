import java.awt.Color;

/**
 * Class bounceBox - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * This particular class was made from a duplicate of BallDemo class with a series of amendments 
 * and changes to better situate itself to BoxBall
 * 
 * We will have to make a new method here which will draw and instantiate the ball that ultimately will be animated within the class
 * 
 * @author Don Santiago
 * @version 2024.10.29
 */

public class boxBounce  
{
    private Canvas myCanvas;
    private BoxBall ball;

    /**
     * Create a canvas object. Creates a fresh canvas 
     * and makes it visible.
     *  
     */
    public boxBounce()
    {
        myCanvas = new Canvas("Ball Demo",1080,720); //full size canvas
        
        
        int ballDiameter = 30;
        Color ballColor = Color.RED;
        int initialXPos = 540; // Center
        int initialYPos = 360; // Center
        int groundPos = 680-ballDiameter; // Bottom of the canvas
        int ceilingPos = 0+ballDiameter; // Top of the canvas
        int leftBoundary = 0+ballDiameter; // Left edge
        int rightBoundary = 1080-ballDiameter; // Right edge
        
        ball = new BoxBall(initialXPos, initialYPos, ballDiameter, ballColor, 
                           groundPos, ceilingPos, leftBoundary, rightBoundary, myCanvas);
    }
    
    /**
     * Simulate single bouncing ball
     */
    public void bounce()
    {
        int frameWidth=1080; //this and the next line define the size of the frame
        int frameHeight=720;
        int rectWidth=640; //this and the next line define the size of the box
        int rectHeight=480;

        int topLeftX=(frameWidth-rectWidth)/2; //this plots the start of the rectangle from the corner
        int topLeftY=(frameHeight-rectHeight)/2; //this plots the distance of the rectangle from the corner
        
        int leftBoundary=topLeftX;
        int rightBoundary=topLeftX+rectWidth;
        int ceiling=topLeftY;
        int ground=topLeftY+rectHeight;
        
        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(leftBoundary,ground,rightBoundary,ground); //x location,start point,x location, end point
        
        //draw the left side bounds
        myCanvas.drawLine(leftBoundary,ceiling,leftBoundary,ground); //y location,starting point,y location, end point
        
        //draw the ceiling line
        myCanvas.drawLine(leftBoundary,ceiling,rightBoundary,ceiling); //x location, start point, x location, end point
        
        //draw the right side bounds
        myCanvas.drawLine(rightBoundary,ceiling,rightBoundary,ground); //y location,start point,y location,end point
        
        //the following lines mark X and Y coordinates within the confinds of the box so that
        //they can be used in the creation of drawing the ball
        int initialBallX=topLeftX+(rectWidth-16)/2;
        int initialBallY=topLeftY+(rectHeight-16)/2;

        // create and show the balls
        BoxBall ball = new BoxBall(initialBallX, initialBallY, 16, Color.BLUE, ground,ceiling,rightBoundary,leftBoundary, myCanvas);
        ball.draw();
        //BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        //ball2.draw(); //this was commented to hide the second ball btw

        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();

            if(ball.getXPosition() <=leftBoundary || ball.getXPosition()+ball.getDiameter()>=rightBoundary){
            }
            if(ball.getXPosition()>=200){
                finished = true;
            }
        }
    }
}

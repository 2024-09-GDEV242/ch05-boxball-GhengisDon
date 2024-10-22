import java.awt.Color;

/**
 * Class bounceBox - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * This particular class was made from a duplicate of BallDemo class with a series of amendments 
 * and changes to better situate itself to BoxBall
 * @author Don Santiago
 * @version 2024.22.10
 */

public class boxBounce  
{
    private Canvas myCanvas;

    /**
     * Create a CopyOfBallDemo object. Creates a fresh canvas and makes it visible.
     */
    public boxBounce()
    {
        myCanvas = new Canvas("Ball Demo",600,500);
    }

    /**
     * Simulate single bouncing ball
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, ground-600, 800, ground-600);

        // create and show the balls
        BoxBall ball = new BoxBall(50, 50, 16, Color.BLUE, ground,-600,800,-600, myCanvas);
        ball.draw();
        //BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        //ball2.draw(); //this was commented to hide the second ball btw

        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            //ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550){// || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
}

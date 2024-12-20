import java.awt.*;
import java.awt.geom.*;

/**
 * Class BoxBall - a graphical ball that observes the effect of gravity. The ball
 * has the ability to move. Details of movement are determined by the ball itself. It
 * will fall downwards, accelerating with time due to the effect of gravity, and bounce
 * upward again when hitting the ground.
 *
 * This movement can be initiated by repeated calls to the "move" method.
 * Additionally this BoxBall class is made from a copy of BouncingBall
 * 
 * @author Don Santiago
 *
 * @version 2024.10.29
 */

public class BoxBall
{

/**
* we introduce multiple things here to this block
* introduction of ceilingPosition, leftBounds,rightBounds
* and xSpeed the private data are things that should not be referrenced by other classes
* 
*/
    private static final int GRAVITY = 3;  // effect of gravity

    private int ballDegradation = 1;
    private Ellipse2D.Double circle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private final int groundPosition;      // y position of ground
    private final int ceilingPosition; //top boundry of the box
    private final int leftBounds; //left bounds of box
    private final int rightBounds; //right bounds of box
    private Canvas canvas;
    private int ySpeed = 1;                // initial downward speed
    private int xSpeed =1; //this was added to give a value to a speed going from along the x axis

    
    
    /**
     * Constructor for objects of class BoxBall
     *
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param ballDiameter  the diameter (in pixels) of the ball
     * @param ballColor  the color of the ball
     * @param groundPos  the position of the ground (where the wall will bounce)
     * 
     * @param ceilingPos the position of the ceiling of the box 
     * @param leftBounds the position of the left side of the box
     * @param rightBounds the position of the right side of the box
     * 
     * @param drawingCanvas  the canvas to draw this ball on
     */
    public BoxBall(int xPos, int yPos, int ballDiameter, Color ballColor,
                        int groundPos, int ceilingPos, int leftBoundry,
                        int rightBoundry, Canvas drawingCanvas)
    {
        xPosition = xPos; //this is the former position of the ball as (X,Y)
        yPosition = yPos; //this is the latter position of the ball as (X,Y)
        color = ballColor; //ball color as referenced 
        diameter = ballDiameter; //size of the ball is based off diameter size
        groundPosition = groundPos;
        ceilingPosition = ceilingPos;
        leftBounds = leftBoundry;
        rightBounds = rightBoundry;
        
        
        canvas = drawingCanvas;
    }

    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }

    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }    

    /**
     * Move this ball according to its position and speed and redraw.
     * 
     * in this section we will add a bunch of things here to make sure
     * once we hit one of the wall values we change the direction in the opposite
     * direction by toggling the +/- value 
     * by utilizing +-diameter we can ensure that the movement of the ball will only collide from the edge
     * it will also ensure that it remains inside of the box
     **/
    public void move()
    {
        // remove from canvas at the current position
        erase(); //if this comments out it will do one of those cool lapse things
            
        // compute new position
        ySpeed += GRAVITY; //instantiate the effects of gravity
        yPosition += ySpeed; //change y position
        xPosition +=xSpeed; //change x position

        // check if it has hit the ground otherwise check to see if it has hit the ceiling
        if (yPosition >= (groundPosition - diameter) && ySpeed > 0){ 
        
            yPosition = (int)(groundPosition - diameter);
            ySpeed = -ySpeed + ballDegradation;
        }

        if (yPosition <= (ceilingPosition+diameter) && ySpeed < 0){
        
            yPosition = (int)(ceilingPosition+diameter);
            ySpeed = ySpeed+ballDegradation;
        }
        
        // check to see if the ball has hit the left side bounds or the right side bounds
        if (xPosition >= (rightBounds - diameter) && xSpeed > 0){
        
            xPosition=(int)(rightBounds-diameter);
            xSpeed=-xSpeed+ballDegradation;
        }
        
        if (xPosition <= (leftBounds+diameter) && xSpeed < 0){
        
            xPosition=(int)(leftBounds+diameter);
            xSpeed=-xSpeed+ballDegradation;
        }
    

        // draw again at new position
        draw();
    }

    /**
     * return the horizontal position of this ball
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * return the vertical position of this ball
     */
    public int getYPosition()
    {
        return yPosition;
    }
    
    /**
     * return the diameter so that it can be used in boxBounce
     */
    public int getDiameter(){
        return diameter;
    }
}
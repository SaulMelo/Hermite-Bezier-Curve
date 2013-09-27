/*------------------------------------------------------------------------------
 * Assignment 2
 * Project: Graphics2
 * Drawing Hermite Curves
 * 
 * California Lutheran University
 * Advanced Computer Graphics
 * Written by: Kevin T. Duraj
 * 
 */
package graphics2;

public class Assignment2 {

    public static void main(String[] args) {

        int image_width = 600;
        int image_height = 600;
        double step = 0.001;

        System.out.println("Processing Hermite Curve ...");
        
        Hermite hermite = new Hermite(image_width, image_height, 0, 0, 0);
        //hermite.steps(128, 495, 313, 493, 12, 120, 474, 124, step);   
        //-------------- P0 -----  P1 --------  P'0 ---------  P'1 -------- 
        hermite.steps(128, 495, 313, 493, 12-128, 120-495, 474-313, 124-493, step);

        hermite.write("/Users/ktd/Desktop/hermite.png");
        
        System.out.println("Processing Bezier Curve ...");
        Bezier bezier = new Bezier(image_width, image_height, 0, 0, 0);
        
        //---------    P0       P1        P2         P3
        bezier.steps(93, 239, 207, 150, 207, 350, 339, 249, step);

        bezier.write("/Users/ktd/Desktop/bezier.png");        
    }
}
/*------------------------------------------------------------------------------
 * Assignment 2
 * Project: Graphics1
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

        System.out.println("Creating Hermite Curve ...");
        
        Hermite hermite = new Hermite(image_width, image_height, 0, 0, 0);
        hermite.two(128, 495, 313, 493, 12, 120, 474, 124);
        hermite.write("/Users/ktd/Desktop/hermite.png");
        
        Bezier bezier = new Bezier(image_width, image_height, 0, 0, 0);
        bezier.two(128, 495, 313, 493, 12, 120, 474, 124);
        bezier.write("/Users/ktd/Desktop/bezier.png");        
    }
}
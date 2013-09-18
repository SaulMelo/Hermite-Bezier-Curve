/*------------------------------------------------------------------------------
 * Bezier Curve: Drawing Curve using Bezier Algorithm
 * 
 * California Lutheran University
 * Advanced Computer Graphics
 * Written by: Kevin T. Duraj
 */
package graphics2;

/*------------------------------------------------------------------------------
 * Class Bezier using Bresenham between points
 */
public class Bezier extends Bresenham {

    public Bezier(int width_p, int height_p, int r, int g, int b) {

        super(width_p, height_p, r, g, b);
    }
    /*--------------------------------------------------------------------------
     *             Compute Bezier Points    
     *  +-----------------+   +-----------+
     *  | -1   3  -3   1  |   |  x0   y0  |
     *  |  3  -6   3   0  | * |  x1   y1  | * [t^3 t^2 t 1] = [x, y]
     *  | -3   3   0   0  |   |  x2   y2  |
     *  |  1   0   0   0  |   |  x3   y3  |
     *  +-----------------+   +-----------+
     *          M	            G
     -------------------------------------------------------------------------*/

    public int[] cubic(int x0, int y0,
            int x1, int y1,
            int x2, int y2,
            int x3, int y3) {
        /*------------------ Firt Column --------------------------*/
        int C3x = (-x0) + (3 * x1) + (-3 * x2) + (x3);   // first row
        int C2x = (3 * x0) + (-6 * x1) + (3 * x2) + (0);    // second row
        int C1x = (-3 * x0) + (3 * x1) + (0) + (0);    // third row
        int C0x = (x0) + (0) + (0) + (0);    // four row

        /*------------------ Second  Column -----------------------*/
        int C3y = (-y0) + (3 * y1) + (-3 * y2) + (y3);   // first row
        int C2y = (3 * y0) + (-6 * y1) + (3 * y2) + (0);    // second row
        int C1y = (-3 * y0) + (3 * y1) + (0) + (0);    // third row
        int C0y = (y0) + (0) + (0) + (0);    // four row        

        /*---------------------------------------------------------*/

        int array[] = {C0x, C0y, C1x, C1y, C2x, C2y, C3x, C3y};

        return array;

    }
    /*------------------------------------------------------------------------
     * Create Steps Method
     * Increasing steps make curve smooth
     * Calculate "z" as 3rd dimension
     */

    public void steps(int x0, int y0,
                             int x1, int y1,
                             int x2, int y2,
                             int x3, int y3) 
    {
        int array[] = cubic(x0, y0, x1, y1, x2, y2, x3, y3);
        int x, y, z;
        int C0x, C0y, C1x, C1y, C2x, C2y, C3x, C3y;

        C0x = array[0];
        C0y = array[1];
        C1x = array[2];
        C1y = array[3];
        C2x = array[4];
        C2y = array[5];
        C3x = array[6];
        C3y = array[7];

        double step = 1;
        int array2[][] = new int[(int) (1 / step)][2];
        int i = 0;

        for (double u = 0.00; u < 1; u += step) {
            x = (int) (C0x + C1x * u + C2x * u * u + C3x * u * u * u);
            y = (int) (C0y + C1y * u + C2y * u * u + C3y * u * u * u);

            array2[i][0] = x;
            array2[i][1] = y;
            i++;

            set_pixel(x, y, 255, 0, 0);
        }

        int j;
        
        for (j = 0; j < (int) (1 / step) - 1; j++) {

            bresenhamLine( array2[j][0]
                         , array2[j][1]
                         , array2[j + 1][0]
                         , array2[j + 1][1]
                         , 255, 0, 0);
        }

        // Draw the last line to the end point
        bresenhamLine(  array2[j][0]
                      , array2[j][1]
                      , x1
                      , y1
                      , 255, 0, 0);

    }
}
    
/*----------------------------------------------------------------------------*/
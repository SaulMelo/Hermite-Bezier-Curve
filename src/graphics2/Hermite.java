/*------------------------------------------------------------------------------
 * Hermite Curve: Drawing Curve using Hermite Algorithm
 * 
 * California Lutheran University
 * Advanced Computer Graphics
 * Written by: Kevin T. Duraj
 */
package graphics2;

/*------------------------------------------------------------------------------
 * Class Hermite using Bresenham between points
 */
public class Hermite extends Bresenham {

    public Hermite(int width_p, int height_p, int r, int g, int b) {

        super(width_p, height_p, r, g, b);
    }
    /*--------------------------------------------------------------------------
     *         Compute Hemite points and derivatives 
     *   -----------------     -----------
     *  |  2  -2   1   1  |   |  x0   y0  |
     *  | -3   3  -2   1  | * |  x1   y1  | * [t^3 t^2 t 1] = [x, y]
     *  |  0   0   1   0  |   |  x'0  y'0 |
     *  |  1   0   0   0  |   |  x'1  y'1 |
     *   -----------------     -----------
     *          M	            G
     -------------------------------------------------------------------------*/

    public int[] compute(int x0, int y0,
                         int x1, int y1,
                         int x0i, int y0i,
                         int x1i, int y1i) 
    {   
        // Compute derivative for Hermite only
        x0i = x0i - x0;
        x1i = x1i - x1;
        y0i = y0i - y0;
        y1i = y1i - y1;

        /*------------------ Firt Column --------------------------*/
        int C3x = 2 * x0 - 2 * x1 + x0i + x1i;        // first row
        int C2x = -3 * x0 + 3 * x1 - 2 * x0i - x1i;   // second row
        int C1x = x0i; // end point x
        int C0x = x0;  // start point x

        /*------------------ Second  Column -----------------------*/
        int C3y = 2 * y0 - 2 * y1 + y0i + y1i;       // first row
        int C2y = -3 * y0 + 3 * y1 - 2 * y0i - y1i;  // second row
        int C1y = y0i; // end point y
        int C0y = y0;  // start point y
        /*---------------------------------------------------------*/

        int array[] = {C0x, C0y, C1x, C1y, C2x, C2y, C3x, C3y};

        return array;

    }
    /*------------------------------------------------------------------------*/
    //Calculate "z" as 3rd dimension

    public void two(int x0, int y0, int x1, int y1, int x0i, int y0i, int x1i, int y1i) {


        int array[] = compute(x0, y0, x1, y1, x0i, y0i, x1i, y1i);
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

        double step = 0.001;
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

        for (int j = 0; j < (int) (1 / step) - 1; j++) {
            bresenhamLine(array2[j][0], array2[j][1], array2[j + 1][0], array2[j + 1][1], 255, 0, 0);
        }

    }
}
    
/*----------------------------------------------------------------------------*/
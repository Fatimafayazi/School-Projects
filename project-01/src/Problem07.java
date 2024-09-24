import processing.core.PApplet;
import javax.swing.*;

public class Problem07 extends PApplet {
    float orbit, dOrbit;
    int nRays, nSmallStars;
    float radius;
    float x, y, r;
    float alpha, beta;
    float minOrbit, maxOrbit;

    public void settings() {
        fullScreen();
    }

    public void setup() {
        alpha = random(2 * PI);
        beta = random(2 * PI);
        textSize(28);
        textAlign(CENTER);
        radius = height / 5f;
        orbit = radius ;
        dOrbit = 2;
        maxOrbit = orbit * 2;
        minOrbit = orbit;
        x = minOrbit;
        y = 0;
        r = height / 16f;
        try {
            nSmallStars = Integer.parseInt(JOptionPane.showInputDialog("Number of small stars [8, 12]: "));
            if (nSmallStars < 8 || 12 < nSmallStars) {
                JOptionPane.showMessageDialog(null, "Incorrect input " + nSmallStars);
                System.exit(1);
            }
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Incorrect input");
            System.exit(0);
        }
        try {
            nRays = Integer.parseInt(JOptionPane.showInputDialog("Number of rays [8, 10, 12]: "));
            if (nRays != 8 && nRays != 10 && nRays != 12) {
                JOptionPane.showMessageDialog(null, "Incorrect input " + nRays);
                System.exit(1);
            }
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Incorrect input");
            System.exit(0);
        }
    }

    public void draw() {
        background(0);
        translate(width / 2f, height / 2f);
        rotate(alpha);
        drawStar(0, 0, radius, 255f, 0f, 0f);

        for (int i = 0; i < nSmallStars; i++) {
            pushMatrix();
            translate(x, 0);
            rotate(beta);
            drawStar(0, 0, r, 0f, 0f, 255f);
            popMatrix();

            rotate(2 * PI / nSmallStars);
        }
        x += dOrbit;
        if (x >= maxOrbit || x <= minOrbit) {
            dOrbit = -dOrbit;
        }
        alpha -= 0.05f;
        beta += 0.1f;
    }

    private void drawStar(float centerX, float centerY, float r, float red, float green, float blue) {
        pushMatrix();
        float angle = 0;
        float dAngle = TWO_PI / nRays;
        translate(centerX, centerY);
        stroke(red, green, blue);
        float prevX = 0;
        float prevY = 0;

        for (int i = 0; i <= nRays; ++i) {
            float curR = r;
            if (i % 2 == 1) {
                curR /= 4;
            }
            float x = curR * cos(angle);
            float y = curR * sin(angle);
            line(0, 0, x, y);
            line(prevX, prevY, x, y);
            prevX = x;
            prevY = y;
            angle += dAngle;
        }
        popMatrix();
    }

    public static void main(String[] args) {
        PApplet.main("Problem07");
    }
}

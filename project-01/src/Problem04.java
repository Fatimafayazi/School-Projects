import processing.core.PApplet;

public class Problem04 extends PApplet {
    float circleX, circleY, radius;
    float circleX2, circleY2;
    int counter = 0;

    public void settings() {
        fullScreen();
    }

    public void setup() {
        background(0);
        frameRate(10);
        radius = 40;
        circleX = 7f * radius;
        circleY = 3f * radius;
        circleX2 = (int)(random(7,23)) * radius;
        circleY2 = (int)(random(3,13)) * radius;
    }

    public void draw() {
        textAlign(CENTER, TOP);
        textSize(35);
        fill(0, 255, 0);
        text("Game: Collect Yellow Circles Using Arrow Buttons",width / 2f, 20f);
        textAlign(CENTER, BOTTOM);
        textSize(30);
        fill(0, 255, 0);
        text("Score : " + counter,width / 2f, 16 * radius);

        for (float i = 6.5f * radius; i < 24.5f * radius ;i += radius) {
            for (float j = 2.5f * radius; j < 14.5f * radius; j += radius) {
                fill(0,0,0,25);
                stroke(0, 0, 150);
                rect(i, j, radius, radius);
            }
        }

        fill(255, 0, 0);
        stroke(0, 0, 0);
        circle(circleX, circleY, radius);
        if (key == CODED) {
            if (keyCode == UP) {
                circleY -= radius;
            } else if (keyCode == DOWN) {
                circleY += radius;
            } else if (keyCode == LEFT) {
                circleX -= radius;
            } else if (keyCode == RIGHT) {
                circleX += radius;
            }
        }

        if (circleX == 6 * radius) {
            circleX = 7 * radius;
        } else if (circleX == 25 * radius) {
            circleX = 24 * radius;
        }

        if (circleY == 2 * radius) {
            circleY = 3 * radius;
        } else if (circleY == 15 * radius) {
            circleY = 14 * radius;
        }

        fill(255, 255, 0);
        stroke(0, 0, 0);
        circle(circleX2, circleY2, radius);
        if (circleX == circleX2 && circleY == circleY2) {
            circleX2 = (int)(random(7,23)) * radius;
            circleY2 = (int)(random(3,13)) * radius;
            counter++;
            fill(0, 0, 0);
            stroke(0, 0, 0);
            rect(width/2f - 80,15 * radius,10 * radius, 5 * radius);
            textAlign(CENTER, BOTTOM);
            textSize(30);
            fill(0, 255, 0);
            text("Score : " + counter,width/2f, 16 * radius);
        }
    }

    public static void main(String[] args) {
        PApplet.main("Problem04");
    }
}

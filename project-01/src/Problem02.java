import processing.core.PApplet;

public class Problem02 extends PApplet {
    float x1, x2, x3;
    float y1, y2, y3;
    float dx1, dx2, dx3;
    float dy1, dy2, dy3;

    public void settings() {
        fullScreen();
    }

    public void setup() {
        x3 = width / 2f;
        y3 = height / 2f;
        x1 = x3 - 60;
        y1 = y3 - 60;
        x2 = x1 - 60;
        y2 = y1 - 60;
        dx3 = 10;
        dy3 = 10;
        dx1 = 10;
        dy1 = 10;
        dx2 = 10;
        dy2 = 10;
    }

    public void draw() {
        background(0);
        fill(0,255,0);
        circle(x1, y1, 80);
        fill(0,0,255);
        circle(x2, y2, 80);
        fill(255, 0, 0);
        circle(x3, y3, 80);
        x1 += dx1;
        y1 += dy1;
        x2 += dx2;
        y2 += dy2;
        x3 += dx3;
        y3 += dy3;

        if (x3 >= width) {
            dx3 = -dx3;
            x3 = width - 1;
        } else if (x1 >= width) {
            dx1 = -dx2;
            x1 = width - 1;
        } else if (x2 >= width){
            dx2 = -dx2;
            x2 = width - 1;
        }

        if (y3 >= height) {
            dy3 = -dy3;
            y3 = height - 1;
        } else if (y1 >= height) {
            dy1 = -dy1;
            y1 = height - 1;
        } else if (y2 >= height){
            dy2 = -dy2;
            y2 = height - 1;
        }

        if (x3 < 0) {
            dx3 = -dx3;
            x3 = 0;
        } else if (x1 < 0) {
            dx1 = -dx1;
            x1 = 0;
        } else if (x2 < 0){
            dx2 = -dx2;
            x2 = 0;
        }

        if (y3 < 0) {
            dy3 = -dy3;
            y3 = 0;
        } else if (y1 < 0) {
            dy1 = -dy1;
            y1 = 0;
        } else if (y2 < 0){
            dy2 = -dy2;
            y2 = 0;
        }
    }

    public static void main(String[] args) {
        PApplet.main("Problem02");
    }
}

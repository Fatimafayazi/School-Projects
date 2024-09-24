import processing.core.PApplet;

public class Problem01 extends PApplet {
    float maxNum = 150;
    float minNum = 20;
    float redColor = 255;
    float greenColor = 0;
    float blueColor = 0;
    float motion = 2;
    String message = "Hello, processing!!!";
    float messageSize = 10;

    public void settings() {
        fullScreen();
    }

    public void setup() {
        messageSize = minNum;
    }

    public void draw() {
        background(0);
        textSize(messageSize);
        textAlign(CENTER, CENTER);
        fill(redColor, greenColor, blueColor);
        text(message, width / 2.0f, height / 2.0f);
        messageSize += motion;

        if (messageSize == maxNum){
            motion = - motion;
        }

        if ( messageSize == minNum) {
            motion = -motion;
            if (redColor == 255) {
                redColor = 0;
                blueColor = 255;
            } else if (blueColor == 255) {
                blueColor = 0;
                greenColor = 255;
            } else if (greenColor == 255) {
                greenColor = 0;
                redColor = 255;
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main("Problem01");
    }
}

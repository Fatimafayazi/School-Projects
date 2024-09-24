import processing.core.PApplet;

public class Problem03 extends PApplet {
    float rectWidth, rectHeight;
    float x, y;

    public void settings() {
        fullScreen();
    }

    public void setup() {
        x = width / 3f;
        y = height / 6f;
        rectWidth = x;
        rectHeight = y / 2f;
        textSize(30);
        frameRate(30);
    }

    public void draw() {
        background(0);

        fill(0, 0, 225);
        stroke(0, 100, 215);
        rect(x, y , rectWidth, rectHeight);
        fill(225, 225, 225);
        text("Java Programming Language", (width / 2f) - 6, height / 4.5f);
        textAlign(CENTER);
        if (x <= mouseX && mouseX <= x + rectWidth && y <= mouseY && mouseY <= y + rectHeight) {
            fill(255, 0, 0);
            text("Java Programming Language", (width / 2f) - 6, height / 4.5f);
            fill(225, 225, 225);
            text("Year: 1995.PYPL Index:2 ", width / 2f, height - rectWidth / 10F);
        }

        fill(0, 0, 225);
        stroke(0, 100, 215);
        rect(x, y + (y / 2), rectWidth, rectHeight);
        fill(225, 225, 225);
        text("Kotlin Programming Language", (width / 2f) - 6, height / 3.3f);
        textAlign(CENTER);
        if (x <= mouseX && mouseX <= x + rectWidth && y + y / 2 <= mouseY && mouseY <= y + y / 2 + rectHeight) {
            fill(225, 0, 0);
            text("Kotlin Programming Language", (width / 2f) - 6, height / 3.3f);
            fill(225, 225, 225);
            text("Year: 2001.PYPL Index:11 ", width / 2f, height - rectWidth / 10f);
        }

        fill(0, 0, 255);
        stroke(0, 100, 215);
        rect(x, y + 2 * (y / 2f), rectWidth, rectHeight);
        fill(225, 225, 225);
        text("Scala Programming Language", (width / 2f) - 6, height / 2.6f);
        textAlign(CENTER);
        if (x <= mouseX && mouseX <= x + rectWidth && y + 2 * y / 2 <= mouseY && mouseY <= y + 2 * y / 2 + rectHeight) {
            fill(255, 0, 0);
            text("Scala Programming Language", (width / 2f) - 6, height / 2.6f);
            fill(225, 225, 225);
            text("Yea: 2001.PYPL Index:20 ", width / 2f, height - rectWidth / 10f);
        }
    }

    public static void main(String[] args) {
        PApplet.main("Problem03");
    }
}

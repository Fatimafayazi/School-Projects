import processing.core.PApplet;
import javax.swing.*;

public class Problem05 extends PApplet {
    float size;
    float scale = height / 2f;
    float x, y;
    int r, c;
    String col;

    public void settings() {
        fullScreen();
    }

    public void setup() {
        textSize(50);
        textAlign(CENTER);
        try {
            String strRadius = JOptionPane.showInputDialog("Enter the board's size: [4,12]: ");
            size = Float.parseFloat(strRadius);
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Incorrect Number");
            System.exit(1);
        }
        if (size < 4 || size > 12) {
            JOptionPane.showMessageDialog(null, "Incorrect Number");
            System.exit(1);
        }
    }

    public void draw() {
        background(0);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                x = i * scale;
                y = j * scale;
                if ((i % 2 == 0) == (j % 2 == 0)) {
                    fill(0);
                } else {
                    fill(255, 255, 255);
                }
                float intX = width / 3f;
                float intY = height / 6f;
                if (size == 12) {
                    intX = width / 3.5f;
                }
                if (size == 11) {
                    intX = width / 3.2f;
                }
                if (size == 4) {
                    intX = width / 2.4f;
                }
                if (size == 5) {
                    intX = width / 2.5f;
                }
                if (size == 7 || size == 6) {
                    intX = width / 2.7f;
                }

                stroke(0);
                rect(x + intX, y + intY, scale, scale);
                if (x + intX <= mouseX && mouseX <= x + intX + scale && y + intY <= mouseY && mouseY <= y + intY + scale) {
                    r = j + 1;
                    c = i + 1;
                    if ((i % 2 == 0) == (j % 2 == 0)) {
                        col = "black";
                    } else {
                        col = "white";
                    }
                    stroke(255, 0, 0);
                    strokeWeight(1.5f);
                    rect(x + intX + 1, y + intY + 1, scale - 2f, scale - 2f);
                    fill(255, 255, 0);
                    text("Row:" + r, width / 2f - width / 6f, height / 13f);
                    text("Column:" + c, width / 4f + width / 4.3f, height / 13f);
                    text("Color: " + col, width / 2f + width / 5.5f, height / 13f);
                }
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main("Problem05");
    }
}

import processing.core.PApplet;

public class Problem08 extends PApplet {
    int nLines, mIntersect, caseNum;
    float redStar, greenStar, blueStar;
    float red, green, blue;
    float squareSide, squareX, topSquareY, middleSquareY, bottomSquareY;
    float angle;
    float starX, starY;

    public void settings() {
        fullScreen();
    }

    public void setup() {
        textSize(35);
        textAlign(LEFT);
        squareSide = width / 30f;
        squareX = width / 6f;
        topSquareY = height / 2f - 2.5f * squareSide;
        middleSquareY = height / 2f - squareSide / 2f;
        bottomSquareY = height / 2f + 1.5f * squareSide;
        starX = width/ 2f;
        starY = height / 2f;
        angle = 0;
        frameRate(100);
        strokeWeight(3);
    }

    public void draw() {
        background(0);
        stroke(redStar, greenStar, blueStar);
        drawStar(nLines, mIntersect, angle);
        angle += 0.02;
        stroke(255f, 0f, 0f);
        red = 255f;
        green = 0f;
        blue = 0f;
        drawSquaresAndCircles(squareX, topSquareY, squareSide, "Red", "12:5");
        if (redStar == 255f) {
            stroke(redStar, 0f, 0f);
            fill(redStar, 0f, 0f);
            square(squareX, topSquareY, squareSide);
        }
        if (caseNum == 1) {
            stroke(255f, 255f, 0f);
            fill(255f, 255f, 0f);
            circle(5 * squareX, topSquareY + squareSide / 2f, squareSide);
        }
        stroke(0f, 255f, 0f);
        red = 0f;
        green = 255f;
        blue = 0f;
        drawSquaresAndCircles(squareX, middleSquareY, squareSide, "Green", "18:7");
        if (greenStar == 255f) {
            stroke(0f, greenStar, 0f);
            fill(0f, greenStar, 0f);
            square(squareX, middleSquareY, squareSide);
        }
        if (caseNum == 2) {
            stroke(255f, 255f, 0f);
            fill(255f, 255f, 0f);
            circle(5 * squareX, middleSquareY + squareSide / 2f, squareSide);
        }
        stroke(0f, 0f, 255f);
        red = 0f;
        green = 0f;
        blue = 255f;
        drawSquaresAndCircles(squareX, bottomSquareY, squareSide, "Blue", "23:11");
        if (blueStar == 255f) {
            stroke(0f, 0f, blueStar);
            fill(0f, 0f, blueStar);
            square(squareX, bottomSquareY, squareSide);
        }
        if (caseNum == 3) {
            stroke(255f, 255f, 0f);
            fill(255f, 255f, 0f);
            circle(5 * squareX, bottomSquareY + squareSide / 2f, squareSide);
        }
    }

    public void mousePressed() {
        if (squareX <= mouseX && mouseX <= squareX + squareSide &&
                topSquareY <= mouseY && mouseY <= topSquareY + squareSide) {
            if (redStar == 0) {
                redStar = 255f;
            } else {
                redStar = 0;
            }
        }

        if (squareX <= mouseX && mouseX <= squareX + squareSide &&
                middleSquareY <= mouseY && mouseY <= middleSquareY + squareSide) {
            if (greenStar == 0) {
                greenStar = 255f;
            } else {
                greenStar = 0;
            }
        }

        if (squareX <= mouseX && mouseX <= squareX + squareSide &&
                bottomSquareY <= mouseY && mouseY <= bottomSquareY + squareSide) {
            if (blueStar == 0) {
                blueStar = 255f;
            } else {
                blueStar = 0;
            }
        }

        if (5 * squareX - squareSide / 2f <= mouseX && mouseX <= 5 * squareX - squareSide / 2f + squareSide &&
                topSquareY <= mouseY && mouseY <= topSquareY + squareSide) {
            caseNum = 1;
            nLines = 12;
            mIntersect = 5;
        }

        if (5 * squareX - squareSide / 2f <= mouseX && mouseX <= 5 * squareX - squareSide / 2f + squareSide &&
                middleSquareY <= mouseY && mouseY <= middleSquareY + squareSide) {
            caseNum = 2;
            nLines = 18;
            mIntersect = 7;
        }

        if (5 * squareX - squareSide / 2f <= mouseX && mouseX <= 5 * squareX - squareSide / 2f + squareSide &&
                bottomSquareY <= mouseY && mouseY <= bottomSquareY + squareSide) {
            caseNum = 3;
            nLines = 23;
            mIntersect = 11;
        }
    }

    private void drawSquaresAndCircles(float squareX, float squareY, float squareSide, String color, String ratio) {
        noFill();
        square(squareX, squareY, squareSide);
        fill(red, green, blue);
        text("" + color, squareX + squareSide * 1.5f, squareY + squareSide);
        noFill();
        stroke(255f, 255f, 0f);
        circle(5 * squareX, squareY + squareSide / 2f, squareSide);
        fill(255f, 255f, 0f);
        text("" + ratio, 5 * squareX + squareSide, squareY + squareSide);
    }

    private void drawStar(int nLines, int mIntersect, float angle) {
        float angleChange = 2 * PI / nLines;
        float starR = height * 2 / 6.0f;
        int count = 1;
        while (count <= nLines) {
            float startX = width / 2f + cos(angle) * starR;
            float startY = height / 2f + sin(angle) * starR;
            float endX = width / 2f + cos(mIntersect * angleChange + angle) * starR;
            float endY = height / 2f + sin(mIntersect * angleChange + angle) * starR;
            line(startX, startY, endX, endY);
            angle += 5 * angleChange;
            count++;
        }
    }

    public static void main(String[] args) {
        PApplet.main("Problem08");
    }
}

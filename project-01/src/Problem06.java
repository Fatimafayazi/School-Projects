import processing.core.PApplet;

public class Problem06 extends PApplet {
    float xCoord = 12 * width;
    float yCoord = height / 7f;
    static final float RECT_WIDTH = 180;
    static final float RECT_HEIGHT = 34;
    float alpha, beta;
    float circleNum = 90;
    float r, dr;

    public void settings() {
        fullScreen();
    }

    public void setup() {
        r = 20;
        noStroke();
    }

    public void draw() {
        background(0);
        pushMatrix();
        translate(width / 2f, height / 2f);
        if (xCoord <= mouseX && mouseX <= xCoord + RECT_WIDTH && yCoord <= mouseY && mouseY <= yCoord + RECT_HEIGHT) {
            stroke(255, 0, 0);
            circle(0,0, 3 * (min(width,height) / 11f));
        }
        if (xCoord <= mouseX && mouseX <= xCoord + xCoord + RECT_WIDTH / 2 &&  yCoord + RECT_HEIGHT <= mouseY && mouseY <= yCoord + height / 11.5f) {
            noFill();
            stroke(255, 0, 0);
            circle(0,0, 5.5f * (min(width,height) / 11f));
        }
        if (xCoord <= mouseX && mouseX <= xCoord + RECT_WIDTH && yCoord + 2* RECT_HEIGHT <= mouseY && mouseY <= yCoord + height / 8f) {
            noFill();
            stroke(255, 0, 0);
            circle(0,0, 10f * (min(width,height) / 11f));
        }
        rotate(alpha);
        r = min(width, height) / 11f;
        dr = r / circleNum;
        float yellowComp = 10;
        float dYellowComp = 225f / circleNum;
        for ( int i = 0; i < circleNum; ++i) {
            fill(yellowComp, yellowComp, 0);
            noStroke();
            circle(0,0,r * 3);
            r -= dr;
            yellowComp += dYellowComp;
        }
        translate (width/8f, height / 8f);
        r = min(width, height) / 9f;
        dr = r / circleNum;
        float aquaComp = 10;
        float dAquaComp = 225f / circleNum;
        if (xCoord <= mouseX && mouseX <= xCoord + xCoord + RECT_WIDTH / 2 && yCoord + RECT_HEIGHT <= mouseY && mouseY <= yCoord + height / 11.5f) {
            noFill();
            stroke(255,0,0);
            circle(0,0,min(width,height) / 9f);
        }
        for (int i = 0; i < circleNum; i++){
            noStroke();
            fill(0, aquaComp, aquaComp);
            circle (0, 0,r);
            r -= dr;
            aquaComp += dAquaComp;
        }
        translate(width / 10f, height / 10f);
        r = min(width, height) / 8f;
        dr = r / circleNum;
        float blueComp = 10;
        float dBlueComp = 225f / circleNum;
        if (xCoord <= mouseX && mouseX <= xCoord + RECT_WIDTH && yCoord + 2 * RECT_HEIGHT <= mouseY && mouseY <= yCoord + height / 8f){
            stroke ( 255, 0, 0);
            noFill();
            circle(0,0,r);
        }
        if (xCoord <= mouseX && mouseX <= xCoord + xCoord + RECT_WIDTH / 2 && yCoord + 3 * RECT_HEIGHT <= mouseY && mouseY <= yCoord + height / 6.2f){
            stroke ( 255, 0, 0);
            noFill();
            circle(0,0,r *1.2f);
        }
        for (int i = 0; i < circleNum; i++){
            noStroke();
            fill(0,0,blueComp);
            circle(0,0,r);
            r -= dr;
            blueComp += dBlueComp;
        }
        rotate(beta);
        translate(width / 132f, height / 13f);
        r = min(width, height) / 11f;
        dr = r / circleNum;
        float whiteComp = 10;
        float dWhiteComp = 225f / circleNum;
        for (int i = 0; i < circleNum; i++){
            noStroke();
            fill(whiteComp, whiteComp, whiteComp);
            circle(0,0,r / 3f);
            r -= dr;
            whiteComp += dWhiteComp;
        }
        popMatrix();
        stroke(225, 225, 225);
        fill(0,0,225);
        rect(xCoord, yCoord, RECT_WIDTH, RECT_HEIGHT);
        textSize(30);
        fill(225, 225, 225);
        text("Sun", (xCoord + RECT_WIDTH / 2F), height / 20F);
        textAlign(CENTER);
        if( xCoord <= mouseX && mouseX <= xCoord + RECT_WIDTH && yCoord <= mouseY && mouseY <= yCoord + RECT_HEIGHT){
            fill(225, 0, 0);
            text("Sun", (xCoord + RECT_WIDTH / 2F), height / 20f);
        }
        stroke(225, 225, 225);
        fill(0,0,225);
        rect(xCoord, yCoord + RECT_HEIGHT, RECT_WIDTH, RECT_HEIGHT);
        textSize(30);
        fill(225, 225, 225);
        textAlign(CENTER);
        text("Venus", (xCoord + RECT_WIDTH / 2), height / 11f);
        if (xCoord <= mouseX && mouseX <= xCoord + xCoord + RECT_WIDTH / 2 && yCoord + RECT_HEIGHT <= mouseY && mouseY <= yCoord + height / 11.5f){
            fill(225, 0 ,0);
            text( "Venus", (xCoord + RECT_WIDTH / 2), height / 11f);
        }
        stroke(225, 225, 225);
        fill(0,0,225);
        rect(xCoord, yCoord + 2 * RECT_HEIGHT, RECT_WIDTH, RECT_HEIGHT);
        textSize(30);
        fill(225,225,225);
        textAlign(CENTER);
        text("Earth", (xCoord + RECT_WIDTH / 2), height / 8f);
        if (xCoord <= mouseX && mouseX <= xCoord + RECT_WIDTH && yCoord + 2 * RECT_HEIGHT <= mouseY && mouseY <= yCoord + height / 8f){
            fill(225, 0 ,0);
            text( "Earth", (xCoord + RECT_WIDTH / 2), height / 8f);
        }
        stroke(225, 225, 225);
        fill(0,0,225);
        rect(xCoord, yCoord + 3 * RECT_HEIGHT, RECT_WIDTH, RECT_HEIGHT);
        textSize(30);
        fill(225,225,225);
        textAlign(CENTER);
        text("Moon", (xCoord + RECT_WIDTH/ 2), height / 6f);
        if (xCoord <= mouseX && mouseX <= xCoord + xCoord + RECT_WIDTH / 2 && yCoord + 3 * RECT_HEIGHT <= mouseY && mouseY <= yCoord + height / 6.2f){
            fill(225, 0 ,0);
            text( "Moon", (xCoord + RECT_WIDTH / 2), height / 6f);
        }
        alpha += 0.02f;
        beta += 0.03;
    }

    public static void main(String[] args) {
        PApplet.main("Problem06");
    }
}

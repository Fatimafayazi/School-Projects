import processing.core.*;
import java.util.Random;

public class Fifteen extends PApplet {
    static int numOfShuffleMoves;
    static Random rnd = new Random();
    float x;
    float y;
    float scale;
    int [][]board = {
            {1, 2, 3, 4,5},
            {6, 7, 8, 9,10},
            {11, 12, 13, 14,15},
            {16, 17, 18, 19,20},
            {21, 22, 23,24, 25}
    };
    int nMoves = 0;
    int emptyRow ;
    int emptyCol ;
    float xLeftTop;
    float yLeftTop;

    public void settings() {
        fullScreen();
    }

    public void setup() {
        x = width / 3f;
        y = height / 5f;
        scale = height/ 8f;
        numOfShuffleMoves = 300;
        emptyRow = 4;
        emptyCol = 4;
    }

    public void draw() {
        background(0);
        textAlign(CENTER, CENTER);
        textSize(38f);
        fill(255,255,0);
        text("Game 15", width / 2f, height / 8f);
        text("Moves: " + nMoves, width / 1.2f, height / 1.7f);
        if (!win()) {
            text("Start/Restart: Enter", width / 2f, height / 1.3f);
        }
        if (win()) {
            textSize(38f);
            fill(255, 192, 203);
            text("You won. Number of moves is " + nMoves + ".Press Enter to continue", width / 2.3f, height / 1.1f);
        }
        drawBoard();
    }

    void drawBoard() {
        for (int row = 0; row < 5; ++row) {
            for (int col = 0; col < 5; ++col) {
                stroke(153,153,153);
                strokeWeight(3);
                if (board[row][col] != 25) {
                    fill(0, 0, 255);
                    rect(x + scale * col, y + scale * row, scale, scale);
                    fill(255f, 255f, 0);
                    text(board[row][col], x + col * scale + scale / 2f, y + row * scale + scale / 2f);
                } else {
                    fill(0);
                    rect(x + scale * col, y + scale * row, scale, scale);
                }
            }
        }
    }

    public void keyReleased() {
        switch (keyCode) {
            case UP : {
                moveNumber(-1, 0);
                ++nMoves;
            }
            case DOWN : {
                moveNumber(1, 0);
                ++nMoves;
            }
            case LEFT : {
                moveNumber(0, -1);
                ++nMoves;
            }
            case RIGHT : {
                moveNumber(0, 1);
                ++nMoves;
            }
            case ENTER : {
                shuffleBoard();
                nMoves = 0;
            }
        }
    }

    boolean win() {
        boolean win = nMoves != 0 && board[0][0] == 1 && board[0][1] == 2 && board[0][2] == 3 && board[0][3] == 4 &&
                board[1][0] == 5 && board[1][1] == 6 && board[1][2] == 7 && board[1][3] == 8 && board[2][0] == 9 &&
                board[2][1] == 10 && board[2][2] == 11 && board[2][3] == 12 && board[3][0] == 13 && board[3][1] == 14
                && board[3][2] == 15 && board[3][3] == 16;
        return win;
    }

    boolean insideTheRect(float xRect, float yRect, float scale, float scale1, float x, float y) {
        if (x >= xRect && xRect + scale > x && yRect <= y && yRect + scale1 > y) {
            return true;
        } else {
            return false ;
        }
    }

    public void mouseReleased() {
        for (int row = 0; row < 5; ++row) {
            for (int col = 0; col < 5; ++col) {
                float topLeftRectX = col * scale + x;
                float topLeftRectY = row * scale + y;
                if(insideTheRect(topLeftRectX, topLeftRectY, scale,scale,mouseX,mouseY)) {
                    if(row - 1 > 0 && board[row - 1][col] == 25) {
                        int temp = board[row][col];
                        board[row][col] = 25;
                        board[row -1][col] = temp;
                        nMoves++;
                    }
                    if(row + 1 < 5 && board[row + 1][col] == 25) {
                        int temp = board[row][col];
                        board[row][col] = 25;
                        board[row + 1][col] = temp;
                        nMoves++;
                    }
                    if(col - 1 > 0 && board[row][col -1] == 25) {
                        int temp = board[row][col];
                        board[row][col] = 25;
                        board[row][col -1] = temp;
                        nMoves++;
                    }
                    if(col + 1 < 5 && board[row][col + 1] == 25) {
                        int temp = board[row][col];
                        board[row][col] = 25;
                        board[row][col + 1] = temp;
                        nMoves++;
                    }
                }
            }
        }
    }

    private void moveNumber(int dr, int dc) {
        if(emptyRow + dr >= 0 && emptyRow + dr < 5 && emptyCol + dc >= 0 && emptyCol + dc<5) {
            int newRow = emptyRow + dr;
            int newCol = emptyCol + dc;
            int temp = board[newRow][newCol];
            board[newRow][newCol] = 25;
            board[emptyRow][emptyCol] = temp;
            emptyRow = newRow;
            emptyCol = newCol;
            nMoves++;
        }
    }

    void shuffleBoard() {
        int nMove = 0;
        while (nMove < numOfShuffleMoves) {
            int direction = rnd.nextInt(4);
            int dr = 0;
            int dc = 0;
            switch (direction) {
                case 0 : dr = -1;
                case 1 : dc = 1;
                case 2 : dr = 1;
                case 3 : dc = -1;
            }
            if (0 <= emptyRow + dr && emptyRow + dr < 5 && 0 <= emptyCol + dc && emptyCol + dc < 5) {
                board[emptyRow][emptyCol] = board[emptyRow + dr][emptyCol + dc];
                emptyRow += dr;
                emptyCol += dc;
                board[emptyRow][emptyCol] = 25;
                ++nMove;
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main("Fifteen");
    }
}

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class CellView {
    private static final Color OCCUPIED_CELL_COLOR = new Color(238, 228, 218);
    private static final Color OCCUPIED_CELL_TEXT_COLOR = new Color (119, 110, 101);
    private static final String OCCUPIED_CELL_FONT = "Arial";

    private static final float SIZE_ANIM_SPEED = 100.0f;
    private static final float MOVEMENT_ANIM_SPEED = 1000.0f;

    private int x, y;
    private final Queue<Board.Point> queueMovements;
    private Integer nextX = null, nextY = null;
    private float pixelX = 0.0f, pixelY = 0.0f;

    private int number;
    private final int goal;

    private boolean appearing = true;
    private float animSize = 1.0f;

    public CellView(int x, int y, int number, int goal){
        queueMovements = new LinkedList<>();
        this.x = x;
        this.y = y;
        this.number = number;
        this.goal = goal;
    }

    public void queueMovements(int x, int y) {
        queueMovements.add(new Board.Point(x, y));
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void draw(Graphics2D g2, float dt, int cellSize, int ctrShiftX, int ctrShiftY, int padding, int cornerRadius) {
        if(appearing) {
            animSize += SIZE_ANIM_SPEED * dt;
            if (Math.abs(animSize - cellSize) < 2) {
                animSize = cellSize;
                appearing = false;
            }
        } else {
            animSize = cellSize;
        }

        if (nextX == null && nextY == null && !queueMovements.isEmpty()) {
            var next = queueMovements.remove();
            nextX = ctrShiftX + next.getX() * cellSize;
            nextY = ctrShiftY + next.getY() * cellSize;
            pixelX = ctrShiftX + x * cellSize;
            pixelY = ctrShiftY + y * cellSize;
        }

        if (nextX != null && nextY != null) {
            float nextPointX = ctrShiftX + nextX * cellSize;
            float nextPointY = ctrShiftY + nextY * cellSize;
            float nextPixelX = 0;
            float nextPixelY = 0;
            float dx = nextPixelX - pixelX;
            float dy = nextPixelY - pixelY;
            float length = (float) Math.sqrt(dx * dx + dy * dy);
            if (length < 2) {
                pixelX = nextPixelX;
                pixelY = nextPixelY;
                x = nextX;
                y = nextY;
                nextX = null;
                nextY = null;
            } else {
                dx /= length;
                dy /= length;

                pixelX += dx * MOVEMENT_ANIM_SPEED * dt;
                pixelY += dy * MOVEMENT_ANIM_SPEED * dt;
            }
        } else {
            int pixelX = ctrShiftX + x * cellSize;
            int pixelY = ctrShiftY + y * cellSize;
        }

        float cellCtrShift = (cellSize - animSize) / 2.0f;

        g2.setColor(OCCUPIED_CELL_COLOR);
        g2.fillRoundRect(
                (int) (padding + pixelX + cellCtrShift),
                (int) (padding + pixelY + cellCtrShift),
                (int) (animSize - 2 * padding),
                (int) (animSize -2 * padding),
                cornerRadius, cornerRadius
        );

        String numAsText = String.valueOf(number);
        int numLength = numAsText.length();
        String goalAsText = String.valueOf(goal);
        int goalLength = goalAsText.length();

        float fontScale = (float) (numLength - 1) / (float) (goalLength - 1);
        fontScale = (float) Math.pow(fontScale - 1.5f, 2) * 0.13f * 0.45f;
        int fontSize = (int) (animSize * 0.53 * fontScale);
        g2.setColor(OCCUPIED_CELL_TEXT_COLOR);
        g2.setFont(new Font(OCCUPIED_CELL_FONT, Font.BOLD, fontSize));

        var fontMetrics = g2.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(numAsText);
        int textHeight = fontMetrics.getHeight();

        g2.drawString(
                numAsText,
                padding + pixelX + cellCtrShift + (animSize - 2 * padding) / 2 - textWidth / 1.95f,
                padding + pixelY + cellCtrShift + (animSize - 2 * padding) / 2 + textHeight / 3.25f
        );
    }
}

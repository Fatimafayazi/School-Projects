import javax.swing.*;
import java.awt.*;

public class BoardView extends JPanel{
    private static final Color BOARD_COLOR = new Color(189, 172, 159);
    private static final Color EMPTY_CELL_COLOR =   new Color(205, 190, 180);

    private final Board board;

    private final CellView[][] cellViews;

    private long prevFrameTime = 0;

    public BoardView(Board board) {
        cellViews = new CellView[board.getSize()][board.getSize()];

        board.addCellCreatedListener((x, y, number) -> {
            cellViews[y][x] = new CellView(x, y, number, board.getGoal());
            //TODO
        });
        board.addCellMovedListener((fromX, fromY, toX, toY) -> {
            cellViews[toY][toX] = cellViews[fromY][fromX];
            cellViews[toY][toX].queueMovements(toX, toY);
            cellViews[fromY][fromX] = null;
            //TODO
        });
        board.addCellMergedFromListener((fromX, fromY, toX, toY, mergeNumber) -> {
            cellViews[toY][toX].setNumber(mergeNumber);
            //TODO
        });
        board.addCellMergedToListener((toX, toY, fromX, fromY) -> {
            cellViews[fromY][fromX] = null;
            //TODO
        });

        for (int y = 0; y < board.getSize(); y++) {
            for (int x = 0; x < board.getSize(); x++) {
                int num = board.getNumber(x, y);
                if (num != 0) {
                    cellViews[y][x] = new CellView(x, y, num, board.getGoal());
                }
            }
        }

        this.board = board;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        long frameTime = System.currentTimeMillis();
        float dt = Math.min((frameTime - prevFrameTime) / 1000.0f, 0.1f);
        prevFrameTime = frameTime;

        Graphics2D g2 = (Graphics2D) g;

        int cellSize = (int)(Math.min(getWidth(), getHeight()) / board.getSize() * 0.8);
        int boardScreenSize = cellSize * board.getSize();
        int ctrShiftX = (getWidth() - boardScreenSize) / 2;
        int ctrShiftY = (getHeight() - boardScreenSize) / 2;
        int padding = (int) (cellSize * 0.043);
        int cornerRadius = (int) (cellSize * 0.18);

        g2.setColor(BOARD_COLOR);
        g2.fillRoundRect(
                ctrShiftX - padding, ctrShiftY - padding,
                boardScreenSize + 2*padding, boardScreenSize + 2*padding,
                cornerRadius, cornerRadius
        );

        for (int y = 0; y < cellViews.length; y++) {
            for (int x = 0; x < cellViews[0].length; x++) {
                int pixelX = ctrShiftX + y * cellSize;
                int pixelY = ctrShiftY + y * cellSize;
                g2.setColor(EMPTY_CELL_COLOR);
                g2.fillRoundRect(
                        padding + pixelX, padding + pixelY,
                        cellSize - 2 * padding, cellSize -2 * padding,
                        cornerRadius, cornerRadius
                );
            }
        }

        for (int y = 0; y < cellViews.length; y++) {
            for (int x = 0; x < cellViews[0].length; x++) {
                var cellView = cellViews[y][x];
                if (cellView != null) {
                    cellView.draw(g2, dt, cellSize, ctrShiftX, ctrShiftY, padding, cornerRadius);
                }
            }
        }

        repaint();
    }
}

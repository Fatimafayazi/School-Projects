import java.util.ArrayList;
import java.util.List;

public class Board {
    static class Point {
        private final int x, y;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    interface CellCreatedListener {
        void cellCreated(int x, int y, int number);
    }

    interface CellMovedListener {
        void cellMoved(int fromX, int fromY, int toX, int toY);
    }

    interface CellMergedFromListener {
        void cellMergedFrom(int fromX, int fromY, int toX, int toY, int mergedNumber);
    }

    interface CellMergedToListener {
        void cellMergedTo(int toX, int toY, int fromX, int fromY);
    }

    private final static int DEFAULT_BOARD_SIZE = 4;
    private final static int FIRST_NUMBER = 2;

    private final int[][] cells;
    private int maxNumber;
    private int goal = 2048;

    private List<CellCreatedListener> cellCreatedListeners =
            new ArrayList<>();
    private List<CellMovedListener> cellMovedListeners =
            new ArrayList<>();
    private List<CellMergedFromListener> cellMergedFromListeners =
            new ArrayList<>();
    private List<CellMergedToListener> cellMergedToListeners =
            new ArrayList<>();

    public Board() {
        this(DEFAULT_BOARD_SIZE);
    }

    public Board(int size) {
        this(new int[size][size]);
        createRandomCells(2);
    }

    public Board(int[][] cells) {
        assert cells.length > 0 && cells.length == cells[0].length;

        cellCreatedListeners = new ArrayList<>();

        maxNumber = cells[0][0];
        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[0].length; x++) {
                if (cells[y][x] > maxNumber) {
                    maxNumber = cells[y][x];
                }
            }
        }

        this.cells = cells;
    }

    public int getSize() {
        return cells.length;
    }

    public int getNumber(int x, int y) {
        return cells[y][x];
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public int getGoal() {
        return  goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public void addCellCreatedListener(CellCreatedListener listener) {
        cellCreatedListeners.add(listener);
    }

    public void addCellMovedListener(CellMovedListener listener) {
        cellMovedListeners.add(listener);
    }

    public void addCellMergedFromListener(CellMergedFromListener listener) {
        cellMergedFromListeners.add(listener);
    }

    public void addCellMergedToListener(CellMergedToListener listener) {
        cellMergedToListeners.add(listener);
    }

    public void removeCellCreatedListener(CellCreatedListener listener) {
        cellCreatedListeners.remove(listener);
    }

    public void removeCellMovedListener(CellMovedListener listener) {
        cellMovedListeners.remove(listener);
    }

    public void removeCellMergedFromListener(CellMergedFromListener listener) {
        cellMergedFromListeners.remove(listener);
    }

    public void removeCellMergedToListener(CellMergedToListener listener) {
        cellMergedToListeners.remove(listener);
    }

    public boolean hasMoreMoves() {
        for (int y = 0; y < getSize(); y++) {
            for (int x = 0; y < getSize(); x++) {
                int num = getNumber(x, y);
                if (num == 0 ||
                    areCoordsInside(x, y - 1) && getNumber(x, y - 1) == num ||
                    areCoordsInside(x, y + 1) && getNumber(x, y + 1) == num ||
                    areCoordsInside(x - 1, y) && getNumber(x - 1, y) == num ||
                    areCoordsInside(x + 1, y) && getNumber(x + 1, y) == num) {
                    return true;
                }
            }
        }
        return false;
    }

    public void moveUp() {
        moveUp(1);
    }

    public void moveUp(int cellsToGenerate) {
        for (int x = 0; x < getSize(); x++) {
            mergeCellsOnCol(x,-1);
            moveCellsOnCol(x, -1);
        }

        if (cellsToGenerate > 0) {
            createRandomCells(cellsToGenerate);
        }
    }

    public void moveDown() {
        moveDown(1);
    }

    public void moveDown(int cellsToGenerate) {
        for (int x = 0; x < getSize(); x++) {
            mergeCellsOnCol(x,1);
            moveCellsOnCol(x, 1);
        }

        if (cellsToGenerate > 0) {
            createRandomCells(cellsToGenerate);
        }
    }

    public void moveLeft() {
        moveLeft(1);
    }

    public void moveLeft(int cellsToGenerate) {
        for (int y = 0; y < getSize(); y++) {
            mergeCellsOnRow(y, -1);
            moveCellsOnRow(y, -1);
        }

        if (cellsToGenerate > 0) {
            createRandomCells(cellsToGenerate);
        }
    }

    public void moveRight() {
        moveRight(1);
    }

    public void moveRight(int cellsToGenerate) {
        for (int y = 0; y < getSize(); y++) {
            mergeCellsOnRow(y,1);
            moveCellsOnRow(y, 1);
        }

        if (cellsToGenerate > 0) {
            createRandomCells(cellsToGenerate);
        }
    }

    @Override
    public String toString() {
        var res = new  StringBuilder();

        String format = "%" + String.valueOf(getMaxNumber()).length() + "s";
        for (int y = 0; y < getSize(); y++) {
            for (int x = 0; x < getSize(); x++ ) {
                int num = getNumber(x, y);
                if (num == 0) {
                    res.append(String.format(format, "-"));
                } else {
                    res.append(String.format(format, num));
                }
            }
            res.append("\n");
        }

        return res.toString();
    }

    public String toStringWithoutFormatting() {
        var res = new  StringBuilder();

        for (int y = 0; y < getSize(); y++) {
            for (int x = 0; x < getSize(); x++ ) {
                res.append(getNumber(x, y));
                if (x < getSize() - 1) {
                    res.append(" ");
                }
            }
            res.append("\n");
        }

        return res.toString();
    }

    private void setNumber(int x, int y, int number) {
        cells[y][x] = number;

    }

    private void createRandomCells(int amount) {
        var unoccupiedCells = new ArrayList<Point>();
        for (int y = 0; y < getSize(); y++) {
            for (int x = 0; x < getSize(); x++ ) {
                if (getNumber(x, y) == 0) {
                    unoccupiedCells.add(new Point(x, y));
                }
            }
        }

        for (int i = 0; i < amount && unoccupiedCells.size() > 0; i++) {
            int randomIndex = (int) (Math.random() * unoccupiedCells.size());
            Point point = unoccupiedCells.get(randomIndex);
            setNumber(point.x, point.y, FIRST_NUMBER);
            if (FIRST_NUMBER > maxNumber) {
                maxNumber = FIRST_NUMBER;
            }
            for (var Listener : cellCreatedListeners) {
                Listener.cellCreated(point.x, point.y, FIRST_NUMBER);
            }
            unoccupiedCells.remove(randomIndex);
        }
    }

    private void moveCellsOnCol(int x, int dy) {
        if (dy < 0) {
            for (int y = 0; y < getSize(); y++) {
                moveCell(x, y, 0, dy);
            }
        } else {
            for (int y = getSize() - 1; y >= 0; y--) {
                moveCell(x, y, 0, dy);
            }
        }
    }

    private void moveCellsOnRow(int y, int dx) {
        if (dx < 0) {
            for (int x = 0; x < getSize(); x++) {
                moveCell(x, y,  dx, 0);
            }
        } else {
            for (int x = getSize() - 1; x >= 0; x--) {
                moveCell(x, y, dx, 0);
            }
        }
    }

    private void mergeCellsOnCol(int x, int dy) {
        int mergeTargetRow = -1;
        if (dy < 0) {
            for (int y = 0; y < getSize(); y++) {
                mergeTargetRow = performMergeOnRow(x, y, mergeTargetRow);
            }
        } else {
            for (int y = getSize() -1; y >= 0; y--) {
                mergeTargetRow = performMergeOnRow(x, y, mergeTargetRow);
            }
        }
    }

    private int performMergeOnRow(int x, int y, int mergeTargetRow) {
        int num = getNumber(x,y);
        if (num != 0) {
            if (mergeTargetRow == -1 || getNumber(x, mergeTargetRow) != num) {
                mergeTargetRow = y;
            } else {
                int mergedNum = getNumber(x, mergeTargetRow) + num;
                setNumber(x, mergeTargetRow, mergedNum);
                setNumber(x, y, 0);
                if (mergedNum > maxNumber) {
                    maxNumber = mergedNum;
                }
                for (var Listener : cellMergedFromListeners) {
                    Listener.cellMergedFrom(x, y, x, mergeTargetRow, mergedNum);
                }
                for (var Listener : cellMergedToListeners) {
                    Listener.cellMergedTo(mergeTargetRow, x, y);

                }
                mergeTargetRow = -1;
            }
        }
        return mergeTargetRow;
    }

    private void mergeCellsOnRow(int y, int dx) {
        int mergeTargetCol = -1;
        if (dx < 0) {
            for (int x = 0; x < getSize(); x++) {
                mergeTargetCol = performMergeOnCol(x, y, mergeTargetCol);
            }
        } else {
            for (int x = getSize() - 1; x >= 0; x--) {
                mergeTargetCol = performMergeOnCol(x, y, mergeTargetCol);
            }
        }
    }

    private int performMergeOnCol(int x, int y, int mergeTargetCol) {
        int num = getNumber(x, y);
        if (num != 0) {
            if (mergeTargetCol == -1 || getNumber(mergeTargetCol, y) != num) {
                mergeTargetCol = x;
            } else {
                int mergedNum = getNumber(mergeTargetCol, y) + num;
                setNumber(mergeTargetCol, y, mergedNum);
                setNumber(x, y, 0);
                if (mergedNum > maxNumber) {
                    maxNumber = mergedNum;
                }
                for (var Listener : cellMergedFromListeners) {
                    Listener.cellMergedFrom(x, y, mergeTargetCol, y, mergedNum);
                }
                for (var Listener : cellMergedToListeners) {
                    Listener.cellMergedTo(mergeTargetCol, y, x, y);
                }
                mergeTargetCol = -1;
            }
        }
        return mergeTargetCol;
    }

    private void moveCell(int x, int y, int dx, int dy) {
        if (getNumber(x, y) == 0) return;

        int fromX = x + dx;
        int fromY = y + dy;

        int nextX = x + dx;
        int nextY = y + dy;
        while (areCoordsInside(nextX, nextY) && getNumber(nextX, nextY) == 0) {
            setNumber(nextX, nextY, getNumber(x, y));
            setNumber(x, y, 0);

            x = nextX;
            y= nextY;
            nextX += dx;
            nextY += dy;
        }

        if (fromX != x || fromY != y) {
            for (var Listener : cellMovedListeners) {
                Listener.cellMoved(fromX, fromY, x, y);
            }
        }
    }

    private boolean areCoordsInside(int x, int y) {
        return x >= 0 && x < getSize() &&
                y >= 0 && y < getSize();
    }
}

package model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author maikel
 */
public class Game implements Iterable<Set<Cell>> {

    final static public int GRIDSIZE = 3;
    private Cell[][] grid;
    private Cell lastMove;

    public Game() {
        reset();
    }

    public final void reset() {
        grid = new Cell[GRIDSIZE][GRIDSIZE];
        lastMove = Cell.O;
    }

    public void doMove(int i, int j) {
        if (grid[i][j] == null) {
            if (lastMove == Cell.O) {
                doMove(i, j, Cell.X);
            } else {
                doMove(i, j, Cell.O);
            }
        }
    }

    public Cell getCell(int i, int j) {
        return grid[i][j];
    }

    private void doMove(int i, int j, Cell c) {
        grid[i][j] = c;
        lastMove = c;
    }

    public boolean hasFinished() {
        return isFull() || hasWon(Cell.O) || hasWon(Cell.X);
    }

    public boolean isFull() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hasWon(Cell cell) {
        for (Set<Cell> line : this) {
            if (isWinningLine(line, cell)) {
                return true;
            }
        }
        return false;
    }

    private boolean isWinningLine(Set<Cell> line, Cell cell) {
        boolean result = true;
        for (Cell c : line) {
            result = result && c == cell;
        }
        return result;
    }

    @Override
    public LineIterator iterator() {
        return new LineIterator();
    }

    /**
     * Inner iteration class
     */
    public class LineIterator implements Iterator<Set<Cell>> {

        int horizontal = 0;
        int vertical = 0;
        int diagonal = 0;

        @Override
        public boolean hasNext() {
            return (horizontal < GRIDSIZE || vertical < GRIDSIZE || diagonal < 2);
        }

        @Override
        public Set<Cell> next() {
            if (horizontal < GRIDSIZE) {
                return horizontalSet();
            } else if (vertical < GRIDSIZE) {
                return verticalSet();
            } else if (diagonal < 2) {
                return diagonalSet();
            } else {
                return null;
            }
        }

        private Set<Cell> horizontalSet() {
            Set<Cell> result = new HashSet<Cell>();

            for (int i = 0; i < GRIDSIZE; i++) {
                result.add(grid[horizontal][i]);
            }
            horizontal++;

            return result;
        }

        private Set<Cell> verticalSet() {
            Set<Cell> result = new HashSet<Cell>();

            for (int i = 0; i < GRIDSIZE; i++) {
                result.add(grid[i][vertical]);
            }
            vertical++;
            
            return result;
        }

        private Set<Cell> diagonalSet() {
            return (diagonal++ == 0) ? diagonalSet1() : diagonalSet2();
        }

        private Set<Cell> diagonalSet1() {
            Set<Cell> result = new HashSet<Cell>();

            for (int i = 0; i < GRIDSIZE; i++) {
                result.add(grid[i][i]);
            }

            return result;
        }

        private Set<Cell> diagonalSet2() {
            Set<Cell> result = new HashSet<Cell>();

            for (int i = 0; i < GRIDSIZE; i++) {
                result.add(grid[i][GRIDSIZE - i - 1]);
            }

            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}

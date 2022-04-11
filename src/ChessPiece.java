public abstract class ChessPiece {
    protected String color;
    boolean check = true;

    public ChessPiece(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    private boolean notBeyondLimits(int toLine, int toColumn) {
        return toLine < 8 && toLine >= 0 && toColumn < 8 && toColumn >= 0;
    }

    private boolean notSameTile(int line, int column, int toLine, int toColumn) {
        return !(line == toLine && column == toColumn);
    }

    public boolean isUnderAttack(ChessPiece[][] board, int line, int column) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    if (board[i][j].canMoveToPosition(board, i, j, line, column) &&
                            !board[i][j].getColor().equals(this.getColor()))
                        return true;
                }
            }
        }
        return false;
    }

    public abstract boolean canMoveToPosition(ChessPiece[][] board, int line,
                                              int column, int toLine, int toColumn);

    protected boolean conditionsArePassed(ChessPiece[][] board, int startLine,
                                          int startColumn, int endLine, int endColumn) {
        return notBeyondLimits(startLine, startColumn) && notSameTile(startLine, startColumn, endLine, endColumn) &&
                board[startLine][startColumn]!=null&&
                this.color.equals(board[startLine][startColumn].getColor()) &&
                (board[endLine][endColumn] == null || !board[endLine][endColumn].getColor().equals(this.color));
    }

    protected boolean noOneOnTheLine(ChessPiece[][] board, int line, int column, int toLine, int toColumn) {
        int lineUp, lineDown;
        if (toLine < line) {
            lineUp = line;
            lineDown = toLine;
        } else {
            lineUp = toLine;
            lineDown = line;
        }

        int columnRight, columnLeft;
        if (toColumn < column) {
            columnRight = column;
            columnLeft = toColumn;
        } else {
            columnRight = toColumn;
            columnLeft = column;
        }

        //diagonals:
        if (toLine - line == toColumn - column) {
            for (int i = lineDown + 1; i < lineUp; i++) {
                if (board[i][columnLeft++] != null)
                    return false;
            }
        } else if (toLine - line == column - toColumn) {
            for (int i = lineUp - 1; i > lineDown; i--) {
                if (board[i][columnLeft++] != null)
                    return false;
            }
        }

        //perpendicular lines:
        if (column == toColumn) {
            for (int i = lineDown + 1; i < lineUp; i++) {
                if (board[i][column] != null)
                    return false;
            }
        }

        if (line == toLine) {
            for (int i = columnLeft + 1; i < columnRight; i++) {
                if (board[line][i] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    public abstract String getSymbol();
}

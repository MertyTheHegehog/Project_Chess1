public interface WalksTheLines {

    default boolean noOneOnTheLine(ChessPiece[][] board, int line, int column, int toLine, int toColumn) {
        int lineUp, lineDown;
        if (toLine < line) {
            lineUp = line;
            lineDown = toLine;
        } else {
            lineUp = toLine;
            lineDown = line;
        }

        //diagonals:
        if (toLine - line == toColumn - column) {
            for (int i = lineDown + 1; i < lineUp; i++) {
                if (board[i][i] != null)
                    return false;
            }
            //!!!!!!!!!! - 1
        } else if (toLine - line == column - toColumn) {
            for (int i = lineUp - 1; i > lineDown; i--) {
                if (board[i][i] != null)
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
        int columnRight, columnLeft;
        if (toColumn < column) {
            columnRight = column;
            columnLeft = toColumn;
        } else {
            columnRight = toColumn;
            columnLeft = column;
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

}

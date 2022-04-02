public class Bishop extends ChessPiece {

    public Bishop(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessPiece[][] board, int line, int column, int toLine, int toColumn) {
        if (conditionsArePassed(board,line, column, toLine, toColumn) &&
                noOneOnTheLine(board, line, column, toLine, toColumn)) {
            return toLine - line == toColumn - column || toLine - line == column - toColumn;
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}

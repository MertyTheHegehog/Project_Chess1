public class Rook extends ChessPiece {
    public Rook(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessPiece[][] board, int line,
                                     int column, int toLine, int toColumn) {
        if (conditionsArePassed(board,line, column, toLine, toColumn) &&
                noOneOnTheLine(board, line, column, toLine, toColumn)) {
            if (line == toLine || column == toColumn) {
                this.check = false;
                return true;
            }
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}

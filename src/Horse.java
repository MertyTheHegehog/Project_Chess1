public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessPiece[][] board, int line,
                                     int column, int toLine, int toColumn) {
        if (notBeyondLimits(toLine, toColumn)) {
            if (column + 1 == toColumn && (line + 2 == toLine || line - 2 == toLine) ||
                    column + 2 == toColumn && (line + 1 == toLine || line - 1 == toLine) ||
                    column - 1 == toColumn && (line + 2 == toLine || line - 2 == toLine) ||
                    column - 2 == toColumn && (line + 1 == toLine || line - 1 == toLine)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}
